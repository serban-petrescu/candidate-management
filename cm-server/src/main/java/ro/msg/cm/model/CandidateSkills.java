package ro.msg.cm.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by oana on 4/24/17.
 */

@Data
@Entity
public class CandidateSkills {
    private @Id @GeneratedValue Long id;
    private @ManyToOne(fetch = FetchType.LAZY, optional = false) Candidate candidate;
    private @ManyToOne(fetch = FetchType.LAZY, optional = false) Tag tag;
    private String rating;

    public CandidateSkills(){}

    public CandidateSkills(Candidate   candidate, Tag tag, String rating) {
    this.candidate = candidate;
    this.tag = tag;
    this.rating = rating;
    }


}
