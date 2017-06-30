package ro.msg.cm.controller;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ro.msg.cm.model.GoogleResponse;
import ro.msg.cm.model.GoogleValidationCode;


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
    public static final String PROXYHOST = "proxy.msg.de";
    public static final String PROXYPORT = "3128";
    public static final String GOOGLESECRET = "6LdWNCcUAAAAANA0MTz33JHeIAxSK1Jv9ghGXtzu";

    @RequestMapping(value = "/googleverify", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GoogleResponse getGRecaptchaResponse(@RequestBody GoogleValidationCode googleRecaptcha) {
        RestTemplate restTemplate = new RestTemplate();
        URI verifyUri = URI.create(String.format("https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s", GOOGLESECRET, googleRecaptcha.getValidationCode()));
        System.setProperty("proxyHost", PROXYHOST);
        System.setProperty("proxyPort", PROXYPORT);
        GoogleResponse googleResponse = restTemplate.getForObject(verifyUri, GoogleResponse.class);
        return googleResponse;
    }
}
