package ro.msg.cm.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.msg.cm.model.Candidate;
import ro.msg.cm.model.StartYearProperties;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static java.util.Calendar.YEAR;

@Component
public class CandidateUtils {
    private final String startYearDate;
    private Date givenDate;

    @Autowired
    public CandidateUtils(StartYearProperties startYearProperties) {
        this.startYearDate = startYearProperties.getStartYearDate();
    }

    /**
     * Method that calculate the current study year based on the duration of study
     *
     * @param candidate Candidate
     * @return int - the current study year
     */
    public int determineYearBasedOnDuration(Candidate candidate) {
        int currentStudyYear = calculateStudyYear(candidate);
        if (candidate.getEducation() != null && currentStudyYear > getDurationOfStudy(candidate)) {
            currentStudyYear = -1;
        }
        return currentStudyYear;
    }

    /**
     * Method that retrieves the current study year for a given candidate
     *
     * @param candidate Candidate
     * @return int - The year of study reported on today
     */
    private int calculateStudyYear(Candidate candidate) {
        Date today = (this.givenDate == null) ? new Date() : this.givenDate;
        int originalStudyYear = candidate.getOriginalStudyYear();
        int diffYears = getDiffYears(candidate.getDateOfAdding(), today);

        if (diffYears > 0 && (getMonthOfAddingYear(candidate) < getMonthOfStartingUniversityYear())) {
            return diffYears + originalStudyYear;
        }

        if (diffYears > 0 && (getMonthOfAddingYear(candidate) >= (getMonthOfStartingUniversityYear()))) {
            return (diffYears - 1) + originalStudyYear;
        }

        if (diffYears == 0 && (getMonthOfAddingYear(candidate) < getMonthOfStartingUniversityYear() &&
                getMonthOfStartingUniversityYear() < calculateCalendarMonth(today))) {
            return originalStudyYear + 1;
        }
        return originalStudyYear;
    }

    private int getDurationOfStudy(Candidate candidate) {
        return candidate.getEducation().getDuration();
    }

    private int getMonthOfStartingUniversityYear() {
        Date startYearDate = appendCurrentYearToMonthDayFormatDate();
        return calculateCalendarMonth(startYearDate);
    }

    private int getMonthOfAddingYear(Candidate candidate) {
        Date addingDate = candidate.getDateOfAdding();
        return calculateCalendarMonth(addingDate);
    }

    private int calculateCalendarMonth(Date addingDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(addingDate);
        return calendar.get(Calendar.MONTH) + 1;
    }

    private Date appendCurrentYearToMonthDayFormatDate() {
        String dateAsString = Integer.toString(Calendar.getInstance().get(YEAR)) + "-" + startYearDate;
        return Date.from(LocalDate.parse(dateAsString).atStartOfDay().toInstant(ZoneOffset.UTC));
    }

    private int getDiffYears(Date first, Date last) {
        Calendar currentDate = getCalendar(first);
        Calendar dateOfAdding = getCalendar(last);
        return dateOfAdding.get(YEAR) - currentDate.get(YEAR);
    }

    private Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal.setTime(date);
        return cal;
    }

    public void setGivenDate(Date givenDate) {
        this.givenDate = givenDate;
    }
}
