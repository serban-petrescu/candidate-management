package ro.msg.cm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import ro.msg.cm.model.CandidateSkills;

import java.util.List;

public interface CandidateSkillsRepository extends CrudRepository<CandidateSkills, Long> {

    List<CandidateSkills> findAll();

    List<CandidateSkills> findFirst10ByTagDescriptionContaining(@Param("description") String description);
}
