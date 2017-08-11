package ro.msg.cm.model;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource("classpath:config.properties")
@ConfigurationProperties
public class RecaptchaProperties {

    @Value("${PROXYHOST:#{''}}")
    private String proxyHost;

    @Value("${PROXYPORT:#{''}}")
    private String proxyPort;

    @Value("${GOOGLESECRET:#{''}}")
    private String googleSecret;
}
