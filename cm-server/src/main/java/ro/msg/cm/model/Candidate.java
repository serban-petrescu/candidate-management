package ro.msg.cm.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
@Data
@Entity
public class Candidate {

	private @Id @GeneratedValue Long id;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private @ManyToOne(fetch = FetchType.LAZY, optional = true) Education education;
	private String educationStatus;
	private int studyYear;
	private String event;

	private Candidate() {}

	public Candidate(String firstName, String lastName) {
		this(firstName,lastName,null,null);
		}

	public Candidate(String firstName, String lastName, String phone, String email){
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone=phone;
		this.email =email;

	}

	public void setEducation(Education education)
	{
		this.education = education;
	}

}
