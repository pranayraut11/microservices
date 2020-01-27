package com.ecors.api.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecors.api.users.service.ProfileService;

@RestController
@RequestMapping("verify")
public class VerifyController {

	@Autowired
	private ProfileService profileService;

	@GetMapping("otp")
	public ResponseEntity<String> verifyOTP(@PathVariable String userName, @PathVariable String otp) {
		if (profileService.verifyOTP(userName, otp))
			return ResponseEntity.status(HttpStatus.OK).body("Authentication successfull");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You entered wrong OTP");
	}

	@PostMapping("mail")
	public ResponseEntity<String> verifyMail(@RequestBody String userName) {
		profileService.verifyMail(userName);
		return ResponseEntity.status(HttpStatus.OK).body("Please check your email for OTP");
	}
}
