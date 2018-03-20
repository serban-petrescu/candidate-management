package ro.msg.cm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
@ToString(exclude = "password")
@Getter
@Setter
@NoArgsConstructor
public class User {
	public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	@JsonIgnore
	private String password;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "active")
	private int active;

	public void setPassword(String password) {
		this.password = PASSWORD_ENCODER.encode(password);
	}

	public User(User user) {
		this.userId = user.getUserId();
		this.username = user.getUsername();
		this.firstName = user.getFirstName();
		this.lastName =user.getLastName();
		this.email = user.getEmail();
		this.setPassword(user.getPassword());
		this.active = user.getActive();
	}

}
