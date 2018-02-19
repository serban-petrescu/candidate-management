package ro.msg.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.msg.cm.model.Candidate;
import ro.msg.cm.pojo.Duplicate;
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

    @Autowired
    public CandidateValidationController(ValidationService validationService, DuplicateFinderService duplicateFinderService) {
        this.validationService = validationService;
        this.duplicateFinderService = duplicateFinderService;
    }

    @PatchMapping("/update-candidate/{id}")
    public Candidate patchCandidate(@RequestBody Map<String, Object> toPatch, @PathVariable Long id) {
        return validationService.patchCandidate(toPatch, id);
    }

    @DeleteMapping("/delete-candidate/{id}")
    public void deleteCandidate(@PathVariable Long id){
        validationService.deleteSelectedEntry(id);
    }

    @DeleteMapping("/delete-candidates/{ids}")
    public void deleteCandidate(@PathVariable List<Long> ids){
        validationService.deleteSelectedEntries(ids);
    }


	@GetMapping("/duplicates/{id}")
    public List<Duplicate> getDuplicates(@PathVariable Long id, CandidateCheck candidateCheck) {
        return duplicateFinderService.getDuplicates(id, candidateCheck);
    }

    @PostMapping("/add-candidate")
    public Candidate saveUnvalidatedCandidate(@RequestBody Candidate candidate) {
        return validationService.saveUnvalidatedCandidate(candidate);
    }

    @PostMapping("/add-candidates")
    public Iterable<Candidate> saveUnvalidatedCandidates(@RequestBody List<Candidate> candidates) {
        return validationService.saveUnvalidatedCandidates(candidates);
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
    public List<Duplicate> getValidDuplicates(@PathVariable Long id) {
        return duplicateFinderService.getDuplicates(id, CandidateCheck.VALIDATED);
    }

    @GetMapping("/duplicates-on-non-valid/{id}")
    public List<Duplicate> getNonValidDuplicates(@PathVariable Long id) {
        return duplicateFinderService.getDuplicates(id, CandidateCheck.NOT_YET_VALIDATED);
    }

    @GetMapping("/valid")
    public List<Candidate> getValidCandidates() {
        return validationService.getValidCandidates();
    }

    @GetMapping("/non-valid")
    public List<Candidate> getNonValidCandidates() {
        return validationService.getNonValidCandidates();
    }

}
