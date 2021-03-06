package com.ecors.user.profile.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecors.user.profile.service.ProfileService;
import com.ecors.user.profile.ui.response.UserProfileResponse;
import com.ecors.user.profile.vo.CreateUserRequest;

@RestController
@RequestMapping("profile")
public class ProfileController {

	@Autowired
	private ProfileService profileService;

	@Autowired
	private Environment env;

	@GetMapping("/{userID}")
	public UserProfileResponse getProfile(@PathVariable String userID) {

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserProfileResponse userProfile = mapper.map(profileService.getUserProfile(userID), UserProfileResponse.class);
		return userProfile;
	}

	@PostMapping("create")
	public ResponseEntity<String> create(@RequestBody CreateUserRequest createUserRequest) {
		System.out.println(env.getProperty("testPT"));
		profileService.createUserProfile(createUserRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body("Please check your email");
	}

	@GetMapping("verify")
	public ResponseEntity<String> verifyOTP(@PathVariable String userName, @PathVariable int otp) {
		if (profileService.verifyOTP(userName, otp))
			return ResponseEntity.status(HttpStatus.OK).body("Authentication successfull");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You entered wrong OTP");
	}

}
