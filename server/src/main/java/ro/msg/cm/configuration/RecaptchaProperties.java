package ro.msg.cm.configuration;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties
public class RecaptchaProperties {

    @Value("${ro.msg.cm.captcha.proxy-host:#{null}}")
    private String proxyHost;

    @Value("${ro.msg.cm.captcha.proxy-port:#{null}}")
    private Integer proxyPort;

    @Value("${ro.msg.cm.captcha.google-secret:#{null}}")
    private String googleSecret;
}
