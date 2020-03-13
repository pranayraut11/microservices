package com.ecors.api.gateway.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.ecors.api.gateway.client.UserServiceClient;
import com.ecors.core.utility.JWTUtility;

public class AuthenticationFilter extends BasicAuthenticationFilter {

	private Environment environment;
	private UserServiceClient userServiceClient;

	public AuthenticationFilter(AuthenticationManager authenticationManager, Environment environment,
			UserServiceClient userServiceClient) {
		super(authenticationManager);
		this.environment = environment;
		this.userServiceClient = userServiceClient;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String token = request.getHeader(environment.getProperty("autherization.token.header"));
		if (token == null || !token.startsWith(environment.getProperty("autherization.token.header.start"))) {
			chain.doFilter(request, response);
			return;
		}
		UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String uuid = JWTUtility.getUserId(request, environment.getProperty("token-secret"),
				environment.getProperty("autherization.token.header"),
				environment.getProperty("autherization.token.header.start"));

		if (uuid == null) {
			return null;

		}
		Boolean isLoggedIn = this.userServiceClient.isLoggedIn(uuid).getBody().getData().getResult();
		if (isLoggedIn)
			return new UsernamePasswordAuthenticationToken(uuid, null, new ArrayList<>());
		return null;
	}

}
