package ro.msg.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ro.msg.cm.model.Candidate;
import ro.msg.cm.model.CandidateNotValidated;
import ro.msg.cm.repository.CandidateNotValidatedRepository;
import ro.msg.cm.repository.CandidateRepository;

import java.util.List;

@RestController
@RequestMapping("/api/validate")
public class ValidateDataController {

    private final CandidateRepository candidateRepository;
    private final CandidateNotValidatedRepository candidateNotValidatedRepository;

    @Autowired
    public ValidateDataController(CandidateRepository candidateRepository,
                                  CandidateNotValidatedRepository candidateNotValidatedRepository) {
        this.candidateRepository = candidateRepository;
        this.candidateNotValidatedRepository = candidateNotValidatedRepository;

    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody CandidateNotValidated candidateNotValidated) {
        candidateNotValidatedRepository.save(candidateNotValidated);
    }

    @RequestMapping(value = "/getAllCandidatesNotValidated", method = RequestMethod.GET)
    public List<CandidateNotValidated> getAllCandidatesNotValidated() {
        return candidateNotValidatedRepository.findAll();
    }

    @RequestMapping(value = "/getAllCandidates", method = RequestMethod.GET)
    public Iterable<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    @RequestMapping(value = "/update/{id}/{firstName}", method = RequestMethod.PUT)
    public CandidateNotValidated updateCandidateById(@PathVariable("id") Long id, @PathVariable("firstName") String firstName) {
        CandidateNotValidated one = candidateNotValidatedRepository.findOne(id);
        one.setFirstName(firstName);
        create(one);
        return one;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        candidateNotValidatedRepository.delete(id);
    }

    @RequestMapping(value = "/find/{lastName}", method = RequestMethod.POST)
    public CandidateNotValidated findByLastName(@Param("lastName") String lastName) {
        return candidateNotValidatedRepository.findByLastName(lastName);
    }

    @RequestMapping(value = "/insert/{lastName}/{originalStudyYear}", method = RequestMethod.POST)
    public void insertByLastName(@PathVariable("lastName") String lastName, @PathVariable("originalStudyYear") int originalStudyYear) {
        candidateNotValidatedRepository.insertIntoCandidateNotValidated(lastName, originalStudyYear);
    }

    @RequestMapping(value = "/insertAll", method = RequestMethod.POST)
    public void insertAll() {
        candidateNotValidatedRepository.insertAll();
    }

    @RequestMapping(value = "/insertById/{id}", method = RequestMethod.POST)
    public void insertByIds(@PathVariable("id") Long id) {
        candidateNotValidatedRepository.insertById(id);
    }

    @RequestMapping(value = "/insertByListOfIds", method = RequestMethod.POST)
    @ResponseBody
    public void insertByIds(@RequestBody List<Long> listOfIds) {
        candidateNotValidatedRepository.insertByListOfIds(listOfIds);
    }
}
