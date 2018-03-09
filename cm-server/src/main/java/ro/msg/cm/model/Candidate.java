package ro.msg.cm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.univocity.parsers.annotations.Parsed;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import ro.msg.cm.exception.PatchCandidateInvalidValueException;
import ro.msg.cm.types.CandidateCheck;
import ro.msg.cm.validator.EmailValidate;
import ro.msg.cm.validator.EmailValidateImpl;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Candidate {
    @Id
    @GeneratedValue
    private Long id;
	@Parsed
	@NotNull
	private String firstName;
	@Parsed
	@NotNull
	private String lastName;
	@Parsed
	@Size(min = 10, max = 15)
	@Pattern(regexp = "[0\\+][0-9]{9,14}")
	private String phone;
	@Parsed
	@Email
    @Setter(AccessLevel.NONE)
	private String email;
    @ManyToOne
    @JoinColumn(name = "education_id")
    private Education education;
    @Parsed
    private String educationStatus;
    @Parsed
    private int originalStudyYear;
    @Parsed
    private String event;
    @OneToMany(mappedBy = "candidate")
    @OrderBy("tag ASC")
    private List<CandidateSkills> candidateSkillsList;
    @OneToMany(mappedBy = "candidate")
    private List<CandidateNotes> candidateNotesList;
    @Parsed
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfAdding;
    @Enumerated(EnumType.STRING)
    private CandidateCheck checkCandidate = CandidateCheck.NOT_YET_VALIDATED;

    @Transient
    private int currentStudyYear;

    public Candidate(String firstName, String lastName) {
        this(firstName, lastName, null, null);
    }

    public Candidate(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        setEmail(email);
    }

    public Candidate(String firstName, String lastName, String phone, String email, String educationStatus, int originalStudyYear, String event, LocalDate dateOfAdding) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        setEmail(email);
        this.educationStatus = educationStatus;
        this.originalStudyYear = originalStudyYear;
        this.event = event;
        this.dateOfAdding = dateOfAdding;
    }

    public void setEmail(String email) {
        EmailValidate emailValidate = new EmailValidateImpl();

        if (!emailValidate.isValid(email)) {
            throw new PatchCandidateInvalidValueException();
        }

        this.email = email;
    }
}
