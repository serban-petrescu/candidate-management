package ro.msg.cm.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by oana on 4/24/17.
 */

@Data
@Getter
@Setter
@Entity
public class CandidateSkills {
    private @Id @GeneratedValue Long id;
    private @ManyToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "candidate_id") Candidate candidate;
    private @ManyToOne(fetch = FetchType.LAZY, optional = false)@JoinColumn(name= "tag_id") Tag tag;
    private String rating;
    private String certifier;

    public CandidateSkills(){}

    public CandidateSkills(Candidate candidate, Tag tag, String rating){
        this(candidate,tag,rating,null);
    }
    public CandidateSkills(Candidate   candidate, Tag tag, String rating, String certifier) {
    this.candidate = candidate;
    this.tag = tag;
    this.rating = rating;
    this.certifier = certifier;
    }


}
