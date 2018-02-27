package ro.msg.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.msg.cm.model.Education;
import ro.msg.cm.repository.EducationRepository;

import java.util.List;

@RestController
@RequestMapping("/api/education")
public class EducationController {

    private final EducationRepository educationRepository;

    @Autowired
    public EducationController(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    @GetMapping("/{id}")
    public Education getEducation(@PathVariable long id) {
        return educationRepository.findOne(id);
    }

    @GetMapping
    public List<Education> getEducationList() {
        return educationRepository.findAll();
    }

    @PostMapping
    public Education postEducation(@RequestBody Education education) {
        return educationRepository.save(education);
    }

    @PostMapping("/multiple")
    public Iterable<Education> postEducationList(@RequestBody List<Education> educationList) {
        return educationRepository.save(educationList);
    }

    @PutMapping("/{id}")
    public Education putEducation(@PathVariable long id, @RequestBody Education education) {
        education.setId(id);
        return educationRepository.save(education);
    }

    @DeleteMapping("/{id}")
    public void deleteEducation(@PathVariable long id) {
        educationRepository.delete(id);
    }

}
