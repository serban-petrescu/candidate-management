package ro.msg.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.msg.cm.dto.CandidateDto;
import ro.msg.cm.model.Candidate;
import ro.msg.cm.pojo.Duplicate;
import ro.msg.cm.processor.LinkMapper;
import ro.msg.cm.service.CandidateService;
import ro.msg.cm.service.DuplicateFinderService;
import ro.msg.cm.types.CandidateCheck;

import java.util.List;

@RestController
@RequestMapping("/api/candidate-validation")
public class CandidateValidationController {

    private final CandidateService validationService;
    private final DuplicateFinderService duplicateFinderService;
    private final LinkMapper linkMapper;

    @Autowired
    public CandidateValidationController(CandidateService validationService, DuplicateFinderService duplicateFinderService,LinkMapper linkMapper) {
        this.validationService = validationService;
        this.duplicateFinderService = duplicateFinderService;
        this.linkMapper = linkMapper;
    }

    @PatchMapping("/update-candidate/{id}")
    public Resource<Candidate> patchCandidate(@RequestBody CandidateDto toPatch, @PathVariable Long id) {
        Candidate candidatePatched = validationService.patchCandidate(toPatch, id);
        return linkMapper.candidateToResource(candidatePatched);
    }

    @DeleteMapping("/delete-candidate/{id}")
    public void deleteCandidate(@PathVariable Long id){
        validationService.deleteSelectedEntry(id);
    }

    @DeleteMapping("/delete-candidates/{ids}")
    public void deleteCandidate(@PathVariable List<Long> ids){
        validationService.deleteSelectedEntries(ids);
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

    @PutMapping("/validate/{id}")
    public void validateCandidate(@PathVariable Long id) {
        validationService.validate(id);
    }

    @PutMapping("/multiple-validate/{ids}")
    public void validateCandidates(@PathVariable List<Long> ids) {
        validationService.validate(ids);
    }

    @GetMapping("/duplicates-on-valid/{id}")
    public Resources<Duplicate> getValidDuplicates(@PathVariable Long id) {
        return linkMapper.duplicateListToResourceForValidAndNonValid(id, duplicateFinderService.getDuplicates(id, CandidateCheck.VALIDATED), true);
    }

    @GetMapping("/duplicates-on-non-valid/{id}")
    public Resources<Duplicate> getNonValidDuplicates(@PathVariable Long id) {
        return linkMapper.duplicateListToResourceForValidAndNonValid(id, duplicateFinderService.getDuplicates(id, CandidateCheck.NOT_YET_VALIDATED), false);
    }

    @GetMapping("/valid")
    public Resources<Resource<Candidate>> getValidCandidates() {
        return linkMapper.candidateListToResourceForValidAndNonValid(validationService.getValidCandidates(), true);
    }

    @GetMapping("/non-valid")
    public Resources<Resource<Candidate>> getNonValidCandidates() {
        return linkMapper.candidateListToResourceForValidAndNonValid(validationService.getNonValidCandidates(), true);
    }

}
