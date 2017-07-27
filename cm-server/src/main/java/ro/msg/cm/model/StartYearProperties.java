package ro.msg.cm.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
@PropertySource("classpath:global.properties")
public class StartYearProperties {
    @DateTimeFormat(pattern = "MM-dd")
    @Value("#{new java.text.SimpleDateFormat('${aDateFormat}').parse('${startYearDate}')}")
    private Date startYearDate;

}
