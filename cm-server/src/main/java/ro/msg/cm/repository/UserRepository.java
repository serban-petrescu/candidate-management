package ro.msg.cm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.cm.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);
}
