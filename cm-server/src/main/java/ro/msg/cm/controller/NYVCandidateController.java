package ro.msg.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.msg.cm.dto.CandidateDto;
import ro.msg.cm.model.Candidate;
import ro.msg.cm.model.CandidateNotes;
import ro.msg.cm.model.CandidateSkills;
import ro.msg.cm.model.Education;
import ro.msg.cm.processor.LinkMapper;
import ro.msg.cm.repository.CandidateRepository;
import ro.msg.cm.service.CandidateService;
import ro.msg.cm.types.CandidateCheck;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/not-yet-validated-candidates")
public class NYVCandidateController {
    private final CandidateRepository candidateRepository;
    private final CandidateService validationService;
    private final LinkMapper linkMapper;

    @Autowired
    public NYVCandidateController(CandidateRepository candidateRepository, LinkMapper linkMapper, CandidateService validationService) {
        this.candidateRepository = candidateRepository;
        this.linkMapper = linkMapper;
        this.validationService = validationService;
    }

    @GetMapping("/{id}")
    public Resource<Candidate> getOneCandidate(@PathVariable long id) {
        return linkMapper.candidateToResource(candidateRepository.findByIdAndCheckCandidate(id, CandidateCheck.NOT_YET_VALIDATED));
    }

    @GetMapping
    public Resources<Resource<Candidate>> getAllNotYetValidatedCandidates() {
        return linkMapper.candidateListToResourceForValidAndNonValid(candidateRepository.findAllByCheckCandidate(CandidateCheck.NOT_YET_VALIDATED), false);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Resource<Candidate> saveCandidate(@RequestBody Candidate candidate) {
        candidate.setCheckCandidate(CandidateCheck.NOT_YET_VALIDATED);
        return linkMapper.candidateToResource(candidateRepository.save(candidate));
    }

    @PostMapping("/multiple")
    @ResponseStatus(HttpStatus.CREATED)
    public Resources<Resource<Candidate>> saveCandidates(@RequestBody List<Candidate> candidates) {
        candidates.forEach(x -> x.setCheckCandidate(CandidateCheck.NOT_YET_VALIDATED));
        return linkMapper.candidateListToResource((List<Candidate>) candidateRepository.save(candidates));
    }

    @PutMapping("/{id}")
    public Resource<Candidate> putCandidate(@PathVariable long id, @RequestBody Candidate candidate) {
        if (candidateRepository.findByIdAndCheckCandidate(id, CandidateCheck.NOT_YET_VALIDATED) != null) {
            candidate.setCheckCandidate(CandidateCheck.NOT_YET_VALIDATED);
            candidate.setId(id);
            return linkMapper.candidateToResource(candidateRepository.save(candidate));
        } else {
            throw new EntityNotFoundException();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCandidateWithId(@PathVariable Long id) {
        if (candidateRepository.findByIdAndCheckCandidate(id, CandidateCheck.NOT_YET_VALIDATED) != null) {
            candidateRepository.delete(id);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @GetMapping("/{id}/education")
    public Resource<Education> getEducation(@PathVariable long id) {
        Candidate candidate = candidateRepository.findByIdAndCheckCandidate(id, CandidateCheck.NOT_YET_VALIDATED);
        if (candidate != null) {
            return linkMapper.educationToResource(candidate.getEducation());
        } else {
            throw new EntityNotFoundException();
        }
    }

    @GetMapping("/{id}/candidateNotesList")
    public Resources<Resource<CandidateNotes>> getCandidateNotesList(@PathVariable long id) {
        Candidate candidate = candidateRepository.findByIdAndCheckCandidate(id, CandidateCheck.NOT_YET_VALIDATED);
        if (candidate != null) {
            return linkMapper.candidateNotesListToResource(candidate.getCandidateNotesList());
        } else {
            throw new EntityNotFoundException();
        }
    }

    @GetMapping("/{id}/candidateSkillsList")
    public Resources<Resource<CandidateSkills>> getCandidateSkillsList(@PathVariable long id) {
        Candidate candidate = candidateRepository.findByIdAndCheckCandidate(id, CandidateCheck.NOT_YET_VALIDATED);
        if (candidate != null) {
            return linkMapper.candidateSkillsListToResource(candidate.getCandidateSkillsList());
        } else {
            throw new EntityNotFoundException();
        }
    }

    @PatchMapping("/{id}")
    public Resource<Candidate> patchCandidate(@RequestBody CandidateDto toPatch, @PathVariable Long id) {
        Candidate candidatePatched = validationService.patchCandidate(toPatch, id);
        return linkMapper.candidateToResource(candidatePatched);
    }

    @PutMapping("/{id}/validate")
    public void validateCandidate(@PathVariable Long id) {
        validationService.validate(id);
    }
}

