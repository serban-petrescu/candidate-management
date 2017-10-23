package ro.msg.cm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Payload which is sent to google. Based on this code google will validate
 * the captcha
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleValidationCode {

    @JsonProperty("response")
    private String validationCode;
}
