package ro.msg.cm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Data
@Getter
@Setter
@Entity
@Table(name = "candidate_not_validated")

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CandidateNotValidated {
    private @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "education_status")
    private String educationStatus;
    @Column(name = "original_study_year")
    private int originalStudyYear;
    @Column(name = "event")
    private String event;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfAdding;

    public CandidateNotValidated() {
    }

    public CandidateNotValidated(String firstName, String lastName) {
        this(firstName, lastName, null, null);
    }

    public CandidateNotValidated(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;

    }

    public CandidateNotValidated(String firstName, String lastName, String phone, String email, String educationStatus, int originalStudyYear, String event, Date dateOfAdding) {
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
