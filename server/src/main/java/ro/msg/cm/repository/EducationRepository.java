package ro.msg.cm.repository;

import org.springframework.data.repository.CrudRepository;
import ro.msg.cm.model.Education;

import java.util.List;

public interface EducationRepository extends CrudRepository<Education, Long> {

    List<Education> findAll();

}
