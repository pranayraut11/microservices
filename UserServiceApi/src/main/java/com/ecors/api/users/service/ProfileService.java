package com.ecors.api.users.service;

import com.ecors.core.dto.ProfileDTO;
import com.ecors.core.dto.UserDTO;

public interface ProfileService {
	public ProfileDTO getUserProfile(String userID) throws Exception;

	public void createUserProfile(UserDTO createUser);

}
