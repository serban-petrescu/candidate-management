package ro.msg.cm.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "ro.msg.cm.startUniversityYear")
public class StartYearProperties {

    @DateTimeFormat(pattern = "MM-dd")
    private String startYearDate;

}
