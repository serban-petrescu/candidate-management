package ro.msg.cm.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "ro.msg.cm.ldap")
class LdapConfiguration {
	private boolean enabled = false;
	private String url;
	private Integer port;
	private String username;
	private String password;
	private String searchBase;
	private String searchFilter;
	private List<String> whitelistedUsers = new ArrayList<>();
}
