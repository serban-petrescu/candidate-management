package ro.msg.cm.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ro.msg.cm.configuration.PropertiesLoader;
import ro.msg.cm.model.GoogleResponse;
import ro.msg.cm.model.GoogleValidationCode;

import java.net.URI;
import java.util.Properties;

/**
 * Client app will send a request using a validation code as a payload (GoogleValidationCode).
 * This code is produced from GRecaptcha.
 * Using this validation code we make a request to https://www.google.com/recaptcha/api/siteverify
 * to test the validity of the clicked captcha.
 * The response is mapped to GoogleResponse and then sent to the client app as a response to the
 * initial request.
 */

@RestController
@RequestMapping("/api/verify")
public class VerifyRecaptcha {

    @RequestMapping(value = "/googleverify", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GoogleResponse getGRecaptchaResponse(@RequestBody GoogleValidationCode googleRecaptcha) {
        Properties propLoader = PropertiesLoader.loadPropertiesFile("config.properties");
        RestTemplate restTemplate = new RestTemplate();
        URI verifyUri = URI.create(String.format("https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s", propLoader.getProperty
                ("GOOGLESECRET"), googleRecaptcha.getValidationCode()));
        System.setProperty("proxyHost", propLoader.getProperty("PROXYHOST"));
        System.setProperty("proxyPort", propLoader.getProperty("PROXYPORT"));
        GoogleResponse googleResponse = restTemplate.getForObject(verifyUri, GoogleResponse.class);
        return googleResponse;
    }
}
