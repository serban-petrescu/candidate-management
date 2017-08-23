package ro.msg.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.msg.cm.model.CandidateSkills;
import ro.msg.cm.repository.CandidateSkillsRepository;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RequestMapping("/api")

@Controller
public class CandidateSkillsController {

    private CandidateSkillsRepository candidateSkillsRepository;

    @Autowired
    public CandidateSkillsController(CandidateSkillsRepository candidateSkillsRepository) {
        this.candidateSkillsRepository = candidateSkillsRepository;
    }

    @RequestMapping(value = "/candidates/{id}/candidateSkillsList", method = RequestMethod.GET, produces = "application/hal+json")
    @ResponseBody
    public Resource getCandidateSkills(@PathVariable(value = "id") Long id) {
        CandidateSkills a = candidateSkillsRepository.findOne(id);
        Resource<CandidateSkills> resource = new Resource<>(a);
        resource.add(linkTo(methodOn(CandidateSkillsController.class).getCandidateSkills(id)).withRel("candidateSkillsList"));
        return resource;
    }

}
