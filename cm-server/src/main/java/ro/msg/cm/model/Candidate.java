package ro.msg.cm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.univocity.parsers.annotations.Parsed;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.cm.types.CandidateCheck;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Candidate {

	private @Id @GeneratedValue Long id;
	@Parsed
	private String firstName;
	@Parsed
	private String lastName;
	@Parsed
	private String phone;
	@Parsed
	private String email;
	private @ManyToOne
    @JoinColumn(name = "education_id")
    Education education;
	@Parsed
    private String educationStatus;
	@Parsed
    private int originalStudyYear;
    @Parsed
	private String event;
    private @OneToMany(mappedBy = "candidate")
    @OrderBy("tag ASC")
    List<CandidateSkills> candidateSkillsList;
	private @OneToMany(mappedBy = "candidate")
	List<CandidateNotes> candidateNotesList;
	@Parsed
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfAdding;
	@Enumerated(EnumType.STRING)
	private CandidateCheck checkCandidate;

	@Transient
	private int currentStudyYear;

	public Candidate(String firstName, String lastName) {
		this(firstName,lastName,null,null);
		}

	public Candidate(String firstName, String lastName, String phone, String email){
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone=phone;
		this.email =email;

	}

	public Candidate(String firstName, String lastName, String phone, String email, String educationStatus, int originalStudyYear, String event, LocalDate dateOfAdding) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.educationStatus = educationStatus;
		this.originalStudyYear = originalStudyYear;
		this.event = event;
		this.dateOfAdding = dateOfAdding;
	}


}
