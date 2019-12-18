package com.ecors.api.users.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ecors.api.users.DTO.UserDTO;

public interface UserService extends UserDetailsService {

	UserDTO createUser(UserDTO userDto);

	UserDTO getUserByEmailID(String email);

	UserDTO getUserByUserID(String userID) throws Exception;

}
