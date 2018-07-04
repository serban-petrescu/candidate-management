package ro.msg.cm.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

import static java.util.Collections.singletonList;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	private final LdapConfiguration ldapConfiguration;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		if (ldapConfiguration.isEnabled()) {
			log.info("Using LDAP authentication.");
			auth.ldapAuthentication()
					.userSearchBase(ldapConfiguration.getSearchBase())
					.userSearchFilter(ldapConfiguration.getSearchFilter())
					.ldapAuthoritiesPopulator((d, s) -> {
						if (ldapConfiguration.getWhitelistedUsers().contains(s)) {
							return singletonList(new SimpleGrantedAuthority("user"));
						} else {
							log.error("User {} attempted to login, but is not whitelisted.", s);
							throw new UsernameNotFoundException("User not whitelisted: " + s);
						}
					})
					.contextSource()
					.url(ldapConfiguration.getUrl())
					.port(ldapConfiguration.getPort())
					.managerDn(ldapConfiguration.getUsername())
					.managerPassword(ldapConfiguration.getPassword());
		} else {
			log.info("Using local in memory authentication.");
			auth.inMemoryAuthentication()
					.withUser("admin").password("init1234").authorities("user");
		}
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		    .authorizeRequests()
				.antMatchers("/api/**").authenticated()
				.and()
			.formLogin()
				.loginProcessingUrl("/authentication")
				.permitAll()
				.successHandler(getSuccessHandler())
				.failureHandler(getFailureHandler())
				.and()
			.exceptionHandling()
				.accessDeniedHandler(getAccessDeniedHandler())
				.authenticationEntryPoint(sendUnauthorized403AuthenticationEntryPoint())
				.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessHandler(getLogoutSuccessHandler())
				.deleteCookies("JSESSIONID")
				.invalidateHttpSession(true)
				.and()
			.cors()
				.and()
				.csrf().disable()
			.sessionManagement()
				.maximumSessions(1);
	}

	@Bean
	public AuthenticationEntryPoint sendUnauthorized403AuthenticationEntryPoint(){
		return (request, response, authException) -> response.sendError(HttpServletResponse.SC_FORBIDDEN);
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedOrigin("*");
		configuration.addAllowedHeader("*");
		configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","PATCH"));
		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

    private AuthenticationSuccessHandler getSuccessHandler() {
		return (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
				Authentication authentication) -> {
			httpServletResponse.getWriter().append("OK");
			httpServletResponse.setStatus(200);
		};
	}

	private AuthenticationFailureHandler getFailureHandler() {
		return (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
				AuthenticationException e) -> {
			httpServletResponse.getWriter().append("Authentication failure");
			httpServletResponse.setStatus(401);
		};
	}

	private AccessDeniedHandler getAccessDeniedHandler() {
		return (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
				AccessDeniedException e) -> {
			httpServletResponse.getWriter().append("Access denied");
			httpServletResponse.setStatus(403);
		};
	}

	private LogoutSuccessHandler getLogoutSuccessHandler() {
		return (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
				Authentication authentication) -> {
			httpServletResponse.getWriter().append("OK");
			httpServletResponse.setStatus(200);
		};
	}
}
