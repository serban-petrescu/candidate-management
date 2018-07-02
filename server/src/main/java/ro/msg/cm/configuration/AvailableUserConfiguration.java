package ro.msg.cm.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "available-application-users")
@Configuration
@Component
@Data
public class AvailableUserConfiguration {

  private List<UserConfiguration> users = new ArrayList<>();

  @Component
  @Data
  public static class UserConfiguration {
      private String username;
      private String password;
      private String firstName;
      private String lastName;
      private String email;
      private int active;
  }
}
