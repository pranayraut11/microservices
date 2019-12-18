package com.ecors.user.profile.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecors.user.profile.service.ProfileService;
import com.ecors.user.profile.ui.response.UserProfileResponse;

@RestController
@RequestMapping("profile")
public class ProfileController {

	@Autowired
	private ProfileService profileService;

	@GetMapping("/{userID}")
	public UserProfileResponse getProfile(@PathVariable String userID) {

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserProfileResponse userProfile = mapper.map(profileService.getUserProfile(userID), UserProfileResponse.class);
		return userProfile;
	}

}
