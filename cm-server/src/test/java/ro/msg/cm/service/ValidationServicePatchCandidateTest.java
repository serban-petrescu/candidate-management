package ro.msg.cm.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import ro.msg.cm.exception.CandidateNotFoundException;
import ro.msg.cm.exception.PatchCandidateInvalidKeyException;
import ro.msg.cm.exception.PatchCandidateInvalidValueException;
import ro.msg.cm.model.*;
import ro.msg.cm.repository.CandidateRepository;
import ro.msg.cm.types.CandidateCheck;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationServicePatchCandidateTest {

    @Mock
    private CandidateRepository mockCandidateRepository;

    @InjectMocks
    private ValidationService validationService;

    private List<Candidate> candidateList;

    private void setUpMockCandidateRepository() {
        Mockito.when(mockCandidateRepository.findOne(Mockito.anyLong())).thenAnswer(
                (Answer<Candidate>) answer ->
                        candidateList.stream().
                                filter(x -> x.getId().equals(answer.getArgumentAt(0, Long.class))).
                                findFirst().orElseThrow(CandidateNotFoundException::new)
        );
        
        Mockito.when(mockCandidateRepository.findByIdAndCheckCandidate(Mockito.anyLong(), Mockito.any(CandidateCheck.class))).thenAnswer(
                (Answer<Candidate>) answer ->
                        candidateList.stream().
                                filter(x -> x.getId().equals(answer.getArgumentAt(0, Long.class)) && x.getCheckCandidate().equals(answer.getArgumentAt(1, CandidateCheck.class))).
                                findFirst().orElseThrow(CandidateNotFoundException::new)
        );

        Mockito.when(mockCandidateRepository.save(Mockito.any(Candidate.class))).thenAnswer(
                (Answer<Candidate>) answer -> answer.getArgumentAt(0, Candidate.class)
        );
    }

    private void setUpCandidateList() {
        candidateList = new ArrayList<>();
        Candidate candidate = new Candidate();
        candidate.setId(1L);
        candidate.setFirstName("Olimpia");
        candidate.setLastName("Andrei");
        candidate.setPhone("6097644586");
        candidate.setEmail("rmerrisson9@gmpg.org");
        candidate.setEducation(new Education("HIGH_SCHOOL", "Informal School", "Mathematics-Informatics", 4));
        candidate.setEducationStatus("senior");
        candidate.setOriginalStudyYear(2);
        candidate.setEvent("Event");
        ArrayList<CandidateSkills> candidateSkills = new ArrayList<>();
        candidateSkills.add(new CandidateSkills(null, new Tag("description", "tagType"), "5.8", "certified"));
        candidate.setCandidateSkillsList(candidateSkills);
        ArrayList<CandidateNotes> candidateNotes = new ArrayList<>();
        candidateNotes.add(new CandidateNotes(null, "status", "note", LocalDate.parse("2000-03-01")));
        candidate.setCandidateNotesList(candidateNotes);
        candidate.setDateOfAdding(LocalDate.parse("1996-02-18"));
        candidate.setCheckCandidate(CandidateCheck.NOT_YET_VALIDATED);

        candidateList.add(candidate);

    }

    private Candidate getExpected() {
        Candidate candidate = new Candidate();
        candidate.setId(2L);
        candidate.setFirstName("FirstName");
        candidate.setLastName("LastName");
        candidate.setPhone("9876543210");
        candidate.setEmail("user@domain.com");
        candidate.setEducation(new Education("type", "prov", "desc", 1));
        candidate.setEducationStatus("status");
        candidate.setOriginalStudyYear(0);
        candidate.setEvent("newEvent");
        ArrayList<CandidateSkills> candidateSkills = new ArrayList<>();
        candidateSkills.add(new CandidateSkills(null, new Tag("newDesc", "newType"), "newRating", "newCertified"));
        candidate.setCandidateSkillsList(candidateSkills);
        ArrayList<CandidateNotes> candidateNotes = new ArrayList<>();
        candidateNotes.add(new CandidateNotes(null, "newStatus", "newNote", LocalDate.parse("2005-04-12")));
        candidate.setCandidateNotesList(candidateNotes);
        candidate.setDateOfAdding(LocalDate.parse("2010-10-17"));
        candidate.setCheckCandidate(CandidateCheck.VALIDATED);
        return candidate;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setUpMockCandidateRepository();
        setUpCandidateList();
    }

    @Test(expected = CandidateNotFoundException.class)
    public void patchCandidateOfNotExistingCandidateTest() {
        validationService.patchCandidate(new HashMap<>(), 2L);
    }

    @Test(expected = PatchCandidateInvalidKeyException.class)
    public void patchCandidateOfNotExistingFieldTest() {
        Map<String, Object> map = new HashMap<>();
        map.put("field", null);
        validationService.patchCandidate(map, 1L);
    }

    @Test()
    public void patchCandidateOfNoPatchCandidateTest() {
        Candidate actual = mockCandidateRepository.findOne(1L);
        Candidate expected = validationService.patchCandidate(new HashMap<>(), 1L);
        Assert.assertEquals("Testing with an empty map ", expected, actual);
    }

    @Test
    public void patchCandidateIdTest() {
        Long expectedId = getExpected().getId();
        Map<String, Object> map = new HashMap<>();
        map.put("id", expectedId);
        Candidate actual = validationService.patchCandidate(map, 1L);
        Assert.assertEquals("Patching the id ", expectedId, actual.getId());
    }

    @Test
    public void patchCandidateFirstNameTest() {
        String expectedName = getExpected().getFirstName();
        Map<String, Object> map = new HashMap<>();
        map.put("firstName", expectedName);
        Candidate actual = validationService.patchCandidate(map, 1L);
        Assert.assertEquals("Patching the first name ", expectedName, actual.getFirstName());
    }

    @Test
    public void patchCandidateLastNameTest() {
        String expectedName = getExpected().getLastName();
        Map<String, Object> map = new HashMap<>();
        map.put("lastName", expectedName);
        Candidate actual = validationService.patchCandidate(map, 1L);
        Assert.assertEquals("Patching the last name ", expectedName, actual.getLastName());
    }

    @Test
    public void patchCandidatePhoneTest() {
        String expectedPhone = getExpected().getPhone();
        Map<String, Object> map = new HashMap<>();
        map.put("phone", expectedPhone);
        Candidate actual = validationService.patchCandidate(map, 1L);
        Assert.assertEquals("Patching the phone ", expectedPhone, actual.getPhone());
    }

    @Test(expected = PatchCandidateInvalidValueException.class)
    public void patchCandidatePhoneTooSmallTest() {
        String expectedPhone = "123456789";
        Map<String, Object> map = new HashMap<>();
        map.put("phone", expectedPhone);
        validationService.patchCandidate(map, 1L);
    }

    @Test(expected = PatchCandidateInvalidValueException.class)
    public void patchCandidatePhoneTooBigTest() {
        String expectedPhone = "0123456789123456";
        Map<String, Object> map = new HashMap<>();
        map.put("phone", expectedPhone);
        validationService.patchCandidate(map, 1L);
    }

    @Test(expected = PatchCandidateInvalidValueException.class)
    public void patchCandidatePhoneNotNumbersTest() {
        String expectedPhone = "notNumbers";
        Map<String, Object> map = new HashMap<>();
        map.put("phone", expectedPhone);
        validationService.patchCandidate(map, 1L);
    }

    @Test
    public void patchCandidateEmailTest() {
        String expectedEmail = getExpected().getEmail();
        Map<String, Object> map = new HashMap<>();
        map.put("email", expectedEmail);
        Candidate actual = validationService.patchCandidate(map, 1L);
        Assert.assertEquals("Patching the phone ", expectedEmail, actual.getEmail());
    }

    @Test(expected = PatchCandidateInvalidValueException.class)
    public void patchCandidateEmailInvalidTest() {
        String expectedEmail = "email";
        Map<String, Object> map = new HashMap<>();
        map.put("email", expectedEmail);
        validationService.patchCandidate(map, 1L);
    }

    @Test
    public void patchCandidateEducationTest() {
        Education expectedEducation = getExpected().getEducation();
        Map<String, Object> map = new HashMap<>();
        map.put("education", expectedEducation);
        Candidate actual = validationService.patchCandidate(map, 1L);
        Assert.assertEquals("Patching the education ", expectedEducation, actual.getEducation());
    }

    @Test
    public void patchCandidateEducationStatusTest() {
        String expectedEducationStatus = getExpected().getEducationStatus();
        Map<String, Object> map = new HashMap<>();
        map.put("educationStatus", expectedEducationStatus);
        Candidate actual = validationService.patchCandidate(map, 1L);
        Assert.assertEquals("Patching the education status ", expectedEducationStatus, actual.getEducationStatus());
    }

    @Test
    public void patchCandidateOriginalStudyYearTest() {
        int expectedOriginalStudyYear = getExpected().getOriginalStudyYear();
        Map<String, Object> map = new HashMap<>();
        map.put("originalStudyYear", expectedOriginalStudyYear);
        Candidate actual = validationService.patchCandidate(map, 1L);
        Assert.assertEquals("Patching the original study year ", expectedOriginalStudyYear, actual.getOriginalStudyYear());
    }

    @Test
    public void patchCandidateEventTest() {
        String expectedEvent = getExpected().getEvent();
        Map<String, Object> map = new HashMap<>();
        map.put("event", expectedEvent);
        Candidate actual = validationService.patchCandidate(map, 1L);
        Assert.assertEquals("Patching the event ", expectedEvent, actual.getEvent());
    }

    @Test
    public void patchCandidateCandidateSkillsListTest() {
        List<CandidateSkills> expectedCandidateSkillsList = getExpected().getCandidateSkillsList();
        Map<String, Object> map = new HashMap<>();
        map.put("candidateSkillsList", expectedCandidateSkillsList);
        Candidate actual = validationService.patchCandidate(map, 1L);
        Assert.assertEquals("Patching the candidate skills list ", expectedCandidateSkillsList, actual.getCandidateSkillsList());
    }

    @Test
    public void patchCandidateCandidateNotesListTest() {
        List<CandidateNotes> expectedCandidateNotesList = getExpected().getCandidateNotesList();
        Map<String, Object> map = new HashMap<>();
        map.put("candidateNotesList", expectedCandidateNotesList);
        Candidate actual = validationService.patchCandidate(map, 1L);
        Assert.assertEquals("Patching the candidate notes list ", expectedCandidateNotesList, actual.getCandidateNotesList());
    }

    @Test
    public void patchCandidateDateOfAddingTest() {
        LocalDate expectedDate = getExpected().getDateOfAdding();
        Map<String, Object> map = new HashMap<>();
        map.put("dateOfAdding", expectedDate);
        Candidate actual = validationService.patchCandidate(map, 1L);
        Assert.assertEquals("Patching the date ", expectedDate, actual.getDateOfAdding());
    }

    @Test
    public void patchCandidateCheckCandidateTest() {
        CandidateCheck expectedCheckCandidate = getExpected().getCheckCandidate();
        Map<String, Object> map = new HashMap<>();
        map.put("checkCandidate", expectedCheckCandidate);
        Candidate actual = validationService.patchCandidate(map, 1L);
        Assert.assertEquals("Patching the check candidate ", expectedCheckCandidate, actual.getCheckCandidate());
    }

    @Test
    public void patchCandidateAllTest() {
        Candidate expected = getExpected();
        Map<String, Object> map = new HashMap<>();
        map.put("id", expected.getId());
        map.put("firstName", expected.getFirstName());
        map.put("lastName", expected.getLastName());
        map.put("phone", expected.getPhone());
        map.put("email", expected.getEmail());
        map.put("education", expected.getEducation());
        map.put("educationStatus", expected.getEducationStatus());
        map.put("originalStudyYear", expected.getOriginalStudyYear());
        map.put("event", expected.getEvent());
        map.put("candidateSkillsList", expected.getCandidateSkillsList());
        map.put("candidateNotesList", expected.getCandidateNotesList());
        map.put("dateOfAdding", expected.getDateOfAdding());
        map.put("checkCandidate", expected.getCheckCandidate());
        Candidate actual = validationService.patchCandidate(map, 1L);
        Assert.assertEquals("Testing with a full map ", expected, actual);
    }

    @Test(expected = PatchCandidateInvalidValueException.class)
    public void patchCandidateAllWithWrongValuesTest() {
        Candidate expected = getExpected();
        Map<String, Object> map = new HashMap<>();
        map.put("id", "name");
        map.put("firstName", expected.getFirstName());
        map.put("lastName", expected.getLastName());
        map.put("phone", 0);
        map.put("email", expected.getEmail());
        map.put("education", expected.getEducation());
        map.put("educationStatus", expected.getEducationStatus());
        map.put("originalStudyYear", expected.getOriginalStudyYear());
        map.put("event", expected.getEvent());
        map.put("candidateSkillsList", expected.getCandidateSkillsList());
        map.put("candidateNotesList", expected.getCandidateNotesList());
        map.put("dateOfAdding", 0);
        map.put("checkCandidate", expected.getCheckCandidate());
        Candidate actual = validationService.patchCandidate(map, 1L);
        Assert.assertEquals("Testing with a full map ", expected, actual);
    }

    @Test
    public void patchCandidateAllWithWhiteSpacesTest() {
        Candidate expected = getExpected();
        Map<String, Object> map = new HashMap<>();
        map.put("id", expected.getId());
        map.put("first\tName", expected.getFirstName());
        map.put("last   Name", expected.getLastName());
        map.put("ph one", expected.getPhone());
        map.put("e m ail", expected.getEmail());
        map.put("education", expected.getEducation());
        map.put("ed u cation\nStatus", expected.getEducationStatus());
        map.put("original " +
                "Study Year", expected.getOriginalStudyYear());
        map.put("eve nt", expected.getEvent());
        map.put("candidate Skills List", expected.getCandidateSkillsList());
        map.put("can    d id    ateNotes  List", expected.getCandidateNotesList());
        map.put("date Of  Adding", expected.getDateOfAdding());
        map.put("check Can        did   ate", expected.getCheckCandidate());
        Candidate actual = validationService.patchCandidate(map, 1L);
        Assert.assertEquals("Testing with a full map ", expected, actual);
    }

    @Test
    public void patchCandidateAllWithLowerCaseKeyTest() {
        Candidate expected = getExpected();
        Map<String, Object> map = new HashMap<>();
        map.put("id", expected.getId());
        map.put("firstname", expected.getFirstName());
        map.put("lastname", expected.getLastName());
        map.put("phone", expected.getPhone());
        map.put("email", expected.getEmail());
        map.put("education", expected.getEducation());
        map.put("educationstatus", expected.getEducationStatus());
        map.put("originalstudyyear", expected.getOriginalStudyYear());
        map.put("event", expected.getEvent());
        map.put("candidateskillslist", expected.getCandidateSkillsList());
        map.put("candidatenoteslist", expected.getCandidateNotesList());
        map.put("dateofadding", expected.getDateOfAdding());
        map.put("checkcandidate", expected.getCheckCandidate());
        Candidate actual = validationService.patchCandidate(map, 1L);
        Assert.assertEquals("Testing with a full map with lower case keys ", expected, actual);
    }

    @Test
    public void patchCandidateAllToNullTest() {
        Candidate expected = new Candidate();
        expected.setDateOfAdding(null);
        expected.setCheckCandidate(null);
        Map<String, Object> map = new HashMap<>();
        map.put("id", expected.getId());
        map.put("firstName", expected.getFirstName());
        map.put("lastName", expected.getLastName());
        map.put("phone", expected.getPhone());
        map.put("email", expected.getEmail());
        map.put("education", expected.getEducation());
        map.put("educationStatus", expected.getEducationStatus());
        map.put("originalStudyYear", expected.getOriginalStudyYear());
        map.put("event", expected.getEvent());
        map.put("candidateSkillsList", expected.getCandidateSkillsList());
        map.put("candidateNotesList", expected.getCandidateNotesList());
        map.put("dateOfAdding", expected.getDateOfAdding());
        map.put("checkCandidate", expected.getCheckCandidate());
        Candidate actual = validationService.patchCandidate(map, 1L);
        Assert.assertEquals("Testing with a everything set to null ", expected, actual);
    }

    @Test
    public void patchCandidateNoIdTest() {
        Candidate unexpected = getExpected();
        Map<String, Object> map = new HashMap<>();
        map.put("firstName", unexpected.getFirstName());
        map.put("lastName", unexpected.getLastName());
        map.put("phone", unexpected.getPhone());
        map.put("email", unexpected.getEmail());
        map.put("education", unexpected.getEducation());
        map.put("educationStatus", unexpected.getEducationStatus());
        map.put("originalStudyYear", unexpected.getOriginalStudyYear());
        map.put("event", unexpected.getEvent());
        map.put("candidateSkillsList", unexpected.getCandidateSkillsList());
        map.put("candidateNotesList", unexpected.getCandidateNotesList());
        map.put("dateOfAdding", unexpected.getDateOfAdding());
        map.put("checkCandidate", unexpected.getCheckCandidate());
        Candidate actual = validationService.patchCandidate(map, 1L);
        Assert.assertNotEquals("Testing to see that the object are different if id is omitted", unexpected, actual);
    }

    @Test
    public void patchCandidateNoFirstNameTest() {
        Candidate unexpected = getExpected();
        Map<String, Object> map = new HashMap<>();
        map.put("id", unexpected.getId());
        map.put("lastName", unexpected.getLastName());
        map.put("phone", unexpected.getPhone());
        map.put("email", unexpected.getEmail());
        map.put("education", unexpected.getEducation());
        map.put("educationStatus", unexpected.getEducationStatus());
        map.put("originalStudyYear", unexpected.getOriginalStudyYear());
        map.put("event", unexpected.getEvent());
        map.put("candidateSkillsList", unexpected.getCandidateSkillsList());
        map.put("candidateNotesList", unexpected.getCandidateNotesList());
        map.put("dateOfAdding", unexpected.getDateOfAdding());
        map.put("checkCandidate", unexpected.getCheckCandidate());
        Candidate actual = validationService.patchCandidate(map, 1L);
        Assert.assertNotEquals("Testing to see that the object are different if first name is omitted", unexpected, actual);
    }

    @Test
    public void patchCandidateNoLastNameTest() {
        Candidate unexpected = getExpected();
        Map<String, Object> map = new HashMap<>();
        map.put("id", unexpected.getId());
        map.put("firstName", unexpected.getFirstName());
        map.put("phone", unexpected.getPhone());
        map.put("email", unexpected.getEmail());
        map.put("education", unexpected.getEducation());
        map.put("educationStatus", unexpected.getEducationStatus());
        map.put("originalStudyYear", unexpected.getOriginalStudyYear());
        map.put("event", unexpected.getEvent());
        map.put("candidateSkillsList", unexpected.getCandidateSkillsList());
        map.put("candidateNotesList", unexpected.getCandidateNotesList());
        map.put("dateOfAdding", unexpected.getDateOfAdding());
        map.put("checkCandidate", unexpected.getCheckCandidate());
        Candidate actual = validationService.patchCandidate(map, 1L);
        Assert.assertNotEquals("Testing to see that the object are different if last name is omitted", unexpected, actual);
    }

    @Test
    public void patchCandidateNoPhoneTest() {
        Candidate unexpected = getExpected();
        Map<String, Object> map = new HashMap<>();
        map.put("id", unexpected.getId());
        map.put("firstName", unexpected.getFirstName());
        map.put("lastName", unexpected.getLastName());
        map.put("email", unexpected.getEmail());
        map.put("education", unexpected.getEducation());
        map.put("educationStatus", unexpected.getEducationStatus());
        map.put("originalStudyYear", unexpected.getOriginalStudyYear());
        map.put("event", unexpected.getEvent());
        map.put("candidateSkillsList", unexpected.getCandidateSkillsList());
        map.put("candidateNotesList", unexpected.getCandidateNotesList());
        map.put("dateOfAdding", unexpected.getDateOfAdding());
        map.put("checkCandidate", unexpected.getCheckCandidate());
        Candidate actual = validationService.patchCandidate(map, 1L);
        Assert.assertNotEquals("Testing to see that the object are different if phone is omitted", unexpected, actual);
    }

    @Test
    public void patchCandidateNoEmailTest() {
        Candidate unexpected = getExpected();
        Map<String, Object> map = new HashMap<>();
        map.put("id", unexpected.getId());
        map.put("firstName", unexpected.getFirstName());
        map.put("lastName", unexpected.getLastName());
        map.put("phone", unexpected.getPhone());
        map.put("education", unexpected.getEducation());
        map.put("educationStatus", unexpected.getEducationStatus());
        map.put("originalStudyYear", unexpected.getOriginalStudyYear());
        map.put("event", unexpected.getEvent());
        map.put("candidateSkillsList", unexpected.getCandidateSkillsList());
        map.put("candidateNotesList", unexpected.getCandidateNotesList());
        map.put("dateOfAdding", unexpected.getDateOfAdding());
        map.put("checkCandidate", unexpected.getCheckCandidate());
        Candidate actual = validationService.patchCandidate(map, 1L);
        Assert.assertNotEquals("Testing to see that the object are different if email is omitted", unexpected, actual);
    }

    @Test
    public void patchCandidateNoEducationTest() {
        Candidate unexpected = getExpected();
        Map<String, Object> map = new HashMap<>();
        map.put("id", unexpected.getId());
        map.put("firstName", unexpected.getFirstName());
        map.put("lastName", unexpected.getLastName());
        map.put("phone", unexpected.getPhone());
        map.put("email", unexpected.getEmail());
        map.put("educationStatus", unexpected.getEducationStatus());
        map.put("originalStudyYear", unexpected.getOriginalStudyYear());
        map.put("event", unexpected.getEvent());
        map.put("candidateSkillsList", unexpected.getCandidateSkillsList());
        map.put("candidateNotesList", unexpected.getCandidateNotesList());
        map.put("dateOfAdding", unexpected.getDateOfAdding());
        map.put("checkCandidate", unexpected.getCheckCandidate());
        Candidate actual = validationService.patchCandidate(map, 1L);
        Assert.assertNotEquals("Testing to see that the object are different if education is omitted", unexpected, actual);
    }

    @Test
    public void patchCandidateNoEducationStatusTest() {
        Candidate unexpected = getExpected();
        Map<String, Object> map = new HashMap<>();
        map.put("id", unexpected.getId());
        map.put("firstName", unexpected.getFirstName());
        map.put("lastName", unexpected.getLastName());
        map.put("phone", unexpected.getPhone());
        map.put("email", unexpected.getEmail());
        map.put("education", unexpected.getEducation());
        map.put("originalStudyYear", unexpected.getOriginalStudyYear());
        map.put("event", unexpected.getEvent());
        map.put("candidateSkillsList", unexpected.getCandidateSkillsList());
        map.put("candidateNotesList", unexpected.getCandidateNotesList());
        map.put("dateOfAdding", unexpected.getDateOfAdding());
        map.put("checkCandidate", unexpected.getCheckCandidate());
        Candidate actual = validationService.patchCandidate(map, 1L);
        Assert.assertNotEquals("Testing to see that the object are different if education status is omitted", unexpected, actual);
    }

    @Test
    public void patchCandidateNoOriginalStudyYearTest() {
        Candidate unexpected = getExpected();
        Map<String, Object> map = new HashMap<>();
        map.put("id", unexpected.getId());
        map.put("firstName", unexpected.getFirstName());
        map.put("lastName", unexpected.getLastName());
        map.put("phone", unexpected.getPhone());
        map.put("email", unexpected.getEmail());
        map.put("education", unexpected.getEducation());
        map.put("educationStatus", unexpected.getEducationStatus());
        map.put("event", unexpected.getEvent());
        map.put("candidateSkillsList", unexpected.getCandidateSkillsList());
        map.put("candidateNotesList", unexpected.getCandidateNotesList());
        map.put("dateOfAdding", unexpected.getDateOfAdding());
        map.put("checkCandidate", unexpected.getCheckCandidate());
        Candidate actual = validationService.patchCandidate(map, 1L);
        Assert.assertNotEquals("Testing to see that the object are different if original study year is omitted", unexpected, actual);
    }

    @Test
    public void patchCandidateNoEventTest() {
        Candidate unexpected = getExpected();
        Map<String, Object> map = new HashMap<>();
        map.put("id", unexpected.getId());
        map.put("firstName", unexpected.getFirstName());
        map.put("lastName", unexpected.getLastName());
        map.put("phone", unexpected.getPhone());
        map.put("email", unexpected.getEmail());
        map.put("education", unexpected.getEducation());
        map.put("educationStatus", unexpected.getEducationStatus());
        map.put("originalStudyYear", unexpected.getOriginalStudyYear());
        map.put("candidateSkillsList", unexpected.getCandidateSkillsList());
        map.put("candidateNotesList", unexpected.getCandidateNotesList());
        map.put("dateOfAdding", unexpected.getDateOfAdding());
        map.put("checkCandidate", unexpected.getCheckCandidate());
        Candidate actual = validationService.patchCandidate(map, 1L);
        Assert.assertNotEquals("Testing to see that the object are different if event is omitted", unexpected, actual);
    }

    @Test
    public void patchCandidateNoCandidateSkillsListTest() {
        Candidate unexpected = getExpected();
        Map<String, Object> map = new HashMap<>();
        map.put("id", unexpected.getId());
        map.put("firstName", unexpected.getFirstName());
        map.put("lastName", unexpected.getLastName());
        map.put("phone", unexpected.getPhone());
        map.put("email", unexpected.getEmail());
        map.put("education", unexpected.getEducation());
        map.put("educationStatus", unexpected.getEducationStatus());
        map.put("originalStudyYear", unexpected.getOriginalStudyYear());
        map.put("event", unexpected.getEvent());
        map.put("candidateNotesList", unexpected.getCandidateNotesList());
        map.put("dateOfAdding", unexpected.getDateOfAdding());
        map.put("checkCandidate", unexpected.getCheckCandidate());
        Candidate actual = validationService.patchCandidate(map, 1L);
        Assert.assertNotEquals("Testing to see that the object are different if candidate skills list is omitted", unexpected, actual);
    }

    @Test
    public void patchCandidateNoCandidateNotesListTest() {
        Candidate unexpected = getExpected();
        Map<String, Object> map = new HashMap<>();
        map.put("id", unexpected.getId());
        map.put("firstName", unexpected.getFirstName());
        map.put("lastName", unexpected.getLastName());
        map.put("phone", unexpected.getPhone());
        map.put("email", unexpected.getEmail());
        map.put("education", unexpected.getEducation());
        map.put("educationStatus", unexpected.getEducationStatus());
        map.put("originalStudyYear", unexpected.getOriginalStudyYear());
        map.put("event", unexpected.getEvent());
        map.put("candidateSkillsList", unexpected.getCandidateSkillsList());
        map.put("dateOfAdding", unexpected.getDateOfAdding());
        map.put("checkCandidate", unexpected.getCheckCandidate());
        Candidate actual = validationService.patchCandidate(map, 1L);
        Assert.assertNotEquals("Testing to see that the object are different if canidate notes list is omitted", unexpected, actual);
    }

    @Test
    public void patchCandidateNoDateOfAddingTest() {
        Candidate unexpected = getExpected();
        Map<String, Object> map = new HashMap<>();
        map.put("id", unexpected.getId());
        map.put("firstName", unexpected.getFirstName());
        map.put("lastName", unexpected.getLastName());
        map.put("phone", unexpected.getPhone());
        map.put("email", unexpected.getEmail());
        map.put("education", unexpected.getEducation());
        map.put("educationStatus", unexpected.getEducationStatus());
        map.put("originalStudyYear", unexpected.getOriginalStudyYear());
        map.put("event", unexpected.getEvent());
        map.put("candidateSkillsList", unexpected.getCandidateSkillsList());
        map.put("candidateNotesList", unexpected.getCandidateNotesList());
        map.put("checkCandidate", unexpected.getCheckCandidate());
        Candidate actual = validationService.patchCandidate(map, 1L);
        Assert.assertNotEquals("Testing to see that the object are different if date of adding is omitted", unexpected, actual);
    }

    @Test
    public void patchCandidateNoCheckCandidateTest() {
        Candidate unexpected = getExpected();
        Map<String, Object> map = new HashMap<>();
        map.put("id", unexpected.getId());
        map.put("firstName", unexpected.getFirstName());
        map.put("lastName", unexpected.getLastName());
        map.put("phone", unexpected.getPhone());
        map.put("email", unexpected.getEmail());
        map.put("education", unexpected.getEducation());
        map.put("educationStatus", unexpected.getEducationStatus());
        map.put("originalStudyYear", unexpected.getOriginalStudyYear());
        map.put("event", unexpected.getEvent());
        map.put("candidateSkillsList", unexpected.getCandidateSkillsList());
        map.put("candidateNotesList", unexpected.getCandidateNotesList());
        map.put("dateOfAdding", unexpected.getDateOfAdding());
        Candidate actual = validationService.patchCandidate(map, 1L);
        Assert.assertNotEquals("Testing to see that the object are different if check candidate is omitted", unexpected, actual);
    }
}