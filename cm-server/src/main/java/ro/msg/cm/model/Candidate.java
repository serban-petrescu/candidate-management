package ro.msg.cm.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@Entity
public class Candidate {

	private @Id @GeneratedValue Long id;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private @ManyToOne
	@JoinColumn(name="education_id", nullable = true) Education education;
	private String educationStatus;
	private int studyYear;
	private String event;
	private @OneToMany(mappedBy = "candidate") @OrderBy("tag ASC ")
    List<CandidateSkills> candidateSkillsList;

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
