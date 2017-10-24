package ro.msg.cm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.cm.model.Candidate;
import ro.msg.cm.pojo.CountDuplicate;
import ro.msg.cm.pojo.Duplicate;
import ro.msg.cm.repository.CandidateRepository;
import ro.msg.cm.types.CandidateCheck;
import ro.msg.cm.types.DuplicateType;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DuplicateFinderService {

    private final CandidateRepository candidateRepository;

    @Autowired
    public DuplicateFinderService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    /**
     * Method that returns all the duplicates of the give Candidate through all the candidate that are of given status
     *
     * @param id is the id of the original Candidate you compare to
     * @param candidateCheck is the status of the candidates you search the duplicates in
     * @return the list of duplicates
     */
    public List<Duplicate> getDuplicates(Long id, CandidateCheck candidateCheck) {
        Candidate original = candidateRepository.findOne(id);
        List<Duplicate> allDuplicates = new ArrayList<>();
        
        if (original != null) {
            Set<Long> ids;
            ids = candidateRepository.findAllByFirstNameAndLastNameAndCheckCandidate(original.getFirstName(), original.getLastName(), candidateCheck).stream().map(Candidate::getId).collect(Collectors.toSet());
            ids.remove(original.getId());
            if (!ids.isEmpty()) {
                allDuplicates.add(new Duplicate(original.getId(), ids, DuplicateType.ON_NAME));
            }
            ids = candidateRepository.findAllByEmailAndCheckCandidate(original.getEmail(), candidateCheck).stream().map(Candidate::getId).collect(Collectors.toSet());
            ids.remove(original.getId());
            if (!ids.isEmpty()) {
                allDuplicates.add(new Duplicate(original.getId(), ids, DuplicateType.ON_EMAIL));
            }
            ids = candidateRepository.findAllByPhoneAndCheckCandidate(original.getPhone(), candidateCheck).stream().map(Candidate::getId).collect(Collectors.toSet());
            ids.remove(original.getId());
            if (!ids.isEmpty()) {
                allDuplicates.add(new Duplicate(original.getId(), ids, DuplicateType.ON_PHONE));
            }
        }
        return allDuplicates;
    }

    /**
     * Method that returns all the duplicates of the give Candidate through all the candidate that are of given status
     *
     * @param id             is the id of the original Candidate you compare to
     * @param candidateCheck is the status of the candidates you search the duplicates in
     * @return the list of duplicates
     */
    public CountDuplicate getCountDuplicate(Long id, CandidateCheck candidateCheck) {
        Candidate original = candidateRepository.findOne(id);
        Map<DuplicateType, Long> duplicateCount = new EnumMap<>(DuplicateType.class);
        if (original != null) {
            long correction = candidateCheck.equals(original.getCheckCandidate()) ? -1 : 0;
            duplicateCount.put(DuplicateType.ON_NAME, candidateRepository.countByFirstNameAndLastNameAndCheckCandidate(original.getFirstName(), original.getLastName(), candidateCheck) + correction);
            duplicateCount.put(DuplicateType.ON_EMAIL, candidateRepository.countByEmailAndCheckCandidate(original.getEmail(), candidateCheck) + correction);
            duplicateCount.put(DuplicateType.ON_PHONE, candidateRepository.countByPhoneAndCheckCandidate(original.getPhone(), candidateCheck) + correction);
        }
        return new CountDuplicate(id, duplicateCount);
    }
}
