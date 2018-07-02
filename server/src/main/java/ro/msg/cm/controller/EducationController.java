package ro.msg.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.msg.cm.model.Education;
import ro.msg.cm.processor.LinkMapper;
import ro.msg.cm.repository.EducationRepository;

import java.util.List;

@RestController
@RequestMapping("/api/educations")
public class EducationController {

    private final EducationRepository educationRepository;
    private final LinkMapper linkMapper;

    @Autowired
    public EducationController(EducationRepository educationRepository, LinkMapper linkMapper) {
        this.educationRepository = educationRepository;
        this.linkMapper = linkMapper;
    }

    @GetMapping("/{id}")
    public Resource<Education> getEducation(@PathVariable Long id) {
        return linkMapper.educationToResource(educationRepository.findOne(id));
    }

    @GetMapping
    public Resources<Resource<Education>> getEducationList() {
        return linkMapper.educationListToResource(educationRepository.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Resource<Education> postEducation(@RequestBody Education education) {
        return linkMapper.educationToResource(educationRepository.save(education));
    }

    @PostMapping("/multiple")
    @ResponseStatus(HttpStatus.CREATED)
    public Resources<Resource<Education>> postEducationList(@RequestBody List<Education> educationList) {
        return linkMapper.educationListToResource((List<Education>) educationRepository.save(educationList));
    }

    @PutMapping("/{id}")
    public Resource<Education> putEducation(@PathVariable Long id, @RequestBody Education education) {
        education.setId(id);
        return linkMapper.educationToResource(educationRepository.save(education));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEducation(@PathVariable Long id) {
        educationRepository.delete(id);
    }

}
