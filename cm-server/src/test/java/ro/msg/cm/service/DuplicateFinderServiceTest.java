package ro.msg.cm.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ro.msg.cm.model.Candidate;
import ro.msg.cm.pojo.CountDuplicate;
import ro.msg.cm.pojo.Duplicate;
import ro.msg.cm.repository.CandidateRepository;
import ro.msg.cm.types.CandidateCheck;
import ro.msg.cm.types.DuplicateType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;

public class DuplicateFinderServiceTest {

    @InjectMocks
    private DuplicateFinderService duplicateFinderService;

    @Mock
    private CandidateRepository mockCandidateRepository;

    private List<Candidate> candidateList;
    private CandidateCheck validated = CandidateCheck.VALIDATED;
    private CandidateCheck notYetValidated = CandidateCheck.NOT_YET_VALIDATED;

    private void setUpMockCandidateRepository() {
        when(mockCandidateRepository.findAllByFirstNameAndLastNameAndCheckCandidateAndIdIsNot(Mockito.anyString(), Mockito.anyString(), Mockito.any(CandidateCheck.class), Mockito.anyLong())).thenAnswer(
                answer -> candidateList.stream().filter(x ->
                        x.getFirstName().equals(answer.getArgumentAt(0, String.class)) &&
                                x.getLastName().equals(answer.getArgumentAt(1, String.class)) &&
                                x.getCheckCandidate().equals(answer.getArgumentAt(2, CandidateCheck.class)) &&
                                !x.getId().equals(answer.getArgumentAt(3, Long.class))).
                                collect(Collectors.toSet())
        );

        when(mockCandidateRepository.findAllByEmailAndCheckCandidateAndIdIsNot(Mockito.anyString(), Mockito.any(CandidateCheck.class), Mockito.anyLong())).thenAnswer(
                answer -> candidateList.stream().filter(x ->
                        x.getEmail().equals(answer.getArgumentAt(0, String.class)) &&
                                x.getCheckCandidate().equals(answer.getArgumentAt(1, CandidateCheck.class)) &&
                                !x.getId().equals(answer.getArgumentAt(2, Long.class))).
                                collect(Collectors.toSet())
        );

        when(mockCandidateRepository.findAllByPhoneAndCheckCandidateAndIdIsNot(Mockito.anyString(), Mockito.any(CandidateCheck.class), Mockito.anyLong())).thenAnswer(
                answer -> candidateList.stream().filter(x ->
                        x.getPhone().equals(answer.getArgumentAt(0, String.class)) &&
                                x.getCheckCandidate().equals(answer.getArgumentAt(1, CandidateCheck.class)) &&
                                !x.getId().equals(answer.getArgumentAt(2, Long.class))).
                                collect(Collectors.toSet())
        );

        when(mockCandidateRepository.countByFirstNameAndLastNameAndCheckCandidateAndIdIsNot(Mockito.anyString(), Mockito.anyString(), Mockito.any(CandidateCheck.class), Mockito.anyLong())).thenAnswer(
                answer -> candidateList.stream().filter(x ->
                        x.getFirstName().equals(answer.getArgumentAt(0, String.class)) &&
                                x.getLastName().equals(answer.getArgumentAt(1, String.class)) &&
                                x.getCheckCandidate().equals(answer.getArgumentAt(2, CandidateCheck.class)) &&
                                !x.getId().equals(answer.getArgumentAt(3, Long.class))).
                                count()
        );

        when(mockCandidateRepository.countByEmailAndCheckCandidateAndIdIsNot(Mockito.anyString(), Mockito.any(CandidateCheck.class), Mockito.anyLong())).thenAnswer(
                answer -> candidateList.stream().filter(x ->
                        x.getEmail().equals(answer.getArgumentAt(0, String.class)) &&
                                x.getCheckCandidate().equals(answer.getArgumentAt(1, CandidateCheck.class)) &&
                                !x.getId().equals(answer.getArgumentAt(2, Long.class))).
                                count()
        );

        when(mockCandidateRepository.countByPhoneAndCheckCandidateAndIdIsNot(Mockito.anyString(), Mockito.any(CandidateCheck.class), Mockito.anyLong())).thenAnswer(
                answer -> candidateList.stream().filter(x ->
                        x.getPhone().equals(answer.getArgumentAt(0, String.class)) &&
                                x.getCheckCandidate().equals(answer.getArgumentAt(1, CandidateCheck.class)) &&
                                !x.getId().equals(answer.getArgumentAt(2, Long.class))).
                                count()
        );

        when(mockCandidateRepository.findOne(Mockito.anyLong())).thenAnswer(
                answer -> candidateList.stream().filter(x ->
                        x.getId().equals(answer.getArgumentAt(0, Long.class))).
                                collect(Collectors.toList()).get(0)
        );
    }

    private void setUpCandidateList() {
        candidateList = new ArrayList<>();

        // Valid

        //2 with nothing in common
        candidateList.add(getCandidate(1, "Nicolae", "Simon", "jrangle0@jalbum.net", "9925848027", CandidateCheck.VALIDATED));
        candidateList.add(getCandidate(2, "Horia", "Victoria", "cshallow1@baidu.com,", "1054647890", CandidateCheck.VALIDATED));

        //2 with name in common
        candidateList.add(getCandidate(7, "Elena", "Marin", "phasely2@google.es", "9565314990", CandidateCheck.VALIDATED));
        candidateList.add(getCandidate(8, "Elena", "Marin", "rvillalta3@de.vu", "8161697903", CandidateCheck.VALIDATED));

        //2 with phone in common
        candidateList.add(getCandidate(14, "Ramona", "Veronica", "vosbidston4@privacy.gov.au", "6697627176", CandidateCheck.VALIDATED));
        candidateList.add(getCandidate(15, "Olimpia", "Luca", "ivaines5@deliciousdays.com", "6697627176", CandidateCheck.VALIDATED));

        //2 with email in common
        candidateList.add(getCandidate(21, "Petre", "Marin", "vmizzi6@hugedomains.com", "1679370689", CandidateCheck.VALIDATED));
        candidateList.add(getCandidate(22, "Cornelia", "Iuliu", "vmizzi6@hugedomains.com", "8876908185", CandidateCheck.VALIDATED));

        //2 with name and phone in common
        candidateList.add(getCandidate(28, "Olimpia", "Andrei", "scassely8@scientificamerican.com", "6097644586", CandidateCheck.VALIDATED));
        candidateList.add(getCandidate(29, "Olimpia", "Andrei", "rmerrisson9@gmpg.org", "6097644586", CandidateCheck.VALIDATED));

        //2 with name and email in common
        candidateList.add(getCandidate(35, "Angela", "Narcisa", "cmellishb@phpbb.com", "8047009418", CandidateCheck.VALIDATED));
        candidateList.add(getCandidate(36, "Angela", "Narcisa", "cmellishb@phpbb.com", "7036829152", CandidateCheck.VALIDATED));

        //2 with phone and email in common
        candidateList.add(getCandidate(42, "Costache", "Doina", "dbennied@aol.com", "2712211158", CandidateCheck.VALIDATED));
        candidateList.add(getCandidate(43, "Roxana", "Constantin", "dbennied@aol.com", "2712211158", CandidateCheck.VALIDATED));

        //2 with name and phone and email in common
        candidateList.add(getCandidate(49, "Aurel", "Stelian", "nsutherley1@princeton.edu", "8266709053", CandidateCheck.VALIDATED));
        candidateList.add(getCandidate(50, "Aurel", "Stelian", "nsutherley1@princeton.edu", "8266709053", CandidateCheck.VALIDATED));

        // NotYetValid

        //2 with nothing in common
        candidateList.add(getCandidate(10, "Nicolae", "Simon", "jrangle0@jalbum.net", "9925848027", CandidateCheck.NOT_YET_VALIDATED));
        candidateList.add(getCandidate(20, "Horia", "Victoria", "cshallow1@baidu.com,", "1054647890", CandidateCheck.NOT_YET_VALIDATED));

        //2 with name in common
        candidateList.add(getCandidate(70, "Elena", "Marin", "phasely2@google.es", "9565314990", CandidateCheck.NOT_YET_VALIDATED));
        candidateList.add(getCandidate(80, "Elena", "Marin", "rvillalta3@de.vu", "8161697903", CandidateCheck.NOT_YET_VALIDATED));

        //2 with phone in common
        candidateList.add(getCandidate(140, "Ramona", "Veronica", "vosbidston4@privacy.gov.au", "6697627176", CandidateCheck.NOT_YET_VALIDATED));
        candidateList.add(getCandidate(150, "Olimpia", "Luca", "ivaines5@deliciousdays.com", "6697627176", CandidateCheck.NOT_YET_VALIDATED));

        //2 with email in common
        candidateList.add(getCandidate(210, "Petre", "Marin", "vmizzi6@hugedomains.com", "1679370689", CandidateCheck.NOT_YET_VALIDATED));
        candidateList.add(getCandidate(220, "Cornelia", "Iuliu", "vmizzi6@hugedomains.com", "8876908185", CandidateCheck.NOT_YET_VALIDATED));

        //2 with name and phone in common
        candidateList.add(getCandidate(280, "Olimpia", "Andrei", "scassely8@scientificamerican.com", "6097644586", CandidateCheck.NOT_YET_VALIDATED));
        candidateList.add(getCandidate(290, "Olimpia", "Andrei", "rmerrisson9@gmpg.org", "6097644586", CandidateCheck.NOT_YET_VALIDATED));

        //2 with name and email in common
        candidateList.add(getCandidate(350, "Angela", "Narcisa", "cmellishb@phpbb.com", "8047009418", CandidateCheck.NOT_YET_VALIDATED));
        candidateList.add(getCandidate(360, "Angela", "Narcisa", "cmellishb@phpbb.com", "7036829152", CandidateCheck.NOT_YET_VALIDATED));

        //2 with phone and email in common
        candidateList.add(getCandidate(420, "Costache", "Doina", "dbennied@aol.com", "2712211158", CandidateCheck.NOT_YET_VALIDATED));
        candidateList.add(getCandidate(430, "Roxana", "Constantin", "dbennied@aol.com", "2712211158", CandidateCheck.NOT_YET_VALIDATED));

        //2 with name and phone and email in common
        candidateList.add(getCandidate(490, "Aurel", "Stelian", "nsutherley1@princeton.edu", "8266709053", CandidateCheck.NOT_YET_VALIDATED));
        candidateList.add(getCandidate(500, "Aurel", "Stelian", "nsutherley1@princeton.edu", "8266709053", CandidateCheck.NOT_YET_VALIDATED));

    }

    private Candidate getCandidate(long id, String firstName, String lastName, String email, String phone, CandidateCheck candidateCheck) {
        Candidate candidate = new Candidate();
        candidate.setId(id);
        candidate.setFirstName(firstName);
        candidate.setLastName(lastName);
        candidate.setEmail(email);
        candidate.setPhone(phone);
        candidate.setCheckCandidate(candidateCheck);
        return candidate;
    }

    private void assertCountOfDuplication(long expected, long actual) {
        Assert.assertEquals("Assert the count of duplications: ", expected, actual);
    }

    private void assertNrOfDuplication(long expected, long actual) {
        Assert.assertEquals("Assert the nr of duplications: ", expected, actual);
    }

    private void assertIdOfDuplication(Object expected, Object actual) {
        Assert.assertEquals("Assert the nr of duplications: ", expected, actual);
    }

    private void assertTypeOfDuplication(DuplicateType expected, DuplicateType actual) {
        Assert.assertEquals("Assert the nr of duplications: ", expected, actual);
    }

    private void assertNotEqualTypeOfDuplication(boolean condition) {
        Assert.assertTrue("Assert the type of duplications: ", condition);
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setUpMockCandidateRepository();
        setUpCandidateList();
    }

    //Find Tests

    //CandidateCheck is Validated

    @Test
    public void findDuplicatesWithNothing1CandidateCheckValidated() {
        List<Duplicate> duplicates = duplicateFinderService.getDuplicates(1L, validated);
        assertNrOfDuplication(0, duplicates.size());
    }

    @Test
    public void findDuplicatesWithNothing2CandidateCheckValidated() {
        List<Duplicate> duplicates = duplicateFinderService.getDuplicates(2L, validated);
        assertNrOfDuplication(0, duplicates.size());
    }

    @Test
    public void findDuplicatesWithName1CandidateCheckValidated() {
        List<Duplicate> duplicates = duplicateFinderService.getDuplicates(7L, validated);
        assertNrOfDuplication(1, duplicates.size());
        assertTypeOfDuplication(DuplicateType.ON_NAME, duplicates.get(0).getDuplicateType());
        assertIdOfDuplication(8L, duplicates.get(0).getIds().toArray()[0]);
    }

    @Test
    public void findDuplicatesWithName2CandidateCheckValidated() {
        List<Duplicate> duplicates = duplicateFinderService.getDuplicates(8L, validated);
        assertNrOfDuplication(1, duplicates.size());
        assertTypeOfDuplication(DuplicateType.ON_NAME, duplicates.get(0).getDuplicateType());
        assertIdOfDuplication(7L, duplicates.get(0).getIds().toArray()[0]);
    }

    @Test
    public void findDuplicatesWithPhone1CandidateCheckValidated() {
        List<Duplicate> duplicates = duplicateFinderService.getDuplicates(14L, validated);
        assertNrOfDuplication(1, duplicates.size());
        assertTypeOfDuplication(DuplicateType.ON_PHONE, duplicates.get(0).getDuplicateType());
        assertIdOfDuplication(15L, duplicates.get(0).getIds().toArray()[0]);
    }

    @Test
    public void findDuplicatesWithPhone2CandidateCheckValidated() {
        List<Duplicate> duplicates = duplicateFinderService.getDuplicates(15L, validated);
        assertNrOfDuplication(1, duplicates.size());
        assertTypeOfDuplication(DuplicateType.ON_PHONE, duplicates.get(0).getDuplicateType());
        assertIdOfDuplication(14L, duplicates.get(0).getIds().toArray()[0]);
    }

    @Test
    public void findDuplicatesWithEmail1CandidateCheckValidated() {
        List<Duplicate> duplicates = duplicateFinderService.getDuplicates(21L, validated);
        assertNrOfDuplication(1, duplicates.size());
        assertTypeOfDuplication(DuplicateType.ON_EMAIL, duplicates.get(0).getDuplicateType());
        assertIdOfDuplication(22L, duplicates.get(0).getIds().toArray()[0]);
    }

    @Test
    public void findDuplicatesWithEmail2CandidateCheckValidated() {
        List<Duplicate> duplicates = duplicateFinderService.getDuplicates(22L, validated);
        assertNrOfDuplication(1, duplicates.size());
        assertTypeOfDuplication(DuplicateType.ON_EMAIL, duplicates.get(0).getDuplicateType());
        assertIdOfDuplication(21L, duplicates.get(0).getIds().toArray()[0]);
    }

    @Test
    public void findDuplicatesWithNameAndPhone1CandidateCheckValidated() {
        List<Duplicate> duplicates = duplicateFinderService.getDuplicates(28L, validated);
        assertNrOfDuplication(2, duplicates.size());
        for (Duplicate duplicate : duplicates) {
            assertNotEqualTypeOfDuplication(duplicate.getDuplicateType() != DuplicateType.ON_EMAIL);
        }
        assertIdOfDuplication(29L, duplicates.get(0).getIds().toArray()[0]);
        assertIdOfDuplication(29L, duplicates.get(1).getIds().toArray()[0]);
    }

    @Test
    public void findDuplicatesWithNameAndPhone2CandidateCheckValidated() {
        List<Duplicate> duplicates = duplicateFinderService.getDuplicates(29L, validated);
        assertNrOfDuplication(2, duplicates.size());
        for (Duplicate duplicate : duplicates) {
            assertNotEqualTypeOfDuplication(duplicate.getDuplicateType() != DuplicateType.ON_EMAIL);
        }
        assertIdOfDuplication(28L, duplicates.get(0).getIds().toArray()[0]);
        assertIdOfDuplication(28L, duplicates.get(1).getIds().toArray()[0]);
    }

    @Test
    public void findDuplicatesWithNameAndEmail1CandidateCheckValidated() {
        List<Duplicate> duplicates = duplicateFinderService.getDuplicates(35L, validated);
        assertNrOfDuplication(2, duplicates.size());
        for (Duplicate duplicate : duplicates) {
            assertNotEqualTypeOfDuplication(duplicate.getDuplicateType() != DuplicateType.ON_PHONE);
        }
        assertIdOfDuplication(36L, duplicates.get(0).getIds().toArray()[0]);
        assertIdOfDuplication(36L, duplicates.get(1).getIds().toArray()[0]);
    }

    @Test
    public void findDuplicatesWithNameAndEmail2CandidateCheckValidated() {
        List<Duplicate> duplicates = duplicateFinderService.getDuplicates(36L, validated);
        assertNrOfDuplication(2, duplicates.size());
        for (Duplicate duplicate : duplicates) {
            assertNotEqualTypeOfDuplication(duplicate.getDuplicateType() != DuplicateType.ON_PHONE);
        }
        assertIdOfDuplication(35L, duplicates.get(0).getIds().toArray()[0]);
        assertIdOfDuplication(35L, duplicates.get(1).getIds().toArray()[0]);
    }

    @Test
    public void findDuplicatesWithPhoneAndEmail1CandidateCheckValidated() {
        List<Duplicate> duplicates = duplicateFinderService.getDuplicates(42L, validated);
        assertNrOfDuplication(2, duplicates.size());
        for (Duplicate duplicate : duplicates) {
            assertNotEqualTypeOfDuplication(duplicate.getDuplicateType() != DuplicateType.ON_NAME);
        }
        assertIdOfDuplication(43L, duplicates.get(0).getIds().toArray()[0]);
        assertIdOfDuplication(43L, duplicates.get(1).getIds().toArray()[0]);
    }

    @Test
    public void findDuplicatesWithPhoneAndEmail2CandidateCheckValidated() {
        List<Duplicate> duplicates = duplicateFinderService.getDuplicates(43L, validated);
        assertNrOfDuplication(2, duplicates.size());
        for (Duplicate duplicate : duplicates) {
            assertNotEqualTypeOfDuplication(duplicate.getDuplicateType() != DuplicateType.ON_NAME);
        }
        assertIdOfDuplication(42L, duplicates.get(0).getIds().toArray()[0]);
        assertIdOfDuplication(42L, duplicates.get(1).getIds().toArray()[0]);
    }

    @Test
    public void findDuplicatesWithNameAndPhoneAndEmail1CandidateCheckValidated() {
        List<Duplicate> duplicates = duplicateFinderService.getDuplicates(49L, validated);
        assertNrOfDuplication(3, duplicates.size());
        assertIdOfDuplication(50L, duplicates.get(0).getIds().toArray()[0]);
        assertIdOfDuplication(50L, duplicates.get(1).getIds().toArray()[0]);
        assertIdOfDuplication(50L, duplicates.get(2).getIds().toArray()[0]);
    }

    @Test
    public void findDuplicatesWithNameAndPhoneAndEmail2CandidateCheckValidated() {
        List<Duplicate> duplicates = duplicateFinderService.getDuplicates(50L, validated);
        assertNrOfDuplication(3, duplicates.size());
        assertIdOfDuplication(49L, duplicates.get(0).getIds().toArray()[0]);
        assertIdOfDuplication(49L, duplicates.get(1).getIds().toArray()[0]);
        assertIdOfDuplication(49L, duplicates.get(2).getIds().toArray()[0]);
    }

    //CandidateCheck is Not Yet Validated

    @Test
    public void findDuplicatesWithNothing1CandidateCheckNotYetValidated() {
        List<Duplicate> duplicates = duplicateFinderService.getDuplicates(10L, notYetValidated);
        assertNrOfDuplication(0, duplicates.size());
    }

    @Test
    public void findDuplicatesWithNothing2CandidateCheckNotYetValidated() {
        List<Duplicate> duplicates = duplicateFinderService.getDuplicates(20L, notYetValidated);
        assertNrOfDuplication(0, duplicates.size());
    }

    @Test
    public void findDuplicatesWithName1CandidateCheckNotYetValidated() {
        List<Duplicate> duplicates = duplicateFinderService.getDuplicates(70L, notYetValidated);
        assertNrOfDuplication(1, duplicates.size());
        assertTypeOfDuplication(DuplicateType.ON_NAME, duplicates.get(0).getDuplicateType());
        assertIdOfDuplication(80L, duplicates.get(0).getIds().toArray()[0]);
    }

    @Test
    public void findDuplicatesWithName2CandidateCheckNotYetValidated() {
        List<Duplicate> duplicates = duplicateFinderService.getDuplicates(80L, notYetValidated);
        assertNrOfDuplication(1, duplicates.size());
        assertTypeOfDuplication(DuplicateType.ON_NAME, duplicates.get(0).getDuplicateType());
        assertIdOfDuplication(70L, duplicates.get(0).getIds().toArray()[0]);
    }

    @Test
    public void findDuplicatesWithPhone1CandidateCheckNotYetValidated() {
        List<Duplicate> duplicates = duplicateFinderService.getDuplicates(140L, notYetValidated);
        assertNrOfDuplication(1, duplicates.size());
        assertTypeOfDuplication(DuplicateType.ON_PHONE, duplicates.get(0).getDuplicateType());
        assertIdOfDuplication(150L, duplicates.get(0).getIds().toArray()[0]);
    }

    @Test
    public void findDuplicatesWithPhone2CandidateCheckNotYetValidated() {
        List<Duplicate> duplicates = duplicateFinderService.getDuplicates(150L, notYetValidated);
        assertNrOfDuplication(1, duplicates.size());
        assertTypeOfDuplication(DuplicateType.ON_PHONE, duplicates.get(0).getDuplicateType());
        assertIdOfDuplication(140L, duplicates.get(0).getIds().toArray()[0]);
    }

    @Test
    public void findDuplicatesWithEmail1CandidateCheckNotYetValidated() {
        List<Duplicate> duplicates = duplicateFinderService.getDuplicates(210L, notYetValidated);
        assertNrOfDuplication(1, duplicates.size());
        assertTypeOfDuplication(DuplicateType.ON_EMAIL, duplicates.get(0).getDuplicateType());
        assertIdOfDuplication(220L, duplicates.get(0).getIds().toArray()[0]);
    }

    @Test
    public void findDuplicatesWithEmail2CandidateCheckNotYetValidated() {
        List<Duplicate> duplicates = duplicateFinderService.getDuplicates(220L, notYetValidated);
        assertNrOfDuplication(1, duplicates.size());
        assertTypeOfDuplication(DuplicateType.ON_EMAIL, duplicates.get(0).getDuplicateType());
        assertIdOfDuplication(210L, duplicates.get(0).getIds().toArray()[0]);
    }

    @Test
    public void findDuplicatesWithNameAndPhone1CandidateCheckNotYetValidated() {
        List<Duplicate> duplicates = duplicateFinderService.getDuplicates(280L, notYetValidated);
        assertNrOfDuplication(2, duplicates.size());
        for (Duplicate duplicate : duplicates) {
            assertNotEqualTypeOfDuplication(duplicate.getDuplicateType() != DuplicateType.ON_EMAIL);
        }
        assertIdOfDuplication(290L, duplicates.get(0).getIds().toArray()[0]);
        assertIdOfDuplication(290L, duplicates.get(1).getIds().toArray()[0]);
    }

    @Test
    public void findDuplicatesWithNameAndPhone2CandidateCheckNotYetValidated() {
        List<Duplicate> duplicates = duplicateFinderService.getDuplicates(290L, notYetValidated);
        assertNrOfDuplication(2, duplicates.size());
        for (Duplicate duplicate : duplicates) {
            assertNotEqualTypeOfDuplication(duplicate.getDuplicateType() != DuplicateType.ON_EMAIL);
        }
        assertIdOfDuplication(280L, duplicates.get(0).getIds().toArray()[0]);
        assertIdOfDuplication(280L, duplicates.get(1).getIds().toArray()[0]);
    }

    @Test
    public void findDuplicatesWithNameAndEmail1CandidateCheckNotYetValidated() {
        List<Duplicate> duplicates = duplicateFinderService.getDuplicates(350L, notYetValidated);
        assertNrOfDuplication(2, duplicates.size());
        for (Duplicate duplicate : duplicates) {
            assertNotEqualTypeOfDuplication(duplicate.getDuplicateType() != DuplicateType.ON_PHONE);
        }
        assertIdOfDuplication(360L, duplicates.get(0).getIds().toArray()[0]);
        assertIdOfDuplication(360L, duplicates.get(1).getIds().toArray()[0]);
    }

    @Test
    public void findDuplicatesWithNameAndEmail2CandidateCheckNotYetValidated() {
        List<Duplicate> duplicates = duplicateFinderService.getDuplicates(360L, notYetValidated);
        assertNrOfDuplication(2, duplicates.size());
        for (Duplicate duplicate : duplicates) {
            assertNotEqualTypeOfDuplication(duplicate.getDuplicateType() != DuplicateType.ON_PHONE);
        }
        assertIdOfDuplication(350L, duplicates.get(0).getIds().toArray()[0]);
        assertIdOfDuplication(350L, duplicates.get(1).getIds().toArray()[0]);
    }

    @Test
    public void findDuplicatesWithPhoneAndEmail1CandidateCheckNotYetValidated() {
        List<Duplicate> duplicates = duplicateFinderService.getDuplicates(420L, notYetValidated);
        assertNrOfDuplication(2, duplicates.size());
        for (Duplicate duplicate : duplicates) {
            assertNotEqualTypeOfDuplication(duplicate.getDuplicateType() != DuplicateType.ON_NAME);
        }
        assertIdOfDuplication(430L, duplicates.get(0).getIds().toArray()[0]);
        assertIdOfDuplication(430L, duplicates.get(1).getIds().toArray()[0]);
    }

    @Test
    public void findDuplicatesWithPhoneAndEmail2CandidateCheckNotYetValidated() {
        List<Duplicate> duplicates = duplicateFinderService.getDuplicates(430L, notYetValidated);
        assertNrOfDuplication(2, duplicates.size());
        for (Duplicate duplicate : duplicates) {
            assertNotEqualTypeOfDuplication(duplicate.getDuplicateType() != DuplicateType.ON_NAME);
        }
        assertIdOfDuplication(420L, duplicates.get(0).getIds().toArray()[0]);
        assertIdOfDuplication(420L, duplicates.get(1).getIds().toArray()[0]);
    }

    @Test
    public void findDuplicatesWithNameAndPhoneAndEmail1CandidateCheckNotYetValidated() {
        List<Duplicate> duplicates = duplicateFinderService.getDuplicates(490L, notYetValidated);
        assertNrOfDuplication(3, duplicates.size());
        assertIdOfDuplication(500L, duplicates.get(0).getIds().toArray()[0]);
        assertIdOfDuplication(500L, duplicates.get(1).getIds().toArray()[0]);
        assertIdOfDuplication(500L, duplicates.get(2).getIds().toArray()[0]);
    }

    @Test
    public void findDuplicatesWithNameAndPhoneAndEmail2CandidateCheckNotYetValidated() {
        List<Duplicate> duplicates = duplicateFinderService.getDuplicates(500L, notYetValidated);
        assertNrOfDuplication(3, duplicates.size());
        assertIdOfDuplication(490L, duplicates.get(0).getIds().toArray()[0]);
        assertIdOfDuplication(490L, duplicates.get(1).getIds().toArray()[0]);
        assertIdOfDuplication(490L, duplicates.get(2).getIds().toArray()[0]);
    }

    //Count Tests

    //CandidateCheck is Validated

    @Test
    public void countDuplicatesWithNothing1CandidateCheckValidated() {
        CountDuplicate countDuplicate = duplicateFinderService.getCountDuplicate(1L, validated);
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_NAME));
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_EMAIL));
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_PHONE));
    }

    @Test
    public void countDuplicatesWithNothing2CandidateCheckValidated() {
        CountDuplicate countDuplicate = duplicateFinderService.getCountDuplicate(2L, validated);
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_NAME));
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_EMAIL));
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_PHONE));
    }

    @Test
    public void countDuplicatesWithName1CandidateCheckValidated() {
        CountDuplicate countDuplicate = duplicateFinderService.getCountDuplicate(7L, validated);
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_NAME));
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_EMAIL));
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_PHONE));
    }

    @Test
    public void countDuplicatesWithName2CandidateCheckValidated() {
        CountDuplicate countDuplicate = duplicateFinderService.getCountDuplicate(8L, validated);
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_NAME));
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_EMAIL));
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_PHONE));
    }

    @Test
    public void countDuplicatesWithPhone1CandidateCheckValidated() {
        CountDuplicate countDuplicate = duplicateFinderService.getCountDuplicate(14L, validated);
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_NAME));
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_EMAIL));
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_PHONE));
    }

    @Test
    public void countDuplicatesWithPhone2CandidateCheckValidated() {
        CountDuplicate countDuplicate = duplicateFinderService.getCountDuplicate(15L, validated);
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_NAME));
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_EMAIL));
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_PHONE));
    }

    @Test
    public void countDuplicatesWithEmail1CandidateCheckValidated() {
        CountDuplicate countDuplicate = duplicateFinderService.getCountDuplicate(21L, validated);
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_NAME));
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_EMAIL));
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_PHONE));
    }

    @Test
    public void countDuplicatesWithEmail2CandidateCheckValidated() {
        CountDuplicate countDuplicate = duplicateFinderService.getCountDuplicate(22L, validated);
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_NAME));
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_EMAIL));
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_PHONE));
    }

    @Test
    public void countDuplicatesWithNameAndPhone1CandidateCheckValidated() {
        CountDuplicate countDuplicate = duplicateFinderService.getCountDuplicate(28L, validated);
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_NAME));
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_EMAIL));
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_PHONE));
    }

    @Test
    public void countDuplicatesWithNameAndPhone2CandidateCheckValidated() {
        CountDuplicate countDuplicate = duplicateFinderService.getCountDuplicate(29L, validated);
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_NAME));
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_EMAIL));
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_PHONE));
    }

    @Test
    public void countDuplicatesWithNameAndEmail1CandidateCheckValidated() {
        CountDuplicate countDuplicate = duplicateFinderService.getCountDuplicate(35L, validated);
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_NAME));
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_EMAIL));
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_PHONE));

    }

    @Test
    public void countDuplicatesWithNameAndEmail2CandidateCheckValidated() {
        CountDuplicate countDuplicate = duplicateFinderService.getCountDuplicate(36L, validated);
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_NAME));
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_EMAIL));
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_PHONE));
    }

    @Test
    public void countDuplicatesWithPhoneAndEmail1CandidateCheckValidated() {
        CountDuplicate countDuplicate = duplicateFinderService.getCountDuplicate(42L, validated);
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_NAME));
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_EMAIL));
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_PHONE));
    }

    @Test
    public void countDuplicatesWithPhoneAndEmail2CandidateCheckValidated() {
        CountDuplicate countDuplicate = duplicateFinderService.getCountDuplicate(43L, validated);
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_NAME));
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_EMAIL));
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_PHONE));
    }

    @Test
    public void countDuplicatesWithNameAndPhoneAndEmail1CandidateCheckValidated() {
        CountDuplicate countDuplicate = duplicateFinderService.getCountDuplicate(49L, validated);
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_NAME));
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_EMAIL));
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_PHONE));
    }

    @Test
    public void countDuplicatesWithNameAndPhoneAndEmail2CandidateCheckValidated() {
        CountDuplicate countDuplicate = duplicateFinderService.getCountDuplicate(50L, validated);
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_NAME));
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_EMAIL));
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_PHONE));

    }

    //CandidateCheck is Not Yet Validated

    @Test
    public void countDuplicatesWithNothing1CandidateCheckNotYetValidated() {
        CountDuplicate countDuplicate = duplicateFinderService.getCountDuplicate(10L, notYetValidated);
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_NAME));
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_EMAIL));
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_PHONE));
    }

    @Test
    public void countDuplicatesWithNothing2CandidateCheckNotYetValidated() {
        CountDuplicate countDuplicate = duplicateFinderService.getCountDuplicate(20L, notYetValidated);
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_NAME));
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_EMAIL));
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_PHONE));
    }

    @Test
    public void countDuplicatesWithName1CandidateCheckNotYetValidated() {
        CountDuplicate countDuplicate = duplicateFinderService.getCountDuplicate(70L, notYetValidated);
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_NAME));
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_EMAIL));
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_PHONE));
    }

    @Test
    public void countDuplicatesWithName2CandidateCheckNotYetValidated() {
        CountDuplicate countDuplicate = duplicateFinderService.getCountDuplicate(80L, notYetValidated);
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_NAME));
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_EMAIL));
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_PHONE));
    }

    @Test
    public void countDuplicatesWithPhone1CandidateCheckNotYetValidated() {
        CountDuplicate countDuplicate = duplicateFinderService.getCountDuplicate(140L, notYetValidated);
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_NAME));
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_EMAIL));
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_PHONE));
    }

    @Test
    public void countDuplicatesWithPhone2CandidateCheckNotYetValidated() {
        CountDuplicate countDuplicate = duplicateFinderService.getCountDuplicate(150L, notYetValidated);
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_NAME));
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_EMAIL));
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_PHONE));
    }

    @Test
    public void countDuplicatesWithEmail1CandidateCheckNotYetValidated() {
        CountDuplicate countDuplicate = duplicateFinderService.getCountDuplicate(210L, notYetValidated);
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_NAME));
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_EMAIL));
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_PHONE));
    }

    @Test
    public void countDuplicatesWithEmail2CandidateCheckNotYetValidated() {
        CountDuplicate countDuplicate = duplicateFinderService.getCountDuplicate(220L, notYetValidated);
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_NAME));
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_EMAIL));
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_PHONE));
    }

    @Test
    public void countDuplicatesWithNameAndPhone1CandidateCheckNotYetValidated() {
        CountDuplicate countDuplicate = duplicateFinderService.getCountDuplicate(280L, notYetValidated);
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_NAME));
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_EMAIL));
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_PHONE));
    }

    @Test
    public void countDuplicatesWithNameAndPhone2CandidateCheckNotYetValidated() {
        CountDuplicate countDuplicate = duplicateFinderService.getCountDuplicate(290L, notYetValidated);
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_NAME));
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_EMAIL));
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_PHONE));
    }

    @Test
    public void countDuplicatesWithNameAndEmail1CandidateCheckNotYetValidated() {
        CountDuplicate countDuplicate = duplicateFinderService.getCountDuplicate(350L, notYetValidated);
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_NAME));
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_EMAIL));
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_PHONE));

    }

    @Test
    public void countDuplicatesWithNameAndEmail2CandidateCheckNotYetValidated() {
        CountDuplicate countDuplicate = duplicateFinderService.getCountDuplicate(360L, notYetValidated);
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_NAME));
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_EMAIL));
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_PHONE));
    }

    @Test
    public void countDuplicatesWithPhoneAndEmail1CandidateCheckNotYetValidated() {
        CountDuplicate countDuplicate = duplicateFinderService.getCountDuplicate(420L, notYetValidated);
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_NAME));
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_EMAIL));
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_PHONE));
    }

    @Test
    public void countDuplicatesWithPhoneAndEmail2CandidateCheckNotYetValidated() {
        CountDuplicate countDuplicate = duplicateFinderService.getCountDuplicate(430L, notYetValidated);
        assertCountOfDuplication(0, countDuplicate.getDuplicateCount().get(DuplicateType.ON_NAME));
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_EMAIL));
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_PHONE));
    }

    @Test
    public void countDuplicatesWithNameAndPhoneAndEmail1CandidateCheckNotYetValidated() {
        CountDuplicate countDuplicate = duplicateFinderService.getCountDuplicate(490L, notYetValidated);
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_NAME));
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_EMAIL));
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_PHONE));
    }

    @Test
    public void countDuplicatesWithNameAndPhoneAndEmail2CandidateCheckNotYetValidated() {
        CountDuplicate countDuplicate = duplicateFinderService.getCountDuplicate(500L, notYetValidated);
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_NAME));
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_EMAIL));
        assertCountOfDuplication(1, countDuplicate.getDuplicateCount().get(DuplicateType.ON_PHONE));

    }
}