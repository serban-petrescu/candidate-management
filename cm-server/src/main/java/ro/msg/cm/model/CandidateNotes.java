package ro.msg.cm.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by oana on 6/14/17.
 */
@Data
@Getter
@Setter
@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CandidateNotes {
    private @Id @GeneratedValue Long id;
    private @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name="candidate_id", insertable=true) Candidate candidate;
    private String status;
    private String note;
    private Date date;

    public CandidateNotes(){}

    public CandidateNotes(Candidate candidate, String status, String note, Date date){
        this.candidate=candidate;
        this.status = status;
        this.note=note;
        this.date=date;
    }
}
