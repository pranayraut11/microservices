package com.ecors.api.users.service;

import com.ecors.api.users.entity.LoginDetails;
import com.ecors.api.users.entity.User;

import javassist.NotFoundException;

public interface LoginDetailsService {

	LoginDetails save(LoginDetails loginDetails);

	LoginDetails findByUUIDAndUser(String uuid, User user) throws NotFoundException;
}
