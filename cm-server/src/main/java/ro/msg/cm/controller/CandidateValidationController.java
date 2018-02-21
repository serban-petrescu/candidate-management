package ro.msg.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;
import ro.msg.cm.model.Candidate;
import ro.msg.cm.pojo.Duplicate;
import ro.msg.cm.processor.LinkMapper;
import ro.msg.cm.service.DuplicateFinderService;
import ro.msg.cm.service.ValidationService;
import ro.msg.cm.types.CandidateCheck;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/candidate-validation")
public class CandidateValidationController {

    private final ValidationService validationService;
    private final DuplicateFinderService duplicateFinderService;
    private final LinkMapper linkMapper;

    @Autowired
    public CandidateValidationController(ValidationService validationService, DuplicateFinderService duplicateFinderService,LinkMapper linkMapper) {
        this.validationService = validationService;
        this.duplicateFinderService = duplicateFinderService;
        this.linkMapper = linkMapper;
    }

    @PatchMapping("/update-candidate/{id}")
    public Resource<Candidate> patchCandidate(@RequestBody Map<String, Object> toPatch, @PathVariable Long id) {
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
    public Resource<Candidate> saveUnvalidatedCandidate(@RequestBody Candidate candidate) {
        Candidate candidateSaved = validationService.saveUnvalidatedCandidate(candidate);
        return linkMapper.candidateToResource(candidateSaved);
    }

    @PostMapping("/add-candidates")
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
