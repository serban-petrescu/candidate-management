package ro.msg.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.*;
import ro.msg.cm.processor.LinkMapper;
import ro.msg.cm.repository.UserRepository;

@RestController
@RequestMapping("/api/login")
@CrossOrigin
public class UserController {

    private final UserRepository userRepository;
    private final LinkMapper linkMapper;

    @Autowired
    public UserController(UserRepository userRepository, LinkMapper linkMapper) {
        this.userRepository = userRepository;
        this.linkMapper = linkMapper;
    }

    @GetMapping()
    public Resource<String> getUser(@RequestParam String username, @RequestParam String password) {

        return linkMapper.userToStringResource(userRepository.findByUsernameAndPassword(username, password));
    }

}
