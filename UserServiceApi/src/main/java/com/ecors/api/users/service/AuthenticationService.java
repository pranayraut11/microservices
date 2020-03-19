package com.ecors.api.users.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.ecors.api.users.entity.LoginDetails;
import com.ecors.api.users.entity.User;
import com.ecors.api.users.repository.LoginDetailsRepository;
import com.ecors.core.exception.NotFoundException;
import com.ecors.core.utility.JWTUtility;

@Component
public class AuthenticationService {

	@Autowired
	private Environment environment;

	@Autowired
	private LoginDetailsRepository loginDetailsRepository;

	public User getUser(HttpServletRequest request) {
		String uuid = JWTUtility.extractUUIDFromHttpRequest(request,
				environment.getProperty("autherization.token.header"),
				environment.getProperty("autherization.token.header.prefix"));
		return getLoginDetails(uuid).getUser();

	}

	public User getUserFromUUID(String uuid) {
		return getLoginDetails(uuid).getUser();
	}

	public LoginDetails getLoginDetails(String uuid) {
		return loginDetailsRepository.findByUuid(uuid).orElseThrow(() -> new NotFoundException("uuid not found"));
	}

	public void logout(String uuid) {
		LoginDetails details = getLoginDetails(uuid);
		details.setLoggedIn(false);
		loginDetailsRepository.save(details);
	}

	public boolean isLoggedIn(String uuid) {
		return getLoginDetails(uuid).isLoggedIn();
	}
}
