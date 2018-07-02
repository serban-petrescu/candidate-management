package ro.msg.cm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@ToString(exclude = "candidate")
@EqualsAndHashCode(exclude = "candidate")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "candidate"})
public class CandidateSkills {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tag_id")
    private Tag tag;
    private String rating;
    private String certifier;

    public CandidateSkills(Candidate candidate, Tag tag, String rating) {
        this(candidate, tag, rating, null);
    }

    public CandidateSkills(Candidate candidate, Tag tag, String rating, String certifier) {
        this.candidate = candidate;
        this.tag = tag;
        this.rating = rating;
        this.certifier = certifier;
    }


}
