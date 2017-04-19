package ro.msg.cm.configuration;


import org.springframework.web.servlet.config.annotation.*;
import org.springframework.context.annotation.*;



@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
 
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
}
