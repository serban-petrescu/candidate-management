package ro.msg.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.msg.cm.model.Candidate;
import ro.msg.cm.repository.CandidateRepository;
import ro.msg.cm.types.CandidateCheck;

import java.util.List;

@RestController
@RequestMapping("/api/candidate")
public class CandidateController {

    private final CandidateRepository candidateRepository;

    @Autowired
    public CandidateController(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @GetMapping("/get-all-validated-candidates")
    public Iterable<Candidate> getAllValidatedCandidates(){
        return candidateRepository.findAllByCheckCandidate(CandidateCheck.VALIDATED);
    }

    @GetMapping("/get-all-candidates")
    public Iterable<Candidate> getAllCandidates(){
        return candidateRepository.findAll();
    }

    @GetMapping("/get-one-candidate/{id}")
    public Candidate getOneCandidate(long id){
        return candidateRepository.findByIdAndCheckCandidate(id, CandidateCheck.VALIDATED);
    }

    @PostMapping("/save-candidate")
    @ResponseStatus(HttpStatus.CREATED)
    public Candidate saveCandidate(@RequestBody Candidate candidate){
        return candidateRepository.save(candidate);
    }

    @PostMapping("/save-candidates")
    @ResponseStatus(HttpStatus.CREATED)
    public Iterable<Candidate> saveCandidates(@RequestBody List<Candidate> candidates){
        return candidateRepository.save(candidates);
    }

    @DeleteMapping("/delete-candidate/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCandidateWithId(@PathVariable Long id){
        candidateRepository.delete(id);
    }
}
