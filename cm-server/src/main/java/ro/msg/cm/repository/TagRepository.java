package ro.msg.cm.repository;

import org.springframework.data.repository.CrudRepository;
import ro.msg.cm.model.Tag;

import java.util.List;

public interface TagRepository extends CrudRepository<Tag, Long> {

    List<Tag> findAll();

    List<Tag> save(List<Tag> tagList);

}
