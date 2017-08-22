package ro.msg.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.msg.cm.model.Education;
import ro.msg.cm.repository.EducationRepository;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RequestMapping("/api")
@Controller
public class EducationController {
    private EducationRepository educationRepository;

    @Autowired
    public EducationController(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    @RequestMapping(value = "/candidates/{id}/education", method = RequestMethod.GET, produces = "application/hal+json")
    @ResponseBody
    public Resource getEducation(@PathVariable(value = "id") Long id) {
        Education education = educationRepository.findOne(id);
        Resource<Education> resource = new Resource<>(education);
        resource.add(linkTo(methodOn(EducationController.class).getEducation(id)).withRel("education"));
        return resource;
    }
}
