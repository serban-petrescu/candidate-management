package ro.msg.cm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Candidate {

    private @Id
    @GeneratedValue
    Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private @ManyToOne
    @JoinColumn(name = "education_id", nullable = true)
    Education education;
    private String educationStatus;
    private int originalStudyYear;
    private String event;
    private @OneToMany(mappedBy = "candidate")
    @OrderBy("tag ASC ")
    List<CandidateSkills> candidateSkillsList;
    private @OneToMany(mappedBy = "candidate")
    List<CandidateNotes> candidateNotesList;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfAdding;

    @Transient
    private int currentStudyYear;

    public Candidate() {
    }

    public Candidate(String firstName, String lastName) {
        this(firstName, lastName, null, null);
    }

    public Candidate(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;

    }

    public Candidate(String firstName, String lastName, String phone, String email, String educationStatus, int originalStudyYear, String event, Date dateOfAdding) {
        this(firstName, lastName, phone, email);
        this.educationStatus = educationStatus;
        this.originalStudyYear = originalStudyYear;
        this.event = event;
        this.dateOfAdding = dateOfAdding;

    }


    public void setEducation(Education education) {
        this.education = education;
    }

}
