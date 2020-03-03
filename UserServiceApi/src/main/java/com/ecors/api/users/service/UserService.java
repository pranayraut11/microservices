package com.ecors.api.users.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ecors.api.users.DTO.AddressDTO;
import com.ecors.api.users.DTO.UserDTO;
import com.ecors.api.users.entity.User;

public interface UserService extends UserDetailsService {

	UserDTO createUser(UserDTO userDto);

	UserDTO getUserByEmailID(String email);

	UserDTO getUserByUserId(String userId) throws Exception;

	UserDTO createBasicUser(String userID,String otp);

	List<AddressDTO> getAddressesByUser(String userId);

	User getUser(String userId);

	void createOrder(List<Integer> productIds,String userId);
}
