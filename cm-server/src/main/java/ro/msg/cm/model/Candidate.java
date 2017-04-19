package ro.msg.cm.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Candidate {

	private @Id @GeneratedValue Long id;
	private String firstName;
	private String lastName;

	private Candidate() {}

	public Candidate(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
}
