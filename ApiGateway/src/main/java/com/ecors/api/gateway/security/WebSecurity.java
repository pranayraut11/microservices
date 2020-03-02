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

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

	private Environment environment;

	public WebSecurity(Environment environment) {
		this.environment = environment;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
		http.headers().frameOptions().disable();
		System.out.println("permit all path " + environment.getProperty("user.create.url"));
		http.authorizeRequests().antMatchers("/userservice/verify/**").permitAll()
				.antMatchers("/productmanagement/offers/**").permitAll()
				.antMatchers("/productmanagement/subcategories/**").permitAll()
				.antMatchers("/productmanagement/products/**").permitAll()
				.antMatchers(HttpMethod.POST, environment.getProperty("user.login.url")).permitAll().anyRequest()
				.authenticated().and().addFilter(new AuthenticationFilter(authenticationManager(), environment));

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").exposedHeaders("token,userid").allowedMethods("GET", "PUT",
				"POST", "DELETE","PATCH");
	}

}
