package ro.msg.cm.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity
public class CandidateNotes {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "candidate_id", insertable = true)
    private Candidate candidate;
    private String status;
    private String note;
    private LocalDate date;

    @Transient
    private Long candidateId;
}
