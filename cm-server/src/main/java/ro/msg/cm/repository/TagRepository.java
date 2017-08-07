package ro.msg.cm.repository;

/**
 * Created by oana on 4/20/17.
 */

import org.springframework.data.repository.CrudRepository;
import ro.msg.cm.model.Tag;

public interface TagRepository extends CrudRepository<Tag, Long> {
}
