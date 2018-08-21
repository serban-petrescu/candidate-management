package ro.msg.cm.local;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
@EnableZuulProxy
public class LocalConfiguration {

	@Bean
	public FilterRegistrationBean blockingResourcesFilter(@Value("${server.port}") int port) {
		FilterRegistrationBean bean = new FilterRegistrationBean(new LocalDevFilter("http://localhost:" + port + "/"));
		bean.setUrlPatterns(Collections.singletonList("/sockjs-node/*"));
		bean.setOrder(0);
		return bean;
	}
}
