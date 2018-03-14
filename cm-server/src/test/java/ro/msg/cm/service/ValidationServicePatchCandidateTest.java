package ro.msg.cm.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import ro.msg.cm.dto.CandidateDto;
import ro.msg.cm.exception.CandidateNotFoundException;
import ro.msg.cm.exception.PatchCandidateInvalidValueException;
import ro.msg.cm.model.Candidate;
import ro.msg.cm.repository.CandidateRepository;
import ro.msg.cm.types.CandidateCheck;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ValidationServicePatchCandidateTest {

    @Mock
    private CandidateRepository mockCandidateRepository;

    @InjectMocks
    private CandidateService validationService;

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
        candidate.setEducationStatus("senior");
        candidate.setOriginalStudyYear(2);
        candidate.setEvent("Event");
        candidate.setDateOfAdding(LocalDate.parse("1996-02-18"));
        candidate.setCheckCandidate(CandidateCheck.NOT_YET_VALIDATED);
        candidateList.add(candidate);
        candidate = new Candidate();
        candidate.setId(5L);
        candidate.setCheckCandidate(CandidateCheck.VALIDATED);
        candidateList.add(candidate);
    }

    private Candidate getExpected() {
        Candidate candidate = new Candidate();
        candidate.setId(1L);
        candidate.setFirstName("FirstName");
        candidate.setLastName("LastName");
        candidate.setPhone("9876543210");
        candidate.setEmail("user@domain.com");
        candidate.setEducationStatus("status");
        candidate.setOriginalStudyYear(0);
        candidate.setEvent("newEvent");
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
        validationService.patchCandidate(new CandidateDto(), 2L);
    }

    @Test(expected = CandidateNotFoundException.class)
    public void patchCandidateOfValidatedCandidateTest() {
        validationService.patchCandidate(new CandidateDto(), 5L);
    }

    @Test()
    public void patchCandidateOfNoPatchCandidateTest() {
        Candidate actual = mockCandidateRepository.findOne(1L);
        Candidate expected = validationService.patchCandidate(new CandidateDto(), 1L);
        Assert.assertEquals("Testing with an empty map ", expected, actual);
    }

    @Test
    public void patchCandidateFirstNameTest() {
        String expectedName = getExpected().getFirstName();
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setFirstName(expectedName);
        Candidate actual = validationService.patchCandidate(candidateDto, 1L);
        Assert.assertEquals("Patching the first name ", expectedName, actual.getFirstName());
    }

    @Test
    public void patchCandidateLastNameTest() {
        String expectedName = getExpected().getLastName();
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setLastName(expectedName);
        Candidate actual = validationService.patchCandidate(candidateDto, 1L);
        Assert.assertEquals("Patching the last name ", expectedName, actual.getLastName());
    }

    @Test
    public void patchCandidatePhoneTest() {
        String expectedPhone = getExpected().getPhone();
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setPhone(expectedPhone);
        Candidate actual = validationService.patchCandidate(candidateDto, 1L);
        Assert.assertEquals("Patching the phone ", expectedPhone, actual.getPhone());
    }

    @Test(expected = PatchCandidateInvalidValueException.class)
    public void patchCandidatePhoneTooSmallTest() {
        String expectedPhone = "123456789";
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setPhone(expectedPhone);
        validationService.patchCandidate(candidateDto, 1L);
    }

    @Test(expected = PatchCandidateInvalidValueException.class)
    public void patchCandidatePhoneTooBigTest() {
        String expectedPhone = "0123456789123456";
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setPhone(expectedPhone);
        validationService.patchCandidate(candidateDto, 1L);
    }


    @Test(expected = PatchCandidateInvalidValueException.class)
    public void patchCandidatePhoneNotNumbersTest() {
        String expectedPhone = "notNumbers";
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setPhone(expectedPhone);
        validationService.patchCandidate(candidateDto, 1L);
    }

    @Test
    public void patchCandidateEmailTest() {
        String expectedEmail = getExpected().getEmail();
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setEmail(expectedEmail);
        Candidate actual = validationService.patchCandidate(candidateDto, 1L);
        Assert.assertEquals("Patching the phone ", expectedEmail, actual.getEmail());
    }

    @Test(expected = PatchCandidateInvalidValueException.class)
    public void patchCandidateEmailInvalidTest() {
        String expectedEmail = "email";
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setEmail(expectedEmail);
        validationService.patchCandidate(candidateDto, 1L);
    }

    @Test
    public void patchCandidateEducationStatusTest() {
        String expectedEducationStatus = getExpected().getEducationStatus();
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setEducationStatus(expectedEducationStatus);
        Candidate actual = validationService.patchCandidate(candidateDto, 1L);
        Assert.assertEquals("Patching the education status ", expectedEducationStatus, actual.getEducationStatus());
    }

    @Test
    public void patchCandidateOriginalStudyYearTest() {
        int expectedOriginalStudyYear = getExpected().getOriginalStudyYear();
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setOriginalStudyYear(expectedOriginalStudyYear);
        Candidate actual = validationService.patchCandidate(candidateDto, 1L);
        Assert.assertEquals("Patching the original study year ", expectedOriginalStudyYear, actual.getOriginalStudyYear());
    }

    @Test
    public void patchCandidateEventTest() {
        String expectedEvent = getExpected().getEvent();
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setEvent(expectedEvent);
        Candidate actual = validationService.patchCandidate(candidateDto, 1L);
        Assert.assertEquals("Patching the event ", expectedEvent, actual.getEvent());
    }

    @Test
    public void patchCandidateDateOfAddingTest() {
        LocalDate expectedDate = getExpected().getDateOfAdding();
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setDateOfAdding(expectedDate);
        Candidate actual = validationService.patchCandidate(candidateDto, 1L);
        Assert.assertEquals("Patching the date ", expectedDate, actual.getDateOfAdding());
    }

    @Test
    public void patchCandidateCheckCandidateTest() {
        CandidateCheck expectedCheckCandidate = getExpected().getCheckCandidate();
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setCheckCandidate(expectedCheckCandidate);
        Candidate actual = validationService.patchCandidate(candidateDto, 1L);
        Assert.assertEquals("Patching the check candidate ", expectedCheckCandidate, actual.getCheckCandidate());
    }

    @Test
    public void patchCandidateAllTest() {
        Candidate expected = getExpected();
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setFirstName(expected.getFirstName());
        candidateDto.setLastName(expected.getLastName());
        candidateDto.setPhone(expected.getPhone());
        candidateDto.setEmail(expected.getEmail());
        candidateDto.setEducationStatus(expected.getEducationStatus());
        candidateDto.setOriginalStudyYear(expected.getOriginalStudyYear());
        candidateDto.setEvent(expected.getEvent());
        candidateDto.setDateOfAdding(expected.getDateOfAdding());
        candidateDto.setCheckCandidate(expected.getCheckCandidate());
        Candidate actual = validationService.patchCandidate(candidateDto, 1L);
        Assert.assertEquals("Testing with a full map ", expected, actual);
    }

    @Test
    public void patchCandidateAllToNullTest() {
        Candidate expected = new Candidate();
        expected.setId(1L);
        expected.setDateOfAdding(null);
        expected.setCheckCandidate(null);
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setFirstName(expected.getFirstName());
        candidateDto.setLastName(expected.getLastName());
        candidateDto.setPhone(expected.getPhone());
        candidateDto.setEmail(expected.getEmail());
        candidateDto.setEducationStatus(expected.getEducationStatus());
        candidateDto.setOriginalStudyYear(expected.getOriginalStudyYear());
        candidateDto.setEvent(expected.getEvent());
        candidateDto.setDateOfAdding(expected.getDateOfAdding());
        candidateDto.setCheckCandidate(expected.getCheckCandidate());
        Candidate actual = validationService.patchCandidate(candidateDto, 1L);
        Assert.assertEquals("Testing with everything set to null ", expected, actual);
    }

    @Test
    public void patchCandidateNoFirstNameTest() {
        Candidate unexpected = getExpected();
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setLastName(unexpected.getLastName());
        candidateDto.setPhone(unexpected.getPhone());
        candidateDto.setEmail(unexpected.getEmail());
        candidateDto.setEducationStatus(unexpected.getEducationStatus());
        candidateDto.setOriginalStudyYear(unexpected.getOriginalStudyYear());
        candidateDto.setEvent(unexpected.getEvent());
        candidateDto.setDateOfAdding(unexpected.getDateOfAdding());
        candidateDto.setCheckCandidate(unexpected.getCheckCandidate());
        Candidate actual = validationService.patchCandidate(candidateDto, 1L);
        Assert.assertNotEquals("Testing to see that the object are different if first name is omitted", unexpected, actual);
    }

    @Test
    public void patchCandidateNoLastNameTest() {
        Candidate unexpected = getExpected();
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setFirstName(unexpected.getFirstName());
        candidateDto.setPhone(unexpected.getPhone());
        candidateDto.setEmail(unexpected.getEmail());
        candidateDto.setEducationStatus(unexpected.getEducationStatus());
        candidateDto.setOriginalStudyYear(unexpected.getOriginalStudyYear());
        candidateDto.setEvent(unexpected.getEvent());
        candidateDto.setDateOfAdding(unexpected.getDateOfAdding());
        candidateDto.setCheckCandidate(unexpected.getCheckCandidate());
        Candidate actual = validationService.patchCandidate(candidateDto, 1L);
        Assert.assertNotEquals("Testing to see that the object are different if last name is omitted", unexpected, actual);
    }

    @Test
    public void patchCandidateNoPhoneTest() {
        Candidate unexpected = getExpected();
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setFirstName(unexpected.getFirstName());
        candidateDto.setLastName(unexpected.getLastName());
        candidateDto.setEmail(unexpected.getEmail());
        candidateDto.setEducationStatus(unexpected.getEducationStatus());
        candidateDto.setOriginalStudyYear(unexpected.getOriginalStudyYear());
        candidateDto.setEvent(unexpected.getEvent());
        candidateDto.setDateOfAdding(unexpected.getDateOfAdding());
        candidateDto.setCheckCandidate(unexpected.getCheckCandidate());
        Candidate actual = validationService.patchCandidate(candidateDto, 1L);
        Assert.assertNotEquals("Testing to see that the object are different if phone is omitted", unexpected, actual);
    }

    @Test
    public void patchCandidateNoEmailTest() {
        Candidate unexpected = getExpected();
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setFirstName(unexpected.getFirstName());
        candidateDto.setLastName(unexpected.getLastName());
        candidateDto.setPhone(unexpected.getPhone());
        candidateDto.setEducationStatus(unexpected.getEducationStatus());
        candidateDto.setOriginalStudyYear(unexpected.getOriginalStudyYear());
        candidateDto.setEvent(unexpected.getEvent());
        candidateDto.setDateOfAdding(unexpected.getDateOfAdding());
        candidateDto.setCheckCandidate(unexpected.getCheckCandidate());
        Candidate actual = validationService.patchCandidate(candidateDto, 1L);
        Assert.assertNotEquals("Testing to see that the object are different if email is omitted", unexpected, actual);
    }

    @Test
    public void patchCandidateNoEducationStatusTest() {
        Candidate unexpected = getExpected();
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setFirstName(unexpected.getFirstName());
        candidateDto.setLastName(unexpected.getLastName());
        candidateDto.setPhone(unexpected.getPhone());
        candidateDto.setEmail(unexpected.getEmail());
        candidateDto.setOriginalStudyYear(unexpected.getOriginalStudyYear());
        candidateDto.setEvent(unexpected.getEvent());
        candidateDto.setDateOfAdding(unexpected.getDateOfAdding());
        candidateDto.setCheckCandidate(unexpected.getCheckCandidate());
        Candidate actual = validationService.patchCandidate(candidateDto, 1L);
        Assert.assertNotEquals("Testing to see that the object are different if education status is omitted", unexpected, actual);
    }

    @Test
    public void patchCandidateNoOriginalStudyYearTest() {
        Candidate unexpected = getExpected();
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setFirstName(unexpected.getFirstName());
        candidateDto.setLastName(unexpected.getLastName());
        candidateDto.setPhone(unexpected.getPhone());
        candidateDto.setEmail(unexpected.getEmail());
        candidateDto.setEducationStatus(unexpected.getEducationStatus());
        candidateDto.setEvent(unexpected.getEvent());
        candidateDto.setDateOfAdding(unexpected.getDateOfAdding());
        candidateDto.setCheckCandidate(unexpected.getCheckCandidate());
        Candidate actual = validationService.patchCandidate(candidateDto, 1L);
        Assert.assertNotEquals("Testing to see that the object are different if original study year is omitted", unexpected, actual);
    }

    @Test
    public void patchCandidateNoEventTest() {
        Candidate unexpected = getExpected();
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setFirstName(unexpected.getFirstName());
        candidateDto.setLastName(unexpected.getLastName());
        candidateDto.setPhone(unexpected.getPhone());
        candidateDto.setEmail(unexpected.getEmail());
        candidateDto.setEducationStatus(unexpected.getEducationStatus());
        candidateDto.setOriginalStudyYear(unexpected.getOriginalStudyYear());
        candidateDto.setDateOfAdding(unexpected.getDateOfAdding());
        candidateDto.setCheckCandidate(unexpected.getCheckCandidate());
        Candidate actual = validationService.patchCandidate(candidateDto, 1L);
        Assert.assertNotEquals("Testing to see that the object are different if event is omitted", unexpected, actual);
    }

    @Test
    public void patchCandidateNoDateOfAddingTest() {
        Candidate unexpected = getExpected();
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setFirstName(unexpected.getFirstName());
        candidateDto.setLastName(unexpected.getLastName());
        candidateDto.setPhone(unexpected.getPhone());
        candidateDto.setEmail(unexpected.getEmail());
        candidateDto.setEducationStatus(unexpected.getEducationStatus());
        candidateDto.setOriginalStudyYear(unexpected.getOriginalStudyYear());
        candidateDto.setEvent(unexpected.getEvent());
        candidateDto.setCheckCandidate(unexpected.getCheckCandidate());
        Candidate actual = validationService.patchCandidate(candidateDto, 1L);
        Assert.assertNotEquals("Testing to see that the object are different if date of adding is omitted", unexpected, actual);
    }

    @Test
    public void patchCandidateNoCheckCandidateTest() {
        Candidate unexpected = getExpected();
        CandidateDto candidateDto = new CandidateDto();
        candidateDto.setFirstName(unexpected.getFirstName());
        candidateDto.setLastName(unexpected.getLastName());
        candidateDto.setPhone(unexpected.getPhone());
        candidateDto.setEmail(unexpected.getEmail());
        candidateDto.setEducationStatus(unexpected.getEducationStatus());
        candidateDto.setOriginalStudyYear(unexpected.getOriginalStudyYear());
        candidateDto.setEvent(unexpected.getEvent());
        candidateDto.setDateOfAdding(unexpected.getDateOfAdding());
        Candidate actual = validationService.patchCandidate(candidateDto, 1L);
        Assert.assertNotEquals("Testing to see that the object are different if check candidate is omitted", unexpected, actual);
    }
}