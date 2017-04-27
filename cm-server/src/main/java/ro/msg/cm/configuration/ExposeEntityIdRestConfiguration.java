package ro.msg.cm.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import ro.msg.cm.model.Candidate;
import ro.msg.cm.model.CandidateSkills;
import ro.msg.cm.model.Education;
import ro.msg.cm.model.Tag;

@Configuration
public class ExposeEntityIdRestConfiguration extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Candidate.class);
        config.exposeIdsFor(Tag.class);
        config.exposeIdsFor(Education.class);
        config.exposeIdsFor(CandidateSkills.class);
    }
}
