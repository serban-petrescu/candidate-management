package ro.msg.cm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ro.msg.cm.model.Users;
import java.util.Optional;

public interface UserRepository extends CrudRepository<Users, Long> {

	Optional<Users> findByUsername(String username);
}
