package ro.msg.cm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.cm.model.Users;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

	Optional<Users> findByUsername(String username);
}
