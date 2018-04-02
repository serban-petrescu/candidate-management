package ro.msg.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ro.msg.cm.model.GoogleResponse;
import ro.msg.cm.model.GoogleValidationCode;
import ro.msg.cm.model.RecaptchaProperties;

import java.net.InetSocketAddress;
import java.net.Proxy;

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
    private static final String GOOGLE_API_URL =
            "https://www.google.com/recaptcha/api/siteverify?secret={secret}&response={code}";
    private RecaptchaProperties properties;

    @Autowired
    public VerifyRecaptcha(RecaptchaProperties properties) {
        this.properties = properties;
    }

    @RequestMapping(value = "/googleverify", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GoogleResponse getGRecaptchaResponse(@RequestBody GoogleValidationCode googleRecaptcha) {
        RestTemplate restTemplate = new RestTemplate(buildRequestFactory());
        return restTemplate.getForObject(GOOGLE_API_URL, GoogleResponse.class, properties.getGoogleSecret(),
                googleRecaptcha.getValidationCode());
    }

    private ClientHttpRequestFactory buildRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        if (!StringUtils.isEmpty(properties.getProxyHost()) && properties.getProxyPort() != null) {
            factory.setProxy(buildProxy());
        }
        return factory;
    }

    private Proxy buildProxy() {
        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(properties.getProxyHost(), properties.getProxyPort()));
    }
}
