package ro.msg.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.msg.cm.model.Tag;
import ro.msg.cm.repository.TagRepository;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
public class TagController {

    private final TagRepository tagRepository;

    @Autowired
    public TagController(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @GetMapping("/{id}")
    public Tag getTag(@PathVariable long id) {
        return tagRepository.findOne(id);
    }

    @GetMapping
    public List<Tag> getTagList() {
        return tagRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tag postTag(@RequestBody Tag tag) {
        return tagRepository.save(tag);
    }

    @PostMapping("/multiple")
    @ResponseStatus(HttpStatus.CREATED)
    public Iterable<Tag> postTagList(@RequestBody List<Tag> tagList) {
        return tagRepository.save(tagList);
    }

    @PutMapping("/{id}")
    public Tag putTag(@PathVariable long id, @RequestBody Tag tag) {
        tag.setId(id);
        return tagRepository.save(tag);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTag(@PathVariable long id) {
        tagRepository.delete(id);
    }

}
