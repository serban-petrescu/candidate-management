package ro.msg.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Tag postTag(@RequestBody Tag tag) {
        return tagRepository.save(tag);
    }

    @PostMapping("/multiple")
    public Iterable<Tag> postTagList(@RequestBody List<Tag> tagList) {
        return tagRepository.save(tagList);
    }

    @PutMapping("/{id}")
    public Tag putTag(@PathVariable long id, @RequestBody Tag tag) {
        tag.setId(id);
        return tagRepository.save(tag);
    }

    @DeleteMapping("/{id}")
    public void deleteTag(@PathVariable long id) {
        tagRepository.delete(id);
    }

}
