package ro.msg.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.msg.cm.model.Candidate;
import ro.msg.cm.model.CandidateSkills;
import ro.msg.cm.model.CandidateSkillsJson;
import ro.msg.cm.model.Tag;
import ro.msg.cm.processor.LinkMapper;
import ro.msg.cm.repository.CandidateRepository;
import ro.msg.cm.repository.CandidateSkillsRepository;
import ro.msg.cm.repository.TagRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/candidateSkills")
public class CandidateSkillsController {

    private final CandidateSkillsRepository candidateSkillsRepository;
    private final CandidateRepository candidateRepository;
    private final TagRepository tagRepository;
    private final LinkMapper linkMapper;

    @Autowired
    public CandidateSkillsController(CandidateSkillsRepository candidateSkillsRepository, CandidateRepository candidateRepository, TagRepository tagRepository, LinkMapper linkMapper) {
        this.candidateSkillsRepository = candidateSkillsRepository;
        this.candidateRepository = candidateRepository;
        this.tagRepository = tagRepository;
        this.linkMapper = linkMapper;
    }

    @GetMapping("/{id}")
    public Resource<CandidateSkills> getCandidateSkills(@PathVariable Long id) {
        return linkMapper.candidateSkillsToResource(candidateSkillsRepository.findOne(id));
    }

    @GetMapping
    public Resources<Resource<CandidateSkills>> getCandidateSkillsList() {
        return linkMapper.candidateSkillsListToResource(candidateSkillsRepository.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Resource<CandidateSkills> postCandidateSkills(@RequestBody CandidateSkillsJson candidateSkillsJson) {
        return linkMapper.candidateSkillsToResource(candidateSkillsRepository.save(getCandidateSkillsFromCandidateSkillsJson(candidateSkillsJson)));
    }

    @PostMapping("/multiple")
    @ResponseStatus(HttpStatus.CREATED)
    public Resources<Resource<CandidateSkills>> postCandidateSkillsList(@RequestBody List<CandidateSkillsJson> candidateSkillsJsonList) {
        return linkMapper.candidateSkillsListToResource((List<CandidateSkills>) candidateSkillsRepository.save(candidateSkillsJsonList.stream().map(this::getCandidateSkillsFromCandidateSkillsJson).collect(Collectors.toList())));
    }

    @PutMapping("/{id}")
    public Resource<CandidateSkills> putCandidateSkills(@PathVariable Long id, @RequestBody CandidateSkillsJson candidateSkillsJson) {
        candidateSkillsJson.setId(id);
        return linkMapper.candidateSkillsToResource(candidateSkillsRepository.save(getCandidateSkillsFromCandidateSkillsJson(candidateSkillsJson)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCandidateSkills(@PathVariable Long id) {
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

    @GetMapping("/{id}/tag")
    public Resource<Tag> getCandidateSkillsTag(@PathVariable Long id) {
        CandidateSkills candidateSkills = candidateSkillsRepository.findOne(id);
        if (candidateSkills != null) {
            return linkMapper.tagToResource(candidateSkills.getTag());
        } else {
            throw new EntityNotFoundException();
        }
    }

    @GetMapping("/{id}/candidate")
    public Resource<Candidate> getCandidateSkillsCandidate(@PathVariable Long id) {
        CandidateSkills candidateSkills = candidateSkillsRepository.findOne(id);
        if (candidateSkills != null) {
            return linkMapper.candidateToResource(candidateSkills.getCandidate());
        } else {
            throw new EntityNotFoundException();
        }
    }
}
