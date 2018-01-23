package ro.msg.cm.processor;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;
import ro.msg.cm.model.Candidate;
import ro.msg.cm.util.CandidateUtils;

import java.util.Date;

@Component
public class CandidateResourceProcessor implements ResourceProcessor<Resource<Candidate>> {
    private final CandidateUtils utils;

    public CandidateResourceProcessor(CandidateUtils utils) {
        this.utils = utils;
    }

    @Override
    public Resource<Candidate> process(Resource<Candidate> resource) {
        Candidate candidate = resource.getContent();
        if (candidate != null) {
            candidate.setDateOfAdding(new Date());
            candidate.setCurrentStudyYear(utils.determineYearBasedOnDuration(candidate));
        }
        return resource;
    }
}
