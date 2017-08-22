package ro.msg.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.msg.cm.model.CandidateNotes;
import ro.msg.cm.repository.CandidateNotesRepository;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RequestMapping("/api")
@Controller
public class CandidateNotesController {
    private CandidateNotesRepository candidateNotesRepository;

    @Autowired
    public CandidateNotesController(CandidateNotesRepository candidateNotesRepository) {
        this.candidateNotesRepository = candidateNotesRepository;
    }

    @RequestMapping(value = "/candidates/{id}/candidateNotesList", method = RequestMethod.GET, produces = "application/hal+json")
    @ResponseBody
    public Resource getCandidateNotes(@PathVariable(value = "id") Long id) {
        CandidateNotes candidateNotes = candidateNotesRepository.findOne(id);
        Resource<CandidateNotes> resource = new Resource<>(candidateNotes);
        resource.add(linkTo(methodOn(CandidateNotesController.class).getCandidateNotes(id)).withRel("candidateNotesList"));
        return resource;
    }
}
