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
import ro.msg.cm.pojo.Duplicate;
import ro.msg.cm.processor.LinkMapper;
import ro.msg.cm.repository.CandidateRepository;
import ro.msg.cm.service.CandidateService;
import ro.msg.cm.service.DuplicateFinderService;
import ro.msg.cm.types.CandidateCheck;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/candidate-validation")
public class CandidateValidationController {

    private final CandidateService validationService;
    private final CandidateRepository candidateRepository;
    private final DuplicateFinderService duplicateFinderService;
    private final LinkMapper linkMapper;

    @Autowired
    public CandidateValidationController(CandidateService validationService, DuplicateFinderService duplicateFinderService,
                                         LinkMapper linkMapper, CandidateRepository candidateRepository) {
        this.validationService = validationService;
        this.duplicateFinderService = duplicateFinderService;
        this.linkMapper = linkMapper;
        this.candidateRepository = candidateRepository;
    }

    @GetMapping
    public Resources<Resource<Candidate>> getAllNotYetValidatedCandidates() {
        return linkMapper.candidateListToResource(candidateRepository.findAllByCheckCandidate(
                CandidateCheck.NOT_YET_VALIDATED));
    }

    @PostMapping("/add-candidate")
    @ResponseStatus(HttpStatus.CREATED)
    public Resource<Candidate> saveUnvalidatedCandidate(@RequestBody Candidate candidate) {
        Candidate candidateSaved = validationService.saveUnvalidatedCandidate(candidate);
        return linkMapper.candidateToResource(candidateSaved);
    }

    @PostMapping("/add-candidates")
    @ResponseStatus(HttpStatus.CREATED)
    public Resources<Resource<Candidate>> saveUnvalidatedCandidates(@RequestBody List<Candidate> candidates) {
        return linkMapper.candidateListToResource(validationService.saveUnvalidatedCandidates(candidates));
    }

    @GetMapping("/{id}")
    public Resource<Candidate> getOneCandidate(@PathVariable Long id) {
        return linkMapper.candidateToResource(
                candidateRepository.findByIdAndCheckCandidate(id, CandidateCheck.NOT_YET_VALIDATED));
    }

    @GetMapping("/{id}/education")
    public Resource<Education> getEducation(@PathVariable Long id) {
        Candidate candidate = candidateRepository.findByIdAndCheckCandidate(id, CandidateCheck.NOT_YET_VALIDATED);
        if (candidate != null) {
            return linkMapper.educationToResource(candidate.getEducation());
        } else {
            throw new EntityNotFoundException();
        }
    }

    @GetMapping("/{id}/candidateNotesList")
    public Resources<Resource<CandidateNotes>> getCandidateNotesList(@PathVariable Long id) {
        Candidate candidate = candidateRepository.findByIdAndCheckCandidate(id, CandidateCheck.NOT_YET_VALIDATED);
        if (candidate != null) {
            return linkMapper.candidateNotesListToResource(candidate.getCandidateNotesList());
        } else {
            throw new EntityNotFoundException();
        }
    }

    @GetMapping("/{id}/candidateSkillsList")
    public Resources<Resource<CandidateSkills>> getCandidateSkillsList(@PathVariable Long id) {
        Candidate candidate = candidateRepository.findByIdAndCheckCandidate(id, CandidateCheck.NOT_YET_VALIDATED);
        if (candidate != null) {
            return linkMapper.candidateSkillsListToResource(candidate.getCandidateSkillsList());
        } else {
            throw new EntityNotFoundException();
        }
    }

    @PutMapping("/{id}")
    public void validateCandidate(@PathVariable Long id){
        validationService.validate(id);
    }

    @PutMapping("/{id}/update")
    public Candidate updateCandidate(@PathVariable Long id, @RequestBody CandidateDto candidate) {
        return validationService.updateCandidate(candidate, id);
    }

    @PutMapping()
    public void validateCandidates(@RequestBody List<Long> ids) {
        validationService.validate(ids);
    }

    @DeleteMapping()
    public void deleteCandidatesWithIds(@RequestBody List<Long> ids) {
        validationService.deleteSelectedEntries(ids);
    }

    @GetMapping("/duplicates-on-valid/{id}")
    public Resources<Duplicate> getValidDuplicates(@PathVariable Long id) {
        return linkMapper.duplicateListToResourceForValidAndNonValid(id,
                duplicateFinderService.getDuplicates(id, CandidateCheck.VALIDATED), true);
    }

    @GetMapping("/duplicates-on-non-valid/{id}")
    public Resources<Duplicate> getNonValidDuplicates(@PathVariable Long id) {
        return linkMapper.duplicateListToResourceForValidAndNonValid(id,
                duplicateFinderService.getDuplicates(id, CandidateCheck.NOT_YET_VALIDATED), false);
    }
}