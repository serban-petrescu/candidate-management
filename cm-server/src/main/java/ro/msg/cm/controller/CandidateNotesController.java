package ro.msg.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.msg.cm.model.Candidate;
import ro.msg.cm.model.CandidateNotes;
import ro.msg.cm.processor.LinkMapper;
import ro.msg.cm.repository.CandidateNotesRepository;
import ro.msg.cm.repository.CandidateRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/candidateNotes")
public class CandidateNotesController {

    private final CandidateNotesRepository candidateNotesRepository;
    private final CandidateRepository candidateRepository;
    private final LinkMapper linkMapper;

    @Autowired
    public CandidateNotesController(CandidateNotesRepository candidateNotesRepository, CandidateRepository candidateRepository, LinkMapper linkMapper) {
        this.candidateNotesRepository = candidateNotesRepository;
        this.candidateRepository = candidateRepository;
        this.linkMapper = linkMapper;
    }

    @GetMapping("/{id}")
    public Resource<CandidateNotes> getCandidateNotes(@PathVariable Long id) {
        CandidateNotes candidateNotes = candidateNotesRepository.findOne(id);
        return linkMapper.candidateNotesToResource(candidateNotes);
    }

    @GetMapping
    public Resources<Resource<CandidateNotes>> getCandidateNotesList() {
        return linkMapper.candidateNotesListToResource(candidateNotesRepository.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Resource<CandidateNotes> postCandidateNotes(@RequestBody CandidateNotes candidateNotes) {
        Candidate candidate = candidateRepository.findCandidateById(candidateNotes.getCandidateId()).get();
        candidateNotes.setCandidate(candidate);
        return linkMapper.candidateNotesToResource(candidateNotesRepository.save(candidateNotes));
    }

    @PostMapping("/multiple")
    @ResponseStatus(HttpStatus.CREATED)
    public Resources<Resource<CandidateNotes>> postCandidateNotesList(@RequestBody List<CandidateNotes> candidateNotesList) {
        return linkMapper.candidateNotesListToResource((List<CandidateNotes>) candidateNotesRepository.save(candidateNotesList));
    }

    @PutMapping("/{id}")
    public Resource<CandidateNotes> putCandidateNotes(@PathVariable Long id, @RequestBody Candidate candidate) {
        CandidateNotes candidateNotes = candidateNotesRepository.findOne(id);
        candidateNotes.setCandidate(candidate);
        return linkMapper.candidateNotesToResource(candidateNotesRepository.save(candidateNotes));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCandidateNotes(@PathVariable long id) {
        candidateNotesRepository.delete(id);
    }


    @GetMapping("/{id}/candidate")
    public Resource<Candidate> getCandidateNotesCandidate(@PathVariable long id) {
        CandidateNotes candidateNotes = candidateNotesRepository.findOne(id);
        if (candidateNotes != null) {
            return linkMapper.candidateToResource(candidateNotes.getCandidate());
        } else {
            throw new EntityNotFoundException();
        }
    }
}
