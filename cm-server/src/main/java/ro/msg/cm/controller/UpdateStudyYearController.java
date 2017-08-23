package ro.msg.cm.controller;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.cm.model.Candidate;
import ro.msg.cm.repository.CandidateRepository;
import ro.msg.cm.util.UpdateUniversityYearUtils;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class UpdateStudyYearController {

    private final CandidateRepository candidateRepository;
    private final BeanFactory beanFactory;

    @Autowired
    public UpdateStudyYearController(CandidateRepository candidateRepository, BeanFactory beanFactory) {
        this.candidateRepository = candidateRepository;
        this.beanFactory = beanFactory;
    }

    @RequestMapping(value = "/candidates", method = RequestMethod.GET, produces = "application/hal+json")
    public Resources updateUniversityYear() throws ParseException {
        UpdateUniversityYearUtils updateUniversityYearUtils = beanFactory.getBean(UpdateUniversityYearUtils.class);
        Iterable<Candidate> candidates = candidateRepository.findAll();
        List<Resource<Candidate>> resources = new LinkedList<>();
        for (Candidate candidate : candidates) {
            candidate.setCurrentStudyYear(updateUniversityYearUtils.determineYearBasedOnDuration(candidate));
            resources.add(getResource(candidate));
        }
        return new Resources<>(resources);

    }

    @RequestMapping(value = "candidates/{id}", method = RequestMethod.GET, produces = "application/hal+json")
    public Resource getResourceCandidateById(@PathVariable(value = "id") Long id) throws ParseException {

        Candidate candidate = candidateRepository.findOne(id);
        Resource<Candidate> resource = getResource(candidate);
        resource.add(linkTo(methodOn(UpdateStudyYearController.class).getResourceCandidateById(id)).withRel("candidate"));
        return resource;
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
}

