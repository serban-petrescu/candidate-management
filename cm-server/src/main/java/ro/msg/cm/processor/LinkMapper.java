package ro.msg.cm.processor;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;
import ro.msg.cm.controller.CandidateValidationController;
import ro.msg.cm.model.Candidate;
import ro.msg.cm.pojo.Duplicate;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LinkMapper {

    private CandidateResourceProcessor candidateResourceProcessor;

    public LinkMapper(CandidateResourceProcessor candidateResourceProcessor) {
        this.candidateResourceProcessor = candidateResourceProcessor;
    }

    public Resource<Candidate> candidateToResource(Candidate candidate) {
        Resource<Candidate> resource = candidateResourceProcessor.process(new Resource<>(candidate));
        resource.add(entityLinks.linkFor(Candidate.class).slash(candidate.getId()).withSelfRel());
        resource.add(entityLinks.linkFor(Candidate.class).slash(candidate.getId()).withRel("candidate"));
        resource.add(entityLinks.linkFor(Candidate.class).slash(candidate.getId()).slash("education").withRel("education"));
        resource.add(entityLinks.linkFor(Candidate.class).slash(candidate.getId()).slash("candidateNotesList").withRel("candidateNotesList"));
        resource.add(entityLinks.linkFor(Candidate.class).slash(candidate.getId()).slash("candidateSkillsList").withRel("candidateSkillsList"));
        return resource;
    }

    public Resources<Resource<Candidate>> candidateListToResource(List<Candidate> candidateList) {
        return new Resources<>(candidateList.stream().map(this::candidateToResource).collect(Collectors.toList()));
    }

    public Resources<Resource<Candidate>> candidateListToResourceForValidAndNonValid(List<Candidate> candidateList, boolean isValid) {
        Link valid = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CandidateValidationController.class).getValidCandidates()).withRel("valid");
        Link nonValid = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CandidateValidationController.class).getNonValidCandidates()).withRel("non-valid");
        Resources<Resource<Candidate>> resources = candidateListToResource(candidateList);
        if (isValid) {
            resources.add(valid.withSelfRel());
        } else {
            resources.add(nonValid.withSelfRel());
        }
        resources.add(valid);
        resources.add(nonValid);
        return resources;
    }

    public Resources<Duplicate> duplicateListToResourceForValidAndNonValid(Long id, List<Duplicate> duplicateList, boolean isValid) {
        Resources<Duplicate> resources = new Resources<>(duplicateList);
        Link valid = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CandidateValidationController.class).getValidDuplicates(id)).withRel("valid");
        Link nonValid = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CandidateValidationController.class).getNonValidDuplicates(id)).withRel("non-valid");
        resources.removeLinks();
        if (isValid) {
            resources.add(valid.withSelfRel());
        } else {
            resources.add(nonValid.withSelfRel());
        }
        resources.add(valid);
        resources.add(nonValid);
        return resources;
    }


}
