package com.ecors.api.gateway.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ecors.api.gateway.client.UserServiceClient;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

	private Environment environment;
	private UserServiceClient userServiceClient;

	public WebSecurity(Environment environment, UserServiceClient userServiceClient) {
		this.environment = environment;
		this.userServiceClient = userServiceClient;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
		http.headers().frameOptions().disable();
		http.authorizeRequests().antMatchers("/userservice/verify/**").permitAll()
				.antMatchers("/productmanagement/offers/**").permitAll()
				.antMatchers("/productmanagement/subcategories/**").permitAll()
				.antMatchers("/productmanagement/products/**").permitAll()
				.antMatchers(HttpMethod.POST, environment.getProperty("user.login.url")).permitAll().anyRequest()
				.authenticated().and()
				.addFilter(new AuthenticationFilter(authenticationManager(), environment, userServiceClient));

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").exposedHeaders("token,userid").allowedMethods("GET", "PUT",
				"POST", "DELETE", "PATCH");
	}

}
