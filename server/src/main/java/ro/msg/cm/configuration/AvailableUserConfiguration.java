package ro.msg.cm.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ro.msg.cm.model.Users;

import java.util.ArrayList;
import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "ro.msg.cm")
public class AvailableUserConfiguration {
    private List<Users> users = new ArrayList<>();
}
