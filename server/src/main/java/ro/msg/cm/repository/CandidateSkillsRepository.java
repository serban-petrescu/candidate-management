package ro.msg.cm.repository;

import org.springframework.data.repository.CrudRepository;
import ro.msg.cm.model.CandidateSkills;

import java.util.List;

public interface CandidateSkillsRepository extends CrudRepository<CandidateSkills, Long> {

    List<CandidateSkills> findAll();

}
