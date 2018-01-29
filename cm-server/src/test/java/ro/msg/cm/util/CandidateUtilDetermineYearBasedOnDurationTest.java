package ro.msg.cm.util;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ro.msg.cm.model.Candidate;
import ro.msg.cm.model.Education;
import ro.msg.cm.model.StartYearProperties;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CandidateUtilDetermineYearBasedOnDurationTest {

    private static CandidateUtils updateUniversityYearUtils;

    @BeforeClass
    public static void setUpClass() {
        StartYearProperties underTest = new StartYearProperties();
        underTest.setStartYearDate("10-01");
        updateUniversityYearUtils = new CandidateUtils(underTest);
    }

    @Test
    public void shouldModifyTheStudyYear() throws ParseException {
        Candidate candidate = getCandidate(1, 4, "2015-09-23");
        setToday("2017-10-30");
        Assert.assertEquals(4, updateUniversityYearUtils.determineYearBasedOnDuration(candidate));
    }

    @Test
    public void shouldModifyTheStudyYearAccordingToStudyDuration() throws ParseException {
        Candidate candidate = getCandidate(3, 2, "2016-10-25");
        setToday("2017-10-30");
        Assert.assertEquals(-1, updateUniversityYearUtils.determineYearBasedOnDuration(candidate));
    }

    @Test
    public void shouldNotModifyTheStudyYear() throws ParseException {
        Candidate candidate = getCandidate(2, 3, "2017-01-02");
        setToday("2017-10-30");
        Assert.assertEquals(3, updateUniversityYearUtils.determineYearBasedOnDuration(candidate));
    }

    private Candidate getCandidate(int OriginalStudyYear, int duration, String yearYMD) throws ParseException {
        Candidate candidate = new Candidate();
        candidate.setDateOfAdding(new SimpleDateFormat("yyyy-MM-dd").parse(yearYMD));
        candidate.setOriginalStudyYear(OriginalStudyYear);
        candidate.setEducation(new Education());
        candidate.getEducation().setDuration(duration);
        return candidate;
    }

    private void setToday(String yearYMD) throws ParseException {
        updateUniversityYearUtils.setGivenDate(new SimpleDateFormat("yyyy-MM-dd").parse(yearYMD));
    }
}