package com.ecors.api.users.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.ecors.api.users.entity.LoginDetails;
import com.ecors.api.users.entity.User;
import com.ecors.api.users.repository.LoginDetailsRepository;
import com.ecors.core.utility.JWTUtility;

@Component
public class AuthenticationService {

	@Autowired
	private Environment environment;

	@Autowired
	private LoginDetailsRepository loginDetailsRepository;

	public String getUUID(HttpServletRequest request) {
		String uuid = JWTUtility.getUserId(request, environment.getProperty("token-secret"),
				environment.getProperty("autherization.token.header"),
				environment.getProperty("autherization.token.header.prefix"));
		if (uuid == null) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to perform this action");
		}
		return uuid;
	}

	public User getUserFromUUID(String uuid) {
		return getLoginDetails(uuid).getUser();
	}

	public LoginDetails getLoginDetails(String uuid) {
		Optional<LoginDetails> loginDetails = loginDetailsRepository.findByUuid(uuid);
		Assert.isTrue(loginDetails.isPresent(), "uuid not found");
		return loginDetails.get();
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
