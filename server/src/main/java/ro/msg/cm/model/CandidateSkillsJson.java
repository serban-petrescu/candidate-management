package ro.msg.cm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateSkillsJson {
    private Long id;
    private Long candidateId;
    private Long tagId;
    private String rating;
    private String certifier;

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
