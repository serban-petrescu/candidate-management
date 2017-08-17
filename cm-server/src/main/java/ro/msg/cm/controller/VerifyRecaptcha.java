package ro.msg.cm.controller;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ro.msg.cm.model.GoogleResponse;
import ro.msg.cm.model.GoogleValidationCode;
import ro.msg.cm.model.RecaptchaProperties;

import java.net.URI;

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

    private static RecaptchaProperties recaptchaProperties;

    @Autowired
    public VerifyRecaptcha(RecaptchaProperties recaptchaProperties) {
        VerifyRecaptcha.recaptchaProperties = recaptchaProperties;
    }

    @RequestMapping(value = "/googleverify", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GoogleResponse getGRecaptchaResponse(@RequestBody GoogleValidationCode googleRecaptcha) {
        RestTemplate restTemplate = new RestTemplate();
        URI verifyUri = URI.create(String.format("https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s", recaptchaProperties.getGoogleSecret(), googleRecaptcha.getValidationCode()));
        VerifyRecaptcha.ProxyCustomizer proxyCustomizer = new VerifyRecaptcha.ProxyCustomizer();
        proxyCustomizer.customize(restTemplate);
        return restTemplate.getForObject(verifyUri, GoogleResponse.class);
    }

    static class ProxyCustomizer implements RestTemplateCustomizer {

        @Override
        public void customize(RestTemplate restTemplate) {
            String proxyHost = recaptchaProperties.getProxyHost();
            Integer proxyPort = recaptchaProperties.getProxyPort();
            HttpClient httpClient;

            if (proxyPort == null || proxyHost == null || proxyHost.isEmpty()) {
                httpClient = HttpClientBuilder.create().build();
            } else {
                httpClient = HttpClientBuilder.create()
                                              .setRoutePlanner(new DefaultProxyRoutePlanner(new HttpHost(proxyHost, proxyPort))).build();
            }
            restTemplate.setRequestFactory(
                    new HttpComponentsClientHttpRequestFactory(httpClient));
        }
    }
}
