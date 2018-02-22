package ro.msg.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.msg.cm.model.CandidateSkills;
import ro.msg.cm.repository.CandidateSkillsRepository;

import java.util.List;

@RestController
@RequestMapping("/api/candidateSkills")
public class CandidateSkillsController {

    private final CandidateSkillsRepository candidateSkillsRepository;

    @Autowired
    public CandidateSkillsController(CandidateSkillsRepository candidateSkillsRepository) {
        this.candidateSkillsRepository = candidateSkillsRepository;
    }

    @GetMapping("/{id}")
    public CandidateSkills getCandidateSkills(@PathVariable long id) {
        return candidateSkillsRepository.findOne(id);
    }

    @GetMapping
    public List<CandidateSkills> getCandidateSkillsList() {
        return candidateSkillsRepository.findAll();
    }

    @PostMapping
    public CandidateSkills postCandidateSkills(@RequestBody CandidateSkills candidateSkills) {
        return candidateSkillsRepository.save(candidateSkills);
    }

    @PostMapping("/multiple")
    public List<CandidateSkills> postCandidateSkillsList(@RequestBody List<CandidateSkills> candidateSkillsList) {
        return candidateSkillsRepository.save(candidateSkillsList);
    }

    @PutMapping("/{id}")
    public CandidateSkills putCandidateSkills(@PathVariable long id, @RequestBody CandidateSkills candidateSkills) {
        candidateSkills.setId(id);
        return candidateSkillsRepository.save(candidateSkills);
    }

    @DeleteMapping("/{id}")
    public void deleteCandidateSkills(@PathVariable long id) {
        candidateSkillsRepository.delete(id);
    }
}
