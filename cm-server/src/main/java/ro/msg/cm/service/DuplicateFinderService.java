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
     * Method that returns the list of duplicates of every duplicate type on the given Candidate searching through all the candidate that are of the given status
     *
     * @param id is the id of the original Candidate you compare to
     * @param candidateCheck is the status of the candidates you search the duplicates in
     * @return the list of duplicates on all of the duplicate types
     */
    public List<Duplicate> getDuplicates(Long id, CandidateCheck candidateCheck) {
        Candidate original = candidateRepository.findOne(id);
        List<Duplicate> allDuplicates = new ArrayList<>();

        if (original != null) {
            Set<Long> ids;
            ids = candidateRepository.findAllByFirstNameAndLastNameAndCheckCandidateAndIdIsNot(original.getFirstName(), original.getLastName(), candidateCheck, original.getId()).stream().map(Candidate::getId).collect(Collectors.toSet());
            if (!ids.isEmpty()) {
                allDuplicates.add(new Duplicate(original.getId(), ids, DuplicateType.ON_NAME));
            }
            ids = candidateRepository.findAllByEmailAndCheckCandidateAndIdIsNot(original.getEmail(), candidateCheck, original.getId()).stream().map(Candidate::getId).collect(Collectors.toSet());
            if (!ids.isEmpty()) {
                allDuplicates.add(new Duplicate(original.getId(), ids, DuplicateType.ON_EMAIL));
            }
            ids = candidateRepository.findAllByPhoneAndCheckCandidateAndIdIsNot(original.getPhone(), candidateCheck, original.getId()).stream().map(Candidate::getId).collect(Collectors.toSet());
            if (!ids.isEmpty()) {
                allDuplicates.add(new Duplicate(original.getId(), ids, DuplicateType.ON_PHONE));
            }
        }
        return allDuplicates;
    }

    /**
     * Method that returns the duplicates of the given Candidate through all the candidate that are of given status
     *
     * @param id             is the id of the original Candidate you compare to
     * @param candidateCheck is the status of the candidates you search the duplicates in
     * @param duplicateType  is the duplicate type you search on
     * @return the duplicates of the duplicate type if given a correct candidate id, else null
     */
    public Duplicate getDuplicatesOnDuplicateType(Long id, CandidateCheck candidateCheck, DuplicateType duplicateType) {
        Candidate original = candidateRepository.findOne(id);
        if (original != null) {
            switch (duplicateType) {
                case ON_NAME:
                    return new Duplicate(original.getId(), candidateRepository.findAllByFirstNameAndLastNameAndCheckCandidateAndIdIsNot(original.getFirstName(), original.getLastName(), candidateCheck, original.getId()).stream().map(Candidate::getId).collect(Collectors.toSet()), DuplicateType.ON_NAME);
                case ON_EMAIL:
                    return new Duplicate(original.getId(), candidateRepository.findAllByEmailAndCheckCandidateAndIdIsNot(original.getEmail(), candidateCheck, original.getId()).stream().map(Candidate::getId).collect(Collectors.toSet()), DuplicateType.ON_EMAIL);
                case ON_PHONE:
                    return new Duplicate(original.getId(), candidateRepository.findAllByPhoneAndCheckCandidateAndIdIsNot(original.getPhone(), candidateCheck, original.getId()).stream().map(Candidate::getId).collect(Collectors.toSet()), DuplicateType.ON_PHONE);
            }
        }
        return null;
    }

    /**
     * Method that returns the individual number of all of the duplicates of the given Candidate through all the candidate that are of given status
     *
     * @param id             is the id of the original Candidate you compare to
     * @param candidateCheck is the status of the candidates you search the duplicates in
     * @return the count of duplicates divided on the type of duplicate
     */
    public CountDuplicate getCountDuplicate(Long id, CandidateCheck candidateCheck) {
        Candidate original = candidateRepository.findOne(id);
        Map<DuplicateType, Long> duplicateCount = new EnumMap<>(DuplicateType.class);
        if (original != null) {
            duplicateCount.put(DuplicateType.ON_NAME, candidateRepository.countByFirstNameAndLastNameAndCheckCandidateAndIdIsNot(original.getFirstName(), original.getLastName(), candidateCheck, original.getId()));
            duplicateCount.put(DuplicateType.ON_EMAIL, candidateRepository.countByEmailAndCheckCandidateAndIdIsNot(original.getEmail(), candidateCheck, original.getId()));
            duplicateCount.put(DuplicateType.ON_PHONE, candidateRepository.countByPhoneAndCheckCandidateAndIdIsNot(original.getPhone(), candidateCheck, original.getId()));
        }
        return new CountDuplicate(id, duplicateCount);
    }

    /**
     * Method that returns the number of duplicates of given type of the given Candidate through all the candidate that are of given status
     *
     * @param id             is the id of the original Candidate you compare to
     * @param candidateCheck is the status of the candidates you search the duplicates in
     * @param duplicateType  is the duplicate type you search
     * @return the count of duplicates of given type
     */
    public long getCountDuplicateOnDuplicateType(Long id, CandidateCheck candidateCheck, DuplicateType duplicateType) {
        Candidate original = candidateRepository.findOne(id);
        if (original != null) {
            switch (duplicateType) {
                case ON_NAME:
                    return candidateRepository.countByFirstNameAndLastNameAndCheckCandidateAndIdIsNot(original.getFirstName(), original.getLastName(), candidateCheck, original.getId());
                case ON_EMAIL:
                    return candidateRepository.countByEmailAndCheckCandidateAndIdIsNot(original.getEmail(), candidateCheck, original.getId());
                case ON_PHONE:
                    return candidateRepository.countByPhoneAndCheckCandidateAndIdIsNot(original.getPhone(), candidateCheck, original.getId());
            }
        }
        return -1;
    }
}
