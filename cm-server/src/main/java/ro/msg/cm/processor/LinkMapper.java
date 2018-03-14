package ro.msg.cm.processor;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;
import ro.msg.cm.controller.*;
import ro.msg.cm.model.*;
import ro.msg.cm.pojo.Duplicate;
import ro.msg.cm.types.CandidateCheck;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LinkMapper {

    private CandidateResourceProcessor candidateResourceProcessor;

    public LinkMapper(CandidateResourceProcessor candidateResourceProcessor) {
        this.candidateResourceProcessor = candidateResourceProcessor;
    }

    public Resource<Candidate> candidateToResource(Candidate candidate) {
        if (candidate != null) {
            Resource<Candidate> resource = candidateResourceProcessor.process(new Resource<>(candidate));
            Link self;
            Link education;
            Link candidateNotesList;
            Link candidateSkillsList;
            if (candidate.getCheckCandidate().equals(CandidateCheck.VALIDATED)) {
                self = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CandidateController.class).getOneCandidate(candidate.getId())).withSelfRel();
                education = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CandidateController.class).getEducation(candidate.getId())).withRel("education");
                candidateNotesList = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CandidateController.class).getCandidateNotesList(candidate.getId())).withRel("candidateNotesList");
                candidateSkillsList = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CandidateController.class).getCandidateSkillsList(candidate.getId())).withRel("candidateSkillsList");
            } else {
                self = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(NYVCandidateController.class).getOneCandidate(candidate.getId())).withSelfRel();
                education = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(NYVCandidateController.class).getEducation(candidate.getId())).withRel("education");
                candidateNotesList = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(NYVCandidateController.class).getCandidateNotesList(candidate.getId())).withRel("candidateNotesList");
                candidateSkillsList = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(NYVCandidateController.class).getCandidateSkillsList(candidate.getId())).withRel("candidateSkillsList");
            }
            resource.add(self);
            resource.add(education);
            resource.add(candidateNotesList);
            resource.add(candidateSkillsList);
            return resource;
        } else {
            throw new EntityNotFoundException();
        }
    }

    public Resources<Resource<Candidate>> candidateListToResource(List<Candidate> candidateList) {
        return new Resources<>(candidateList.stream().map(this::candidateToResource).collect(Collectors.toList()));
    }

    public Resources<Resource<Candidate>> candidateListToResourceForValidAndNonValid(List<Candidate> candidateList, boolean isValid) {
        Link valid = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CandidateController.class).getAllValidatedCandidates()).withRel("valid");
        Link nonValid = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(NYVCandidateController.class).getAllNotYetValidatedCandidates()).withRel("non-valid");
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

    public Resource<CandidateSkills> candidateSkillsToResource(CandidateSkills candidateSkills) {
        if (candidateSkills != null) {
            Resource<CandidateSkills> resource = new Resource<>(candidateSkills);
            Link self = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CandidateSkillsController.class).getCandidateSkills(candidateSkills.getId())).withSelfRel();
            Link candidate = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CandidateSkillsController.class).getCandidateSkillsCandidate(candidateSkills.getId())).withRel("candidate");
            Link tag = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CandidateSkillsController.class).getCandidateSkillsTag(candidateSkills.getId())).withRel("tag");
            resource.add(self);
            resource.add(candidate);
            resource.add(tag);
            return resource;
        } else {
            throw new EntityNotFoundException();
        }
    }

    public Resources<Resource<CandidateSkills>> candidateSkillsListToResource(List<CandidateSkills> candidateSkillsList) {
        return new Resources<>(candidateSkillsList.stream().map(this::candidateSkillsToResource).collect(Collectors.toList()));
    }

    public Resource<CandidateNotes> candidateNotesToResource(CandidateNotes candidateNotes) {
        if (candidateNotes != null) {
            Resource<CandidateNotes> resource = new Resource<>(candidateNotes);
            Link self = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CandidateNotesController.class).getCandidateNotes(candidateNotes.getId())).withSelfRel();
            Link candidate = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CandidateNotesController.class).getCandidateNotesCandidate(candidateNotes.getId())).withRel("candidate");
            resource.add(self);
            resource.add(candidate);
            return resource;
        } else {
            throw new EntityNotFoundException();
        }
    }

    public Resources<Resource<CandidateNotes>> candidateNotesListToResource(List<CandidateNotes> candidateNotesList) {
        return new Resources<>(candidateNotesList.stream().map(this::candidateNotesToResource).collect(Collectors.toList()));
    }

    public Resource<Tag> tagToResource(Tag tag) {
        if (tag != null) {
            Resource<Tag> resource = new Resource<>(tag);
            Link self = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(TagController.class).getTag(tag.getId())).withSelfRel();
            resource.add(self);
            return resource;
        } else {
            throw new EntityNotFoundException();
        }
    }

    public Resources<Resource<Tag>> tagListToResource(List<Tag> tagList) {
        return new Resources<>(tagList.stream().map(this::tagToResource).collect(Collectors.toList()));
    }


    public Resource<Education> educationToResource(Education education) {
        if (education != null) {
            Resource<Education> resource = new Resource<>(education);
            Link self = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(TagController.class).getTag(education.getId())).withSelfRel();
            resource.add(self);
            return resource;
        } else {
            throw new EntityNotFoundException();
        }
    }

    public Resources<Resource<Education>> educationListToResource(List<Education> educationList) {
        return new Resources<>(educationList.stream().map(this::educationToResource).collect(Collectors.toList()));
    }

}
