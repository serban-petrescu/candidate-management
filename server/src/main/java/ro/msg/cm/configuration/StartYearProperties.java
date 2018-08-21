package ro.msg.cm.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class StartYearProperties {
    @Value("${ro.msg.cm.university-year-start:10-01}")
    private String startYearDate;
}
