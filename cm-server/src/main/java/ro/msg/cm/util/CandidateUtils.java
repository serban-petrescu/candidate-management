package ro.msg.cm.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.msg.cm.model.Candidate;
import ro.msg.cm.model.StartYearProperties;

import javax.validation.ValidationException;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CandidateUtils {
    private final String startYearDate;

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
        if (candidate.getEducation() != null && currentStudyYear > candidate.getEducation().getDuration()) {
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
        LocalDate today = getToday();
        LocalDate dateOfAdding = candidate.getDateOfAdding();
        LocalDate firstNewUniversityYear = getNewUniversityDateOfYear(dateOfAdding.getYear());
        int originalStudyYear = candidate.getOriginalStudyYear();
        Period diffYears = Period.between(dateOfAdding, today);
        originalStudyYear += diffYears.getYears();
        LocalDate remainder = addPeriodToDate(dateOfAdding, diffYears);
        if (dateOfAdding.isBefore(firstNewUniversityYear) && remainder.compareTo(firstNewUniversityYear) >= 0) {
            originalStudyYear += 1;
        }
        return originalStudyYear;
    }

    /**
     * Method that adds months and days from a Period <b>adder</b> to a LocalDate <b>base</b>
     *
     * @param base  LocalDate
     * @param adder Period
     * @return LocalDate - The new LocalDate
     */
    private LocalDate addPeriodToDate(LocalDate base, Period adder) {
        return base.plus(adder.getDays(), ChronoUnit.DAYS).plus(adder.getMonths(), ChronoUnit.MONTHS);
    }

    /**
     * Method that return a LocalDate that represents the starting of the new university year LocalDate based on the given <p>year</p>
     *
     * @param year int
     * @return LocalDate - new university year LocalDate based on the given <p>year</p>
     */
    private LocalDate getNewUniversityDateOfYear(int year) {
        Pattern pattern = Pattern.compile("(\\d{1,2})[-](\\d{1,2})");
        Matcher matcher = pattern.matcher(startYearDate);
        if (matcher.find()) {
            int month = Integer.parseInt(matcher.group(1));
            int day = Integer.parseInt(matcher.group(2));
            return LocalDate.of(year, month, day);
        }
        throw new ValidationException("The startYearDate was not of the form MM-dd");
    }

    /**
     * returns LocalDate for today
     *
     * @return LocalDate - returns the date for today
     */
    protected LocalDate getToday() {
        return LocalDate.now();
    }
}
