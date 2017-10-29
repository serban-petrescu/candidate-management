package ro.msg.cm.repository;

import org.springframework.data.repository.CrudRepository;
import ro.msg.cm.model.Tag;

public interface TagRepository extends CrudRepository<Tag, Long> {
}
