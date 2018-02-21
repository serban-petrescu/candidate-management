package ro.msg.cm.service;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ro.msg.cm.exception.*;
import ro.msg.cm.model.Candidate;
import ro.msg.cm.pojo.Duplicate;
import ro.msg.cm.repository.CandidateRepository;
import ro.msg.cm.types.CandidateCheck;
import ro.msg.cm.types.DuplicateType;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ValidationService {

    private final CandidateRepository candidateRepository;

    @Autowired
    public ValidationService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
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

    public void deleteSelectedEntries(List<Long> ids) {
        for (long id : ids) {
            if (candidateRepository.findByIdAndCheckCandidate(id, CandidateCheck.NOT_YET_VALIDATED)!=null) {
                candidateRepository.delete(id);
            }else {
                throw new CandidateNotFoundException("Candidate with id: " + id + " was not found");
            }
        }
    }

    public void validate(Long id) {
        if(candidateRepository.findCandidateById(id).isPresent()) {
            if (!duplicateOn(id, DuplicateType.ON_EMAIL)) {
                candidateRepository.setCheckCandidateForId(CandidateCheck.VALIDATED, id);
            }
        } else {
            throw new CandidateNotFoundException();
        }
    }

    public void validate(List<Long> ids) {
        for (long id : ids) {
            if (candidateRepository.findCandidateById(id).isPresent() && duplicateOn(id, DuplicateType.ON_EMAIL)) {
                ids.remove(id);
            }
        }
        candidateRepository.setCheckCandidateForIdIn(CandidateCheck.VALIDATED, ids);
    }

    private boolean duplicateOn(Long id, DuplicateType duplicateType) {
        DuplicateFinderService duplicateFinderService = new DuplicateFinderService(candidateRepository);
        List<Duplicate> duplicates = duplicateFinderService.getDuplicates(id, CandidateCheck.VALIDATED);
        boolean condition = false;
        for (Duplicate duplicate : duplicates) {
            condition = duplicate.getDuplicateType().equals(duplicateType);
            if (condition) {
                break;
            }
        }
        return condition;
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

    public List<Candidate> saveUnvalidatedCandidates(List<Candidate> candidates) {
        candidates.forEach(x -> x.setCheckCandidate(CandidateCheck.NOT_YET_VALIDATED));
        return candidateRepository.save(candidates);
    }
}

