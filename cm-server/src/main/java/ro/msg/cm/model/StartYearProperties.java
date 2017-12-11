package ro.msg.cm.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "ro.msg.cm.startUniversityYear")
public class StartYearProperties {
    private String startYearDate = "10-01";
}
