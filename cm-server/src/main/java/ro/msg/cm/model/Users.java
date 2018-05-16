package ro.msg.cm.model;

import lombok.Data;
import ro.msg.cm.configuration.AvailableUserConfiguration;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USERS")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "active")
	private int active;

	public Users() {
	}

	public Users(Users user) {
		this.userId= user.getUserId();
	    this.username = user.getUsername();
		this.password = user.getPassword();
		this.firstName = user.getFirstName();
		this.lastName =user.getLastName();
		this.email = user.getEmail();
		this.active = user.getActive();
	}

	public Users(AvailableUserConfiguration.UserConfiguration userConfiguration) {
		this.username = userConfiguration.getUsername();
		this.password = userConfiguration.getPassword();
		this.firstName = userConfiguration.getFirstName();
		this.lastName =userConfiguration.getLastName();
		this.email = userConfiguration.getEmail();
		this.active = userConfiguration.getActive();
	}

}

