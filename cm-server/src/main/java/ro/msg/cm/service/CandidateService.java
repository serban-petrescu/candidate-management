package ro.msg.cm.service;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.cm.dto.CandidateDto;
import ro.msg.cm.exception.CandidateIsAlreadyValidatedException;
import ro.msg.cm.exception.CandidateNotFoundException;
import ro.msg.cm.exception.PatchCandidateInvalidValueException;
import ro.msg.cm.mapper.CandidateMapper;
import ro.msg.cm.model.Candidate;
import ro.msg.cm.repository.CandidateRepository;
import ro.msg.cm.types.CandidateCheck;
import ro.msg.cm.types.DuplicateType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final DuplicateFinderService duplicateFinderService;
    /**
     * this regex matches Romanian phone numbers like:
     * +40741 234 567
     * +40741 23 45 67
     * +4 074 123 45 67
     * where all of the spaces are optional, therefore a number without spaces is also valid AND
     * +4 is optional OR
     * +4 can be replaced with 004
     */
    private static final String PHONE_REGEX="^(0|(0040|004\\s0)|(\\+40|\\+4\\s0))([0-9]{3}\\s?|[0-9]{2}\\s[0-9])(([0-9]{3}\\s?){2}|([0-9]{2}\\s?){3})$";

    @Autowired
    public CandidateService(CandidateRepository candidateRepository, DuplicateFinderService duplicateFinderService) {
        this.candidateRepository = candidateRepository;
        this.duplicateFinderService = duplicateFinderService;
    }

    public Candidate patchCandidate(CandidateDto patchCandidate, Long id) {
        Candidate candidate = candidateRepository.findByIdAndCheckCandidate(id, CandidateCheck.NOT_YET_VALIDATED);
        if (candidate != null) {
            if (!isEmailValid(patchCandidate.getEmail())) {
                throw new PatchCandidateInvalidValueException("Invalid eMail");
            }
            if (!isPhoneNrValid(patchCandidate.getPhone())) {
                throw new PatchCandidateInvalidValueException("Invalid phone number");
            }
            candidate = CandidateMapper.map(patchCandidate.getChangedAttributes(), candidate);
            return candidateRepository.save(candidate);
        } else {
            throw new CandidateNotFoundException();
        }
    }

    public Candidate updateCandidate(CandidateDto candidateDto, Long id) {
        Candidate candidate = candidateRepository.findByIdAndCheckCandidate(id, CandidateCheck.NOT_YET_VALIDATED);
        if (candidate != null) {
            candidate = CandidateMapper.map(candidateDto.toMap(), candidate);
            return candidateRepository.save(candidate);
        } else {
            throw new CandidateNotFoundException();
        }
    }

    public void deleteSelectedEntry(Long id) {
        Candidate candidate = candidateRepository.findByIdAndCheckCandidate(id, CandidateCheck.NOT_YET_VALIDATED);
        if (candidate != null) {
            candidateRepository.delete(id);
        } else {
            throw new CandidateIsAlreadyValidatedException();
        }
    }

    @Transactional
    public void deleteSelectedEntries(List<Long> ids) {
        for (long id : ids) {
            if (candidateRepository.findByIdAndCheckCandidate(id, CandidateCheck.NOT_YET_VALIDATED) != null) {
                candidateRepository.delete(id);
            } else {
                throw new CandidateNotFoundException("Candidate with id: " + id + " was not found");
            }
        }
    }

    public void validate(Long id) {
        if (candidateRepository.findCandidateById(id).isPresent()) {
            if (!duplicateOnEmail(id)) {
                candidateRepository.setCheckCandidateForId(CandidateCheck.VALIDATED, id);
            }
        } else {
            throw new CandidateNotFoundException();
        }
    }

    @Transactional
    public void validate(List<Long> ids) {
        Set<Long> longSet = new HashSet<>(ids);
        Set<Candidate> candidates = candidateRepository.findAllByIdIn(longSet);
        longSet = findSingleEmailCandidates(candidates);
        longSet = filterDuplicateOnEmail(longSet);
        if (!longSet.isEmpty()) {
            candidateRepository.setCheckCandidateForIdIn(CandidateCheck.VALIDATED, longSet);
        }
    }

    private Set<Long> findSingleEmailCandidates(Set<Candidate> candidates) {
        Set<String> emails = candidates.stream().map(Candidate::getEmail).collect(Collectors.toSet());
        Set<Long> longSet = new HashSet<>();
        for (Candidate candidate : candidates) {
            if (emails.remove(candidate.getEmail())) {
                longSet.add(candidate.getId());
            }
        }
        return longSet;
    }

    private boolean duplicateOnEmail(Long id) {
        return duplicateFinderService.getCountDuplicateOnDuplicateType(id, CandidateCheck.VALIDATED, DuplicateType.ON_EMAIL) > 0;
    }

    private Set<Long> filterDuplicateOnEmail(Set<Long> ids) {
        return (ids.isEmpty()) ? ids : candidateRepository.filterValidCandidates(ids).stream().map(Candidate::getId).collect(Collectors.toSet());
    }

    public List<Candidate> getValidCandidates() {
        return candidateRepository.findAllByCheckCandidate(CandidateCheck.VALIDATED);
    }

    public List<Candidate> getNonValidCandidates() {
        return candidateRepository.findAllByCheckCandidate(CandidateCheck.NOT_YET_VALIDATED);
    }

    /**
     *
     * @param object phone number to validate
     * @return true - is the given number matches aur regex
     */
    private boolean isPhoneNrValid(Object object) {
        return object == null || object.toString().matches(PHONE_REGEX);
    }

    private boolean isEmailValid(Object object) {
        return object == null || new EmailValidator().isValid(object.toString(), null);
    }

    public Candidate saveUnvalidatedCandidate(Candidate candidate) {
        candidate.setCheckCandidate(CandidateCheck.NOT_YET_VALIDATED);
        return candidateRepository.save(candidate);
    }

    @Transactional
    public List<Candidate> saveUnvalidatedCandidates(List<Candidate> candidates) {
        candidates.forEach(x -> x.setCheckCandidate(CandidateCheck.NOT_YET_VALIDATED));
        return (List<Candidate>) candidateRepository.save(candidates);
    }
}

