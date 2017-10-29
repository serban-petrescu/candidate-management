package ro.msg.cm.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource("classpath:config.properties")
@ConfigurationProperties
public class MockProperties {
    @Value("${ro.msg.cm.mock.enabled}")
    private Boolean enabled;

    @Value("${ro.msg.cm.mock.location}")
    private String location;
}
