package ro.msg.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.msg.cm.model.CandidateSkills;
import ro.msg.cm.model.CandidateSkillsJson;
import ro.msg.cm.repository.CandidateRepository;
import ro.msg.cm.repository.CandidateSkillsRepository;
import ro.msg.cm.repository.TagRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/candidateSkills")
public class CandidateSkillsController {

    private final CandidateSkillsRepository candidateSkillsRepository;
    private final CandidateRepository candidateRepository;
    private final TagRepository tagRepository;

    @Autowired
    public CandidateSkillsController(CandidateSkillsRepository candidateSkillsRepository, CandidateRepository candidateRepository, TagRepository tagRepository) {
        this.candidateSkillsRepository = candidateSkillsRepository;
        this.candidateRepository = candidateRepository;
        this.tagRepository = tagRepository;
    }

    @GetMapping("/{id}")
    public CandidateSkillsJson getCandidateSkills(@PathVariable long id) {
        return new CandidateSkillsJson(candidateSkillsRepository.findOne(id));
    }

    @GetMapping
    public List<CandidateSkillsJson> getCandidateSkillsList() {
        return candidateSkillsRepository.findAll().stream().map(CandidateSkillsJson::new).collect(Collectors.toList());
    }

    @PostMapping
    public CandidateSkillsJson postCandidateSkills(@RequestBody CandidateSkillsJson candidateSkillsJson) {
        return new CandidateSkillsJson(candidateSkillsRepository.save(getCandidateSkillsFromCandidateSkillsJson(candidateSkillsJson)));
    }

    @PostMapping("/multiple")
    public Iterable<CandidateSkillsJson> postCandidateSkillsList(@RequestBody List<CandidateSkillsJson> candidateSkillsJsonList) {
        Iterable<CandidateSkills> saved = candidateSkillsRepository.save(candidateSkillsJsonList.stream().map(this::getCandidateSkillsFromCandidateSkillsJson).collect(Collectors.toList()));
        List<CandidateSkillsJson> newCandidateSkillsJsonList = new ArrayList<>();
        for (CandidateSkills candidateSkills : saved) {
            newCandidateSkillsJsonList.add(new CandidateSkillsJson(candidateSkills));
        }
        return newCandidateSkillsJsonList;
    }

    @PutMapping("/{id}")
    public CandidateSkillsJson putCandidateSkills(@PathVariable long id, @RequestBody CandidateSkillsJson candidateSkillsJson) {
        candidateSkillsJson.setId(id);
        return new CandidateSkillsJson(candidateSkillsRepository.save(getCandidateSkillsFromCandidateSkillsJson(candidateSkillsJson)));
    }

    @DeleteMapping("/{id}")
    public void deleteCandidateSkills(@PathVariable long id) {
        candidateSkillsRepository.delete(id);
    }

    private CandidateSkills getCandidateSkillsFromCandidateSkillsJson(CandidateSkillsJson candidateSkillsJson) {
        CandidateSkills candidateSkills = new CandidateSkills();
        candidateSkills.setId(candidateSkillsJson.getId());
        candidateSkills.setCandidate(candidateRepository.findOne(candidateSkillsJson.getCandidateId()));
        candidateSkills.setTag(tagRepository.findOne(candidateSkillsJson.getTagId()));
        candidateSkills.setCertifier(candidateSkillsJson.getCertifier());
        candidateSkills.setRating(candidateSkillsJson.getRating());
        return candidateSkills;
    }
}
