package ro.msg.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.cm.model.Candidate;
import ro.msg.cm.model.StartYearProperties;
import ro.msg.cm.repository.CandidateRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.util.Calendar.YEAR;

@RestController
@RequestMapping("/api/update")
public class UpdateStudyYearController {
    private final CandidateRepository candidateRepository;
    private final StartYearProperties startYearProperties;

    @Autowired
    public UpdateStudyYearController(CandidateRepository candidateRepository, StartYearProperties startYearProperties) {
        this.candidateRepository = candidateRepository;
        this.startYearProperties = startYearProperties;

    }

    @RequestMapping(value = "/universityYear", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

    public Iterable<Candidate> updateUniversityYear() throws ParseException {
        Iterable<Candidate> candidates = findAll();

        for (Candidate candidate : candidates) {
            candidate.setCurrentStudyYear(determineYearBasedOnDuration(candidate));
        }

        return candidates;
    }

    private Iterable<Candidate> findAll() {
        return candidateRepository.findAll();
    }

    private int calculateStudyYear(Candidate candidate) throws ParseException {
        Date currentDate = new Date();

        int currentStudyYear;

        int originalStudyYear = candidate.getOriginalStudyYear();
        int diffYears = getDiffYears(candidate.getDateOfAdding(), currentDate);

        if (diffYears > 0 && (getMonthOfAddingYear(candidate) < (getMonthOfStartingUniversityYear()))) {
            currentStudyYear = diffYears + originalStudyYear;
        } else if (diffYears > 0 && (getMonthOfAddingYear(candidate) >= (getMonthOfStartingUniversityYear()))) {
            currentStudyYear = (diffYears - 1) + originalStudyYear;
        } else if (diffYears == 0 && (getMonthOfAddingYear(candidate) < getMonthOfStartingUniversityYear() &&
                getMonthOfStartingUniversityYear() < getMonthOfCurrentYear())) {
            currentStudyYear = ++originalStudyYear;
        } else {
            currentStudyYear = candidate.getOriginalStudyYear();
        }
        return currentStudyYear;

    }

    private int determineYearBasedOnDuration(Candidate candidate) throws ParseException {
        int currentStudyYear = calculateStudyYear(candidate);
        if (candidate.getEducation() != null) {
            if (currentStudyYear > getDurationOfStudy(candidate)) {
                currentStudyYear = -1;
            }
        }
        return currentStudyYear;
    }

    private int getDurationOfStudy(Candidate candidate) {
        return candidate.getEducation().getDuration();
    }

    private int getMonthOfStartingUniversityYear() throws ParseException {
        Date startYearDate = appendCurrentYearToMonthDayFormatDate();
        return calculateCalendarMonth(startYearDate);
    }

    private int getMonthOfCurrentYear() {
        Date currentDate = new Date();
        return calculateCalendarMonth(currentDate);
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
        SimpleDateFormat dateformatMMDD = new SimpleDateFormat("MM-dd");
        Date startYearDate = getStartYearDateFromConfigurationFile();
        StringBuilder nowYYYYMMDD = new StringBuilder(dateformatMMDD.format(startYearDate));
        String yearStringTotal = yearString + "-" + nowYYYYMMDD;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.parse(yearStringTotal);

    }

    private Date getStartYearDateFromConfigurationFile() {
        return startYearProperties.getStartYearDate();
    }

    private int getDiffYears(Date first, Date last) {
        Calendar currentDate = getCalendar(first);
        Calendar dateOfAdding = getCalendar(last);
        return dateOfAdding.get(YEAR) - currentDate.get(YEAR);
    }

    private static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }

}
