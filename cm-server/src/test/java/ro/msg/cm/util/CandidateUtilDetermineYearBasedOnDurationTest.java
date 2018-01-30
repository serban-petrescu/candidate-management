package ro.msg.cm.util;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ro.msg.cm.model.Candidate;
import ro.msg.cm.model.Education;
import ro.msg.cm.model.StartYearProperties;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

public class CandidateUtilDetermineYearBasedOnDurationTest {

    private static CandidateUtils updateUniversityYearUtils;

    @BeforeClass
    public static void setUpClass() {
        StartYearProperties underTest = new StartYearProperties();
        underTest.setStartYearDate("10-01");
        updateUniversityYearUtils = new CandidateUtils(underTest);
    }

    @Test
    public void oneAndHalfYearDifference() {
        Candidate candidate = getCandidate(1, 4, "2015-10-15");
        String today="2017-04-15";
        setToday(today);
        Assert.assertEquals(getMessage(candidate,today),2, updateUniversityYearUtils.determineYearBasedOnDuration(candidate));
    }

    @Test
    public void oneAndHalfYearDifferenceWithDuration() {
        Candidate candidate = getCandidate(3, 3, "2015-11-13");
        String today="2017-05-13";
        setToday(today);
        Assert.assertEquals(getMessage(candidate,today),-1, updateUniversityYearUtils.determineYearBasedOnDuration(candidate));
    }

    @Test
    public void oneAndHalfYearDifferenceWithAddedDateOnNewUniversityYear() {
        Candidate candidate = getCandidate(1, 4, "2015-10-01");
        String today="2017-04-01";
        setToday(today);
        Assert.assertEquals(getMessage(candidate,today),2, updateUniversityYearUtils.determineYearBasedOnDuration(candidate));
    }

    @Test
    public void oneAndHalfYearDifferenceWithAddedDateOnNewUniversityYearWithDuration() {
        Candidate candidate = getCandidate(4, 4, "2015-10-01");
        String today="2017-04-01";
        setToday(today);
        Assert.assertEquals(getMessage(candidate,today),-1, updateUniversityYearUtils.determineYearBasedOnDuration(candidate));
    }

    @Test
    public void oneAndHalfYearDifferenceWithBonusYear() {
        Candidate candidate = getCandidate(1, 4, "2015-09-18");
        String today="2017-03-18";
        setToday(today);
        Assert.assertEquals(getMessage(candidate,today),3, updateUniversityYearUtils.determineYearBasedOnDuration(candidate));
    }

    @Test
    public void oneAndHalfYearDifferenceWithBonusYearWithDuration() {
        Candidate candidate = getCandidate(3, 4, "2015-08-17");
        String today="2017-02-17";
        setToday(today);
        Assert.assertEquals(getMessage(candidate,today),-1, updateUniversityYearUtils.determineYearBasedOnDuration(candidate));
    }

    @Test
    public void oneYearDifference() {
        Candidate candidate = getCandidate(1, 4, "2015-09-20");
        String today="2016-09-20";
        setToday(today);
        Assert.assertEquals(getMessage(candidate,today),2, updateUniversityYearUtils.determineYearBasedOnDuration(candidate));
    }

    @Test
    public void oneYearDifferenceWithAddedDateOnNewUniversityYear() {
        Candidate candidate = getCandidate(2, 4, "2015-10-01");
        String today="2016-10-01";
        setToday(today);
        Assert.assertEquals(getMessage(candidate,today),3, updateUniversityYearUtils.determineYearBasedOnDuration(candidate));
    }

    @Test
    public void oneYearDifferenceWithDuration() {
        Candidate candidate = getCandidate(3, 3, "2015-09-07");
        String today="2016-09-07";
        setToday(today);
        Assert.assertEquals(getMessage(candidate,today),-1, updateUniversityYearUtils.determineYearBasedOnDuration(candidate));
    }

    @Test
    public void halfYearDifferenceWithNewYear() {
        Candidate candidate = getCandidate(2, 4, "2015-09-05");
        String today="2016-03-05";
        setToday(today);
        Assert.assertEquals(getMessage(candidate,today),3, updateUniversityYearUtils.determineYearBasedOnDuration(candidate));
    }

    @Test
    public void halfYearDifferenceWithoutNewYear() {
        Candidate candidate = getCandidate(3, 4, "2015-04-08");
        String today="2015-10-08";
        setToday(today);
        Assert.assertEquals(getMessage(candidate,today),4, updateUniversityYearUtils.determineYearBasedOnDuration(candidate));
    }

    @Test
    public void halfYearDifferenceWithNewYearWithDuration() {
        Candidate candidate = getCandidate(3, 3, "2015-08-01");
        String today="2016-02-01";
        setToday(today);
        Assert.assertEquals(getMessage(candidate,today),-1, updateUniversityYearUtils.determineYearBasedOnDuration(candidate));
    }

    @Test
    public void halfYearDifferenceWithoutNewYearWithDuration() {
        Candidate candidate = getCandidate(5, 5, "2015-06-06");
        String today="2015-12-06";
        setToday(today);
        Assert.assertEquals(getMessage(candidate,today),-1, updateUniversityYearUtils.determineYearBasedOnDuration(candidate));
    }

    @Test
    public void halfYearDifferenceWithNewYearNoChange() {
        Candidate candidate = getCandidate(3, 3, "2015-11-01");
        String today="2016-05-01";
        setToday(today);
        Assert.assertEquals(getMessage(candidate,today),3, updateUniversityYearUtils.determineYearBasedOnDuration(candidate));
    }

    @Test
    public void halfYearDifferenceWithNewYearWithAddedDateOnNewUniversityYearNoChange() {
        Candidate candidate = getCandidate(3, 3, "2015-10-01");
        String today="2016-04-01";
        setToday(today);
        Assert.assertEquals(getMessage(candidate,today),3, updateUniversityYearUtils.determineYearBasedOnDuration(candidate));
    }

    @Test
    public void halfYearDifferenceWithoutNewYearNoChange() {
        Candidate candidate = getCandidate(3, 3, "2015-01-19");
        String today="2015-07-19";
        setToday(today);
        Assert.assertEquals(getMessage(candidate,today),3, updateUniversityYearUtils.determineYearBasedOnDuration(candidate));
    }

    @Test
    public void oneDayDifference() {
        Candidate candidate = getCandidate(3, 4, "2015-09-30");
        String today="2015-10-01";
        setToday(today);
        Assert.assertEquals(getMessage(candidate,today),4, updateUniversityYearUtils.determineYearBasedOnDuration(candidate));
    }

    @Test
    public void oneDayDifferenceWithDuration() {
        Candidate candidate = getCandidate(4, 4, "2015-09-30");
        String today="2015-10-01";
        setToday(today);
        Assert.assertEquals(getMessage(candidate,today),-1, updateUniversityYearUtils.determineYearBasedOnDuration(candidate));
    }

    @Test
    public void oneDayDifferenceNoChange() {
        Candidate candidate = getCandidate(3, 4, "2015-09-28");
        String today="2015-09-29";
        setToday(today);
        Assert.assertEquals(getMessage(candidate,today),3, updateUniversityYearUtils.determineYearBasedOnDuration(candidate));
    }

    @Test
    public void oneDayDifferenceWithNewYearsNoChange() {
        Candidate candidate = getCandidate(3, 4, "2015-12-31");
        String today="2016-01-01";
        setToday(today);
        Assert.assertEquals(getMessage(candidate,today),3, updateUniversityYearUtils.determineYearBasedOnDuration(candidate));
    }


    @Test
    public void oneDayDifferenceWithAddedDateOnNewUniversityYearNoChange() {
        Candidate candidate = getCandidate(3, 4, "2015-10-01");
        String today="2015-10-02";
        setToday(today);
        Assert.assertEquals(getMessage(candidate,today),3, updateUniversityYearUtils.determineYearBasedOnDuration(candidate));
    }

    @Test
    public void shouldModifyTheStudyYear_2018_01_29() {
        Candidate candidate = getCandidate(1, 4, "2015-09-23");
        String today="2018-01-29";
        setToday(today);
        Assert.assertEquals(getMessage(candidate,today),4, updateUniversityYearUtils.determineYearBasedOnDuration(candidate));
    }

    @Test
    public void shouldModifyTheStudyYearAccordingToStudyDuration_2018_01_29() {
        Candidate candidate = getCandidate(3, 2, "2016-10-25");
        String today="2018-01-29";
        setToday(today);
        Assert.assertEquals(getMessage(candidate,today),-1, updateUniversityYearUtils.determineYearBasedOnDuration(candidate));
    }

    @Test
    public void shouldNotModifyTheStudyYear_2018_01_29() {
        Candidate candidate = getCandidate(2, 3, "2017-01-02");
        String today="2018-01-29";
        setToday(today);
        Assert.assertEquals(getMessage(candidate,today),3, updateUniversityYearUtils.determineYearBasedOnDuration(candidate));
    }

    @Test
    public void shouldModifyTheStudyYear_2017_08_23() {
        Candidate candidate = getCandidate(1, 4, "2015-09-23");
        String today="2017-08-23";
        setToday(today);
        Assert.assertEquals(getMessage(candidate,today),3, updateUniversityYearUtils.determineYearBasedOnDuration(candidate));
    }

    @Test
    public void shouldModifyTheStudyYearAccordingToStudyDuration_2017_08_23() {
        Candidate candidate = getCandidate(3, 2, "2016-10-25");
        String today="2017-08-23";
        setToday(today);
        Assert.assertEquals(getMessage(candidate,today),-1, updateUniversityYearUtils.determineYearBasedOnDuration(candidate));
    }

    @Test
    public void shouldNotModifyTheStudyYear_2017_08_23() {
        Candidate candidate = getCandidate(2, 3, "2017-01-02");
        String today="2017-08-23";
        setToday(today);
        Assert.assertEquals(getMessage(candidate,today),2, updateUniversityYearUtils.determineYearBasedOnDuration(candidate));
    }

    @Test
    public void shouldModifyTheStudyYear_2017_10_30() {
        Candidate candidate = getCandidate(1, 4, "2015-09-23");
        String today="2017-10-30";
        setToday(today);
        Assert.assertEquals(getMessage(candidate,today),4, updateUniversityYearUtils.determineYearBasedOnDuration(candidate));
    }

    @Test
    public void shouldModifyTheStudyYearAccordingToStudyDuration_2017_10_30() {
        Candidate candidate = getCandidate(3, 2, "2016-10-25");
        String today="2017-10-30";
        setToday(today);
        Assert.assertEquals(getMessage(candidate,today),-1, updateUniversityYearUtils.determineYearBasedOnDuration(candidate));
    }

    @Test
    public void shouldNotModifyTheStudyYear_2017_10_30() {
        Candidate candidate = getCandidate(2, 3, "2017-01-02");
        String today="2017-10-30";
        setToday(today);
        Assert.assertEquals(getMessage(candidate,today),3, updateUniversityYearUtils.determineYearBasedOnDuration(candidate));
    }

    private Candidate getCandidate(int OriginalStudyYear, int duration, String yearYMD) {
        Candidate candidate = new Candidate();
        candidate.setDateOfAdding(Date.from(LocalDate.parse(yearYMD).atStartOfDay().toInstant(ZoneOffset.UTC)));
        candidate.setOriginalStudyYear(OriginalStudyYear);
        candidate.setEducation(new Education());
        candidate.getEducation().setDuration(duration);
        return candidate;
    }

    private void setToday(String yearYMD) {
        updateUniversityYearUtils.setGivenDate(Date.from(LocalDate.parse(yearYMD).atStartOfDay().toInstant(ZoneOffset.UTC)));
    }

    private String getMessage(Candidate candidate, String today) {
        return "\nTest with:"
                + "\n\ttoday = " + today
                + "\n\toriginal study year = " + candidate.getOriginalStudyYear()
                + "\n\tduration = " + candidate.getEducation().getDuration()
                + "\n\tdate of adding = " + dateToLocalDate(candidate.getDateOfAdding()).toString();
    }

    private LocalDate dateToLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}