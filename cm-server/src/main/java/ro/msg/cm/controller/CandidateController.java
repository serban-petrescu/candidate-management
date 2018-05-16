package ro.msg.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.msg.cm.model.Candidate;
import ro.msg.cm.model.CandidateNotes;
import ro.msg.cm.model.CandidateSkills;
import ro.msg.cm.model.Education;
import ro.msg.cm.processor.LinkMapper;
import ro.msg.cm.repository.CandidateRepository;
import ro.msg.cm.types.CandidateCheck;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 * Controller responsible for actions on {@link CandidateCheck#VALIDATED} candidates
 */
@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    private final CandidateRepository candidateRepository;
    private final LinkMapper linkMapper;

    @Autowired
    public CandidateController(CandidateRepository candidateRepository, LinkMapper linkMapper) {
        this.candidateRepository = candidateRepository;
        this.linkMapper = linkMapper;
    }

    @GetMapping("/{id}")
    public Resource<Candidate> getOneCandidate(@PathVariable Long id) {
        return linkMapper.candidateToResource(candidateRepository.findByIdAndCheckCandidate(id, CandidateCheck.VALIDATED));
    }

    @GetMapping
    public Resources<Resource<Candidate>> getAllValidatedCandidates() {
        return linkMapper.candidateListToResource(candidateRepository.findAllByCheckCandidate(CandidateCheck.VALIDATED));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Resource<Candidate> saveCandidate(@RequestBody Candidate candidate) {
        candidate.setCheckCandidate(CandidateCheck.VALIDATED);
        return linkMapper.candidateToResource(candidateRepository.save(candidate));
    }

    @PostMapping("/multiple")
    @ResponseStatus(HttpStatus.CREATED)
    public Resources<Resource<Candidate>> saveCandidates(@RequestBody List<Candidate> candidates) {
        candidates.forEach(x -> x.setCheckCandidate(CandidateCheck.VALIDATED));
        return linkMapper.candidateListToResource((List<Candidate>) candidateRepository.save(candidates));
    }

    /**
     * update a candidate
     *
     * @param id        id of the candidate
     * @param candidate candidate with changes
     * @return updated candidate
     */
    @PutMapping("/{id}")
    public Resource<Candidate> updateCandidate(@PathVariable Long id, @RequestBody Candidate candidate) {
        Optional<Candidate> dbCandidate = candidateRepository.findCandidateById(id);
        if (dbCandidate.isPresent()) {
            candidate.setEducation(dbCandidate.get().getEducation());
            candidate.setCandidateNotesList(dbCandidate.get().getCandidateNotesList());
            candidate.setCandidateSkillsList(dbCandidate.get().getCandidateSkillsList());
            return linkMapper.candidateToResource(candidateRepository.save(candidate));
        } else {
            throw new EntityNotFoundException();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCandidateWithId(@PathVariable Long id) {
        if (candidateRepository.findCandidateById(id) != null) {
            candidateRepository.delete(id);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @GetMapping("/{id}/education")
    public Resource<Education> getEducation(@PathVariable Long id) {
        Candidate candidate = candidateRepository.findByIdAndCheckCandidate(id, CandidateCheck.VALIDATED);
        if (candidate != null) {
            return linkMapper.educationToResource(candidate.getEducation());
        } else {
            throw new EntityNotFoundException();
        }
    }

    @GetMapping("/{id}/candidateNotesList")
    public Resources<Resource<CandidateNotes>> getCandidateNotesList(@PathVariable Long id) {
        Candidate candidate = candidateRepository.findByIdAndCheckCandidate(id, CandidateCheck.VALIDATED);
        if (candidate != null) {
            return linkMapper.candidateNotesListToResource(candidate.getCandidateNotesList());
        } else {
            throw new EntityNotFoundException();
        }
    }

    @GetMapping("/{id}/candidateSkillsList")
    public Resources<Resource<CandidateSkills>> getCandidateSkillsList(@PathVariable Long id) {
        Candidate candidate = candidateRepository.findByIdAndCheckCandidate(id, CandidateCheck.VALIDATED);
        if (candidate != null) {
            return linkMapper.candidateSkillsListToResource(candidate.getCandidateSkillsList());
        } else {
            throw new EntityNotFoundException();
        }
    }
}
