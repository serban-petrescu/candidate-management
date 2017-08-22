package ro.msg.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.cm.model.Candidate;
import ro.msg.cm.model.StartYearProperties;
import ro.msg.cm.repository.CandidateRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.Calendar.YEAR;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class UpdateStudyYearController {
    private final CandidateRepository candidateRepository;
    private final StartYearProperties startYearProperties;

    @Autowired
    public UpdateStudyYearController(CandidateRepository candidateRepository, StartYearProperties startYearProperties) {
        this.candidateRepository = candidateRepository;
        this.startYearProperties = startYearProperties;
    }

    @RequestMapping(value = "/candidates", method = RequestMethod.GET, produces = "application/hal+json")
    public Resources updateUniversityYear() throws ParseException {

        Iterable<Candidate> candidates = findAll();
        List<Candidate> candidateList = new ArrayList<>();
        candidates.forEach(candidateList::add);
        List<Resource<Candidate>> resources = new LinkedList<>();
        for (Candidate candidate : candidates) {
            candidate.setCurrentStudyYear(determineYearBasedOnDuration(candidate));
            resources = candidateToResource(candidateList);
        }
        return new Resources<>(resources);

    }

    private Resource<Candidate> getResource(Candidate candidate) throws ParseException {

        Resource<Candidate> resource = new Resource<>(candidate);
        resource.add(linkTo(methodOn(UpdateStudyYearController.class).getResourceCandidateById(candidate.getId())).withSelfRel());
        resource.add(linkTo(methodOn(UpdateStudyYearController.class).getResourceCandidateById(candidate.getId())).withRel("candidate"));
        resource.add(linkTo(methodOn(CandidateSkillsController.class).getCandidateSkills(candidate.getId())).withRel("candidateSkillsList"));
        resource.add(linkTo(methodOn(EducationController.class).getEducation(candidate.getId())).withRel("education"));
        resource.add(linkTo(methodOn(CandidateNotesController.class).getCandidateNotes(candidate.getId())).withRel("candidateNotesList"));

        return resource;
    }

    private List<Resource<Candidate>> candidateToResource(List<Candidate> candidates) throws ParseException {
        List<Resource<Candidate>> resources = new ArrayList<>(candidates.size());
        for (Candidate candidate : candidates) {
            Resource<Candidate> resource = getResource(candidate);
            resources.add(resource);
        }
        return resources;
    }

    @RequestMapping(value = "candidates/{id}", method = RequestMethod.GET, produces = "application/hal+json")
    public Resource getResourceCandidateById(@PathVariable(value = "id") Long id) throws ParseException {

        Candidate candidate = candidateRepository.findOne(id);
        Resource<Candidate> resource = new Resource<>(candidate);
        resource.add(linkTo(methodOn(UpdateStudyYearController.class).getResourceCandidateById(id)).withRel("candidate"));
        return resource;
    }

    private Iterable<Candidate> findAll() {
            return candidateRepository.findAll();
    }

    /**
     * Method that calculate the current study year based on the duration of study
     *
     * @param candidate Candidate
     * @return int - the current study year
     * @throws ParseException indicates if errors occurred
     */
    private int determineYearBasedOnDuration(Candidate candidate) throws ParseException {
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
        Date currentDate = new Date();

        int originalStudyYear = candidate.getOriginalStudyYear();
        int diffYears = getDiffYears(candidate.getDateOfAdding(), currentDate);

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
        String startYearDate = getStartYearDateFromConfigurationFile();
        String yearStringTotal = yearString + "-" + startYearDate;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
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
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }

}

