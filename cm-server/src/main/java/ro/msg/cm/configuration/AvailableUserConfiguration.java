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
public class AvailableUserConfiguration {

  private List<UserConfiguration> userConfigurationList = new ArrayList<>();

  public AvailableUserConfiguration(List<UserConfiguration> userConfigurationList) {
      this.userConfigurationList = userConfigurationList;
  }

  public AvailableUserConfiguration() {

  }

  public List<UserConfiguration> getUserConfigurationList() {
    return userConfigurationList;
  }

  public void setUserConfigurationList(List<UserConfiguration> userConfigurationList) {
    this.userConfigurationList = userConfigurationList;
  }

  @Component
  public static class UserConfiguration {
      private String username;
      private String password;
      private String firstName;
      private String lastName;
      private String email;
      private int active;

      public String getUsername() {
          return username;
      }

      public void setUsername(String username) {
          this.username = username;
      }

      public String getPassword() {
          return password;
      }

      public void setPassword(String password) {
          this.password = password;
      }

      public String getFirstName() {
          return firstName;
      }

      public void setFirstName(String firstName) {
          this.firstName = firstName;
      }

      public String getLastName() {
          return lastName;
      }

      public void setLastName(String lastName) {
          this.lastName = lastName;
      }

      public String getEmail() {
          return email;
      }

      public void setEmail(String email) {
          this.email = email;
      }

      public int getActive() {
          return active;
      }

      public void setActive(int active) {
          this.active = active;
      }


      public UserConfiguration(String username, String password, String firstName, String lastName, String email, int active){
          this.username = username;
          this.password = password;
          this.firstName = firstName;
          this.lastName = lastName;
          this.email = email;
          this.active = active;
      }

      public UserConfiguration(){

      }

  }


}
