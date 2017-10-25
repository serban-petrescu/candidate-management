package ro.msg.cm.util;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ro.msg.cm.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class UpdateUniversityYearUtilsTest {
//
//    private static CandidateUtils updateUniversityYearUtils;
//
//    @BeforeClass
//    public static void setUp() {
//        StartYearProperties underTest = new StartYearProperties();
//        underTest.setStartYearDate("10-01");
//        updateUniversityYearUtils = new CandidateUtils(underTest);
//
//    }
//
//    @Test
//    public void shouldModifyTheStudyYear() throws Exception {
//        String stringDateOfAdding = "2015-09-23";
//        SimpleDateFormat d = getSimpleDateFormat();
//        Date dateOfAdding = d.parse(stringDateOfAdding);
//        Candidate actualCandidate = createCandidate(dateOfAdding, 1, 3, 4);
//        verifyConditions(actualCandidate);
//    }
//
//    @Test
//    public void shouldModifyTheStudyYearAccordingToStudyDuration() throws Exception {
//        String stringDateOfAdding = "2016-10-25";
//        SimpleDateFormat d = getSimpleDateFormat();
//        Date dateOfAdding = d.parse(stringDateOfAdding);
//        Candidate actualCandidate = createCandidate(dateOfAdding, 3, -1, 2);
//        verifyConditions(actualCandidate);
//    }
//
//    @Test
//    public void shouldNotModifyTheStudyYear() throws Exception {
//        String stringDateOfAdding = "2017-01-02";
//        SimpleDateFormat d = getSimpleDateFormat();
//        Date dateOfAdding = d.parse(stringDateOfAdding);
//        Candidate actualCandidate = createCandidate(dateOfAdding, 2, 2, 3);
//        verifyConditions(actualCandidate);
//    }
//
//    private void verifyConditions(Candidate actualCandidate) throws ParseException {
//        int expectedCurrentStudyYear = determineYearBasedOnDuration(actualCandidate);
//        Assert.assertEquals(expectedCurrentStudyYear, actualCandidate.getCurrentStudyYear());
//    }
//
//    private Candidate createCandidate(Date dateOfAdding, int originalStudyYear, int currentStudyYear, int duration) throws ParseException {
//        Candidate candidate = new Candidate();
//        List<CandidateSkills> candidateSkillsList = new ArrayList<>();
//        candidateSkillsList.add(createCandidateSkills());
//        List<CandidateNotes> candidateNotesList = new ArrayList<>();
//        candidateNotesList.add(createNotesList());
//
//        candidate.setId(1L);
//        candidate.setDateOfAdding(dateOfAdding);
//        candidate.setEducationStatus("student");
//        candidate.setEmail("email");
//        candidate.setEvent("event");
//        candidate.setFirstName("firstName");
//        candidate.setLastName("lastName");
//        candidate.setPhone("phone");
//        candidate.setOriginalStudyYear(originalStudyYear);
//        candidate.setCandidateSkillsList(candidateSkillsList);
//        candidate.setCandidateNotesList(candidateNotesList);
//        candidate.setEducation(createEducation(duration));
//        candidate.setCurrentStudyYear(currentStudyYear);
//
//        return candidate;
//    }
//
//    private CandidateNotes createNotesList() throws ParseException {
//        CandidateNotes candidateNotes = new CandidateNotes();
//        String stringDate = "2017-06-10 14:00";
//        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(stringDate);
//
//        candidateNotes.setId(1L);
//        candidateNotes.setDate(date);
//        candidateNotes.setStatus("status");
//        candidateNotes.setNote("note");
//        return candidateNotes;
//    }
//
//    private Education createEducation(int duration) {
//        Education education = new Education();
//        education.setId(1L);
//        education.setDescription("description");
//        education.setDuration(duration);
//        education.setEducationType("educationType");
//        education.setProvider("provider");
//        return education;
//    }
//
//    private CandidateSkills createCandidateSkills() {
//        CandidateSkills candidateSkills = new CandidateSkills();
//        candidateSkills.setId(1L);
//        candidateSkills.setCertifier("certifier");
//        candidateSkills.setRating("rating");
//        candidateSkills.setTag(createTag());
//        return candidateSkills;
//    }
//
//    private Tag createTag() {
//        Tag tag = new Tag();
//        tag.setId(1L);
//        tag.setTagType("tagType");
//        tag.setDescription("description");
//        return tag;
//    }
//
//    private SimpleDateFormat getSimpleDateFormat() {
//        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
//        d.setTimeZone(TimeZone.getTimeZone("UTC"));
//        return d;
//    }
//
//    private int determineYearBasedOnDuration(Candidate candidate) throws ParseException {
//        return updateUniversityYearUtils.determineYearBasedOnDuration(candidate);
//    }
}