package ro.msg.cm.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ro.msg.cm.model.Candidate;
import ro.msg.cm.model.StartYearProperties;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static java.util.Calendar.YEAR;

@Component
@Scope(value = "prototype")
public class UpdateUniversityYearUtils {

    private final Date today = new Date();
    private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private final StartYearProperties startYearProperties;

    @Autowired
    public UpdateUniversityYearUtils(StartYearProperties startYearProperties) {
        this.startYearProperties = startYearProperties;
    }

    /**
     * Method that calculate the current study year based on the duration of study
     *
     * @param candidate Candidate
     * @return int - the current study year
     * @throws ParseException indicates if errors occurred
     */
    public int determineYearBasedOnDuration(Candidate candidate) throws ParseException {
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
     * @throws ParseException indicates if errors occurred
     */
    private int calculateStudyYear(Candidate candidate) throws ParseException {

        int originalStudyYear = candidate.getOriginalStudyYear();
        int diffYears = getDiffYears(candidate.getDateOfAdding(), today);

        if (diffYears > 0 && (getMonthOfAddingYear(candidate) < getMonthOfStartingUniversityYear())) {
            return diffYears + originalStudyYear;
        }

        if (diffYears > 0 && (getMonthOfAddingYear(candidate) >= (getMonthOfStartingUniversityYear()))) {
            return (diffYears - 1) + originalStudyYear;
        }

        if (diffYears == 0 && (getMonthOfAddingYear(candidate) < getMonthOfStartingUniversityYear() &&
                getMonthOfStartingUniversityYear() < getMonthOfCurrentYear())) {
            return ++originalStudyYear;
        }
        return originalStudyYear;
    }

    private int getDurationOfStudy(Candidate candidate) {
        return candidate.getEducation().getDuration();
    }

    private int getMonthOfStartingUniversityYear() throws ParseException {
        Date startYearDate = appendCurrentYearToMonthDayFormatDate();
        return calculateCalendarMonth(startYearDate);
    }

    private int getMonthOfCurrentYear() {
        return calculateCalendarMonth(today);
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

    private Date appendCurrentYearToMonthDayFormatDate() throws ParseException {
        int year = Calendar.getInstance().get(YEAR);
        String yearString = Integer.toString(year);
        String startYearDate = getStartYearDateFromConfigurationFile();
        String yearStringTotal = yearString + "-" + startYearDate;
        return df.parse(yearStringTotal);

    }

    private String getStartYearDateFromConfigurationFile() {
        return startYearProperties.getStartYearDate();
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

}
