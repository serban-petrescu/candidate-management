package ro.msg.cm.repository;

import org.springframework.data.repository.CrudRepository;
import ro.msg.cm.model.CandidateNotes;

import java.util.List;

public interface CandidateNotesRepository extends CrudRepository<CandidateNotes, Long> {

    List<CandidateNotes> findAll();

    List<CandidateNotes> save(List<CandidateNotes> candidateNotesList);

}
