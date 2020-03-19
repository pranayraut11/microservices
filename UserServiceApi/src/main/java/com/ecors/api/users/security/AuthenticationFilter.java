package com.ecors.api.users.security;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ecors.api.users.DTO.UserContext;
import com.ecors.api.users.entity.LoginDetails;
import com.ecors.api.users.entity.User;
import com.ecors.api.users.repository.LoginDetailsRepository;
import com.ecors.api.users.repository.UserRepository;
import com.ecors.api.users.service.LoginDetailsService;
import com.ecors.api.users.service.UserService;
import com.ecors.api.users.ui.request.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.lang.Assert;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private Environment environment;
	private UserRepository userRepository;
	private LoginDetailsService loginDetailsService;

	public AuthenticationFilter(UserService userService, Environment environment,
			AuthenticationManager authenticationManager, UserRepository userRepository,
			LoginDetailsService loginDetailsService) {
		this.environment = environment;
		this.loginDetailsService = loginDetailsService;
		this.userRepository = userRepository;
		super.setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			LoginRequest loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
			if (loginRequest.getOtp() != null && !loginRequest.getOtp().isEmpty()) {
				return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
						loginRequest.getUsername(), loginRequest.getOtp(), new ArrayList<>()));
			}
			return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
					loginRequest.getUsername(), loginRequest.getPassword(), new ArrayList<>()));

		} catch (IOException e) {
			throw new RuntimeException();
		}

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		UserContext context = ((UserContext) authResult.getPrincipal());
		Optional<User> user = userRepository.findUserByUsername(context.getUsername());
		Assert.isTrue(user.isPresent(), "User not found");
		String uuid = UUID.randomUUID().toString();

		// Save login details in database with uuid

		String token = Jwts.builder().setSubject(uuid)
				.setExpiration(new Date(System.currentTimeMillis()
						+ Long.parseLong(environment.getProperty("jwt-token-expiration-time"))))
				.signWith(SignatureAlgorithm.HS256, environment.getProperty("token-secret")).compact();
		loginDetailsService.save(createLoginDetails(user.get(), uuid, token));
		response.setHeader("token", uuid);
	}

	private LoginDetails createLoginDetails(User user, String uuid, String token) {
		LoginDetails loginDetails = new LoginDetails();
		loginDetails.setLoggedIn(true);
		loginDetails.setLoginTime(LocalDateTime.now());
		loginDetails.setUser(user);
		loginDetails.setUuid(uuid);
		loginDetails.setToken(token);
		return loginDetails;
	}
}
