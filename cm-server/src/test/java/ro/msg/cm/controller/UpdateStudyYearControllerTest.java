package ro.msg.cm.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ro.msg.cm.Main;
import ro.msg.cm.model.*;
import ro.msg.cm.repository.CandidateRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@WebAppConfiguration
public class UpdateStudyYearControllerTest {

    @Autowired
    private UpdateStudyYearController controller;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private StartYearProperties properties = new StartYearProperties();

    @Before
    public void setUp() {
        controller = new UpdateStudyYearController(candidateRepository, properties);
    }

    @Test
    public void shouldModifyTheStudyYear() throws Exception {
        String stringDateOfAdding = "2015-09-23";
        SimpleDateFormat d = getSimpleDateFormat();
        Date dateOfAdding = d.parse(stringDateOfAdding);
        Candidate actualCandidate = createCandidate(dateOfAdding, 1, 3, 4);

        Candidate expectedCandidate = getCandidateFromList(0);
        verify(actualCandidate, expectedCandidate);
    }

    @Test
    public void shouldModifyTheStudyYearAccordingToStudyDuration() throws Exception {
        String stringDateOfAdding = "2016-10-25";
        SimpleDateFormat d = getSimpleDateFormat();
        Date dateOfAdding = d.parse(stringDateOfAdding);
        Candidate actualCandidate = createCandidate(dateOfAdding, 3, -1, 2);

        Candidate expectedCandidate = getCandidateFromList(1);
        verify(actualCandidate, expectedCandidate);
    }

    @Test
    public void shouldNotModifyTheStudyYear() throws Exception {
        String stringDateOfAdding = "2017-01-02";
        SimpleDateFormat d = getSimpleDateFormat();
        Date dateOfAdding = d.parse(stringDateOfAdding);
        Candidate actualCandidate = createCandidate(dateOfAdding, 2, 2, 3);

        Candidate expectedCandidate = getCandidateFromList(20);
        verify(actualCandidate, expectedCandidate);
    }

    private SimpleDateFormat getSimpleDateFormat() {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        d.setTimeZone(TimeZone.getTimeZone("UTC"));
        return d;
    }

    private void verify(Candidate expectedCandidate, Candidate actualCandidate) {
        Assert.assertEquals(expectedCandidate.getOriginalStudyYear(), actualCandidate.getOriginalStudyYear());
        Assert.assertEquals(expectedCandidate.getEducation().getDuration(), actualCandidate.getEducation().getDuration());
        Assert.assertEquals(expectedCandidate.getCurrentStudyYear(), actualCandidate.getCurrentStudyYear());
    }

    private Candidate getCandidateFromList(int position) throws ParseException {
        Iterable<Candidate> candidates = controller.updateUniversityYear();

        List<Candidate> candidateList = new ArrayList<>();
        candidates.forEach(candidateList::add);
        return candidateList.get(position);
    }

    private Candidate createCandidate(Date dateOfAdding, int originalStudyYear, int currentStudyYear, int duration) throws ParseException {
        Candidate candidate = new Candidate();
        List<CandidateSkills> candidateSkillsList = new ArrayList<>();
        candidateSkillsList.add(createCandidateSkills());
        List<CandidateNotes> candidateNotesList = new ArrayList<>();
        candidateNotesList.add(createNotesList());

        candidate.setId(1L);
        candidate.setDateOfAdding(dateOfAdding);
        candidate.setEducationStatus("student");
        candidate.setEmail("email");
        candidate.setEvent("event");
        candidate.setFirstName("firstName");
        candidate.setLastName("lastName");
        candidate.setPhone("phone");
        candidate.setOriginalStudyYear(originalStudyYear);
        candidate.setCandidateSkillsList(candidateSkillsList);
        candidate.setCandidateNotesList(candidateNotesList);
        candidate.setEducation(createEducation(duration));
        candidate.setCurrentStudyYear(currentStudyYear);

        return candidate;
    }

    private CandidateNotes createNotesList() throws ParseException {
        CandidateNotes candidateNotes = new CandidateNotes();
        String stringDate = "2017-06-10 14:00";
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(stringDate);

        candidateNotes.setId(1L);
        candidateNotes.setDate(date);
        candidateNotes.setStatus("status");
        candidateNotes.setNote("note");
        return candidateNotes;
    }

    private Education createEducation(int duration) {
        Education education = new Education();
        education.setId(1L);
        education.setDescription("description");
        education.setDuration(duration);
        education.setEducationType("educationType");
        education.setProvider("provider");
        return education;
    }

    private CandidateSkills createCandidateSkills() {
        CandidateSkills candidateSkills = new CandidateSkills();
        candidateSkills.setId(1L);
        candidateSkills.setCertifier("certifier");
        candidateSkills.setRating("rating");
        candidateSkills.setTag(createTag());
        return candidateSkills;
    }

    private Tag createTag() {
        Tag tag = new Tag();
        tag.setId(1L);
        tag.setTagType("tagType");
        tag.setDescription("description");
        return tag;
    }

}