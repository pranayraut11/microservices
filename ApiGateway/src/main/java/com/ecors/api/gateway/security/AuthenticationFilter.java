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

import com.ecors.core.utility.JWTUtility;

public class AuthenticationFilter extends BasicAuthenticationFilter {

	private Environment environment;

	public AuthenticationFilter(AuthenticationManager authenticationManager, Environment environment) {
		super(authenticationManager);
		this.environment = environment;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Properties location check " + environment.getProperty("fortest"));
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
		String userID = JWTUtility.getUserId(request, environment.getProperty("token-secret"),
				environment.getProperty("autherization.token.header"),
				environment.getProperty("autherization.token.header.start"));
		if (userID == null)
			return null;
		return new UsernamePasswordAuthenticationToken(userID, null, new ArrayList<>());
	}

}
