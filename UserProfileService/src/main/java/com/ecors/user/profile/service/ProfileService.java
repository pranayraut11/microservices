package com.ecors.user.profile.service;

import com.ecors.user.profile.DTO.ProfileDTO;
import com.ecors.user.profile.vo.CreateUserRequest;

public interface ProfileService {
	public ProfileDTO getUserProfile(String userID);

	public void createUserProfile(CreateUserRequest createUser);

}
