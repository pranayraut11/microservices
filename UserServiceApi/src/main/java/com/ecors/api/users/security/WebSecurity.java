package com.ecors.api.users.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ecors.api.users.repository.UserRepository;
import com.ecors.api.users.service.LoginDetailsService;
import com.ecors.api.users.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private Environment env;
	private UserService userService;
	private LoginDetailsService loginDetailsService;
	private BCryptPasswordEncoder bcrypt;
	private UserRepository userRepository;

	@Autowired
	public WebSecurity(Environment environment, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder,
			LoginDetailsService loginDetailsService, UserRepository userRepository) {
		this.env = environment;
		this.userService = userService;
		this.bcrypt = bCryptPasswordEncoder;
		this.loginDetailsService = loginDetailsService;
		this.userRepository = userRepository;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/**").hasIpAddress(env.getProperty("gateway_ip")).and()
				.addFilter(getAuthenticationFilter());
		http.headers().frameOptions().disable();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(bcrypt);
	}

	private AuthenticationFilter getAuthenticationFilter() throws Exception {
		AuthenticationFilter authenticationFilter = new AuthenticationFilter(userService, env, authenticationManager(),
				userRepository, loginDetailsService);
		authenticationFilter.setFilterProcessesUrl(env.getProperty("login-url"));
		return authenticationFilter;
	}

}
