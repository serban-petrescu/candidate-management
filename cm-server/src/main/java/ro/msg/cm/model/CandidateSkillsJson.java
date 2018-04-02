package ro.msg.cm.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class CandidateSkillsJson {
    @Id
    @GeneratedValue
    private Long id;
    private Long candidateId;
    private Long tagId;
    private String rating;
    private String certifier;

    public CandidateSkillsJson() {
    }

    public CandidateSkillsJson(Long id, Long candidateId, Long tagId, String rating, String certifier) {
        this.id = id;
        this.candidateId = candidateId;
        this.tagId = tagId;
        this.rating = rating;
        this.certifier = certifier;
    }

    public CandidateSkillsJson(CandidateSkills candidateSkills) {
        if (candidateSkills != null) {
            this.id = candidateSkills.getId();
            this.rating = candidateSkills.getRating();
            this.certifier = candidateSkills.getCertifier();
            if (candidateSkills.getCandidate() != null) {
                this.candidateId = candidateSkills.getCandidate().getId();
            }
            if (candidateSkills.getTag() != null) {
                this.tagId = candidateSkills.getTag().getId();
            }
        }
    }
}
