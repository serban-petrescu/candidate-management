package ro.msg.cm.dbloader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;
import ro.msg.cm.configuration.AvailableUserConfiguration;
import ro.msg.cm.model.Users;
import ro.msg.cm.repository.UserRepository;

@Component
public class UserLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    private final AvailableUserConfiguration availableUserConfiguration;

    @Autowired
    public UserLoader(UserRepository userRepository, AvailableUserConfiguration availableUserConfiguration){
        this.userRepository = userRepository;
        this.availableUserConfiguration = availableUserConfiguration;
    }

    @Override
    public void run(String... strings) throws Exception {

        availableUserConfiguration.getUsers().forEach(newUser -> userRepository.save(new Users(newUser)));
    }
}
