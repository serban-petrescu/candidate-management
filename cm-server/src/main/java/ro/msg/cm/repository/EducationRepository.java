package ro.msg.cm.repository;

import org.springframework.data.repository.CrudRepository;
import ro.msg.cm.model.Education;

public interface EducationRepository extends CrudRepository<Education, Long> {
}
