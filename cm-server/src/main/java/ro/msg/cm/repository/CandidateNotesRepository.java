package ro.msg.cm.repository;

import org.springframework.data.repository.CrudRepository;
import ro.msg.cm.model.CandidateNotes;

public interface CandidateNotesRepository extends CrudRepository<CandidateNotes, Long> {
}
