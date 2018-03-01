package ro.msg.cm.service;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ro.msg.cm.exception.CandidateIsAlreadyValidatedException;
import ro.msg.cm.exception.CandidateNotFoundException;
import ro.msg.cm.exception.PatchCandidateInvalidKeyException;
import ro.msg.cm.exception.PatchCandidateInvalidValueException;
import ro.msg.cm.model.Candidate;
import ro.msg.cm.repository.CandidateRepository;
import ro.msg.cm.types.CandidateCheck;
import ro.msg.cm.types.DuplicateType;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ValidationService {

    private final CandidateRepository candidateRepository;
    private final DuplicateFinderService duplicateFinderService;

    @Autowired
    public ValidationService(CandidateRepository candidateRepository, DuplicateFinderService duplicateFinderService) {
        this.candidateRepository = candidateRepository;
        this.duplicateFinderService = duplicateFinderService;
    }

    public Candidate patchCandidate(Map<String, Object> patchCandidate, Long id) {
        Candidate candidate = candidateRepository.findByIdAndCheckCandidate(id, CandidateCheck.NOT_YET_VALIDATED);
        List<Field> fields = Arrays.asList(Candidate.class.getDeclaredFields());
        if (candidate != null) {
            for (Iterator<String> iterator = patchCandidate.keySet().iterator(); iterator.hasNext(); ) {
                String key = iterator.next();
                if (key.equalsIgnoreCase("email") &&
                        !isEmail(patchCandidate.get(key))) {
                    throw new PatchCandidateInvalidValueException();
                }
                if (key.equalsIgnoreCase("phone") &&
                        !isPhoneNr(patchCandidate.get(key))) {
                    throw new PatchCandidateInvalidValueException();
                }
            }
            patchCandidate.forEach((String k, Object v) -> {
                try {
                    Field field = fields.stream().filter(x -> x.getName().equalsIgnoreCase(StringUtils.trimAllWhitespace(k))).findFirst().orElseThrow(PatchCandidateInvalidKeyException::new);
                    Method method = Candidate.class.getDeclaredMethod("set" + StringUtils.capitalize(field.getName()), field.getType());
                    method.invoke(candidate, v);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    throw new PatchCandidateInvalidKeyException();
                }
            });
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

    private boolean isPhoneNr(Object object) {
        return object == null || object.toString().matches("^\\d{10,15}$");
    }

    private boolean isEmail(Object object) {
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

