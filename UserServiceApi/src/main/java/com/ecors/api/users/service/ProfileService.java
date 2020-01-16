package com.ecors.api.users.service;

import com.ecors.api.users.DTO.ProfileDTO;
import com.ecors.api.users.DTO.UserDTO;

public interface ProfileService {
	public ProfileDTO getUserProfile(String userID) throws Exception;

	public void createUserProfile(UserDTO createUser);

	public boolean verifyOTP(String userName, int otp);

	public void verifyMail(String userName);
}
