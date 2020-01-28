package com.ecors.api.users.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ecors.api.users.DTO.UserDTO;

public interface UserService extends UserDetailsService {

	UserDTO createUser(UserDTO userDto);

	UserDTO getUserByEmailID(String email);

	UserDTO getUserByUserId(String userId) throws Exception;

	UserDTO createBasicUser(String userID);

}
