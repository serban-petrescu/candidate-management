package ro.msg.cm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.univocity.parsers.annotations.Parsed;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import ro.msg.cm.types.CandidateCheck;
import ro.msg.cm.validator.OneNotNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@OneNotNull({"phone", "email"})
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Parsed
    @NotNull
    private String firstName;

    @Parsed
    @NotNull
    private String lastName;

    @Parsed
    @Pattern(regexp = "^(0|(0040|004\\s0)|(\\+40|\\+4\\s0))([0-9]{3}\\s?|[0-9]{2}\\s[0-9])(([0-9]{3}\\s?){2}|([0-9]{2}\\s?){3})$")
    private String phone;

    @Parsed
    @Email
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

    @OrderBy("tag ASC")
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.REMOVE)
    private List<CandidateSkills> candidateSkillsList;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.REMOVE)
    private List<CandidateNotes> candidateNotesList;

    @Parsed
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfAdding;

    @Enumerated(EnumType.STRING)
    private CandidateCheck checkCandidate = CandidateCheck.NOT_YET_VALIDATED;

    @Transient
    private int currentStudyYear;
    @Transient
    private String foreignLanguages;
    @Transient
    private String academicInformation;

    public Candidate(String firstName, String lastName) {
        this(firstName, lastName, null, null);
    }

    public Candidate(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;

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
