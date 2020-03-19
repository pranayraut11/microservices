package com.ecors.api.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecors.api.users.entity.LoginDetails;
import com.ecors.api.users.entity.User;
import com.ecors.api.users.repository.LoginDetailsRepository;
import com.ecors.core.exception.NotFoundException;

@Service
public class LoginDetailsServiceImpl implements LoginDetailsService {

	@Autowired
	private LoginDetailsRepository loginDetailsRepository;

	@Override
	public LoginDetails save(LoginDetails loginDetails) {
		return loginDetailsRepository.save(loginDetails);
	}

	@Override
	public LoginDetails findByUUIDAndUser(String uuid, User user) throws NotFoundException {
		return loginDetailsRepository.findByUuidAndUser(uuid, user)
				.orElseThrow(() -> new NotFoundException("Login details", uuid));
	}

}
