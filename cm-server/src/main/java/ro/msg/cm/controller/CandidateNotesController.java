package ro.msg.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.msg.cm.model.CandidateNotes;
import ro.msg.cm.repository.CandidateNotesRepository;

import java.util.List;

@RestController
@RequestMapping("/api/candidateNotes")
public class CandidateNotesController {

    private final CandidateNotesRepository candidateNotesRepository;

    @Autowired
    public CandidateNotesController(CandidateNotesRepository candidateNotesRepository) {
        this.candidateNotesRepository = candidateNotesRepository;
    }

    @GetMapping("/{id}")
    public CandidateNotes getCandidateNotes(@PathVariable long id) {
        return candidateNotesRepository.findOne(id);
    }

    @GetMapping
    public List<CandidateNotes> getCandidateNotesList() {
        return candidateNotesRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CandidateNotes postCandidateNotes(@RequestBody CandidateNotes candidateNotes) {
        return candidateNotesRepository.save(candidateNotes);
    }

    @PostMapping("/multiple")
    @ResponseStatus(HttpStatus.CREATED)
    public Iterable<CandidateNotes> postCandidateNotesList(@RequestBody List<CandidateNotes> candidateNotesList) {
        return candidateNotesRepository.save(candidateNotesList);
    }

    @PutMapping("/{id}")
    public CandidateNotes putCandidateNotes(@PathVariable long id, @RequestBody CandidateNotes candidateNotes) {
        candidateNotes.setId(id);
        return candidateNotesRepository.save(candidateNotes);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCandidateNotes(@PathVariable long id) {
        candidateNotesRepository.delete(id);
    }
}
