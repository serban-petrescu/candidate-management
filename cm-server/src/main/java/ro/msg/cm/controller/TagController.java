package ro.msg.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.msg.cm.model.Tag;
import ro.msg.cm.processor.LinkMapper;
import ro.msg.cm.repository.TagRepository;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final TagRepository tagRepository;
    private final LinkMapper linkMapper;

    @Autowired
    public TagController(TagRepository tagRepository, LinkMapper linkMapper) {
        this.tagRepository = tagRepository;
        this.linkMapper = linkMapper;
    }

    @GetMapping("/{id}")
    public Resource<Tag> getTag(@PathVariable Long id) {
        return linkMapper.tagToResource(tagRepository.findOne(id));
    }

    @GetMapping
    public Resources<Resource<Tag>> getTagList() {
        return linkMapper.tagListToResource(tagRepository.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Resource<Tag> postTag(@RequestBody Tag tag) {
        return linkMapper.tagToResource(tagRepository.save(tag));
    }

    @PostMapping("/multiple")
    @ResponseStatus(HttpStatus.CREATED)
    public Resources<Resource<Tag>> postTagList(@RequestBody List<Tag> tagList) {
        return linkMapper.tagListToResource((List<Tag>) tagRepository.save(tagList));
    }

    @PutMapping("/{id}")
    public Resource<Tag> putTag(@PathVariable Long id, @RequestBody Tag tag) {
        tag.setId(id);
        return linkMapper.tagToResource(tagRepository.save(tag));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTag(@PathVariable Long id) {
        tagRepository.delete(id);
    }

}
