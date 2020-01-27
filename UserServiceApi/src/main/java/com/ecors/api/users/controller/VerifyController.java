package com.ecors.api.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecors.api.users.service.VerifyService;
import com.ecors.api.users.ui.request.OTPVerifyRequest;
import com.ecors.api.users.ui.request.UserIdVerifyRequest;

@RestController
@RequestMapping("verify")
public class VerifyController {

	@Autowired
	private VerifyService verifyService;

	@PostMapping("otp")
	public ResponseEntity<String> verifyOTP(@RequestBody OTPVerifyRequest otpRequest) {
		if (verifyService.verifyOTP(otpRequest))
			return ResponseEntity.status(HttpStatus.OK).body("Authentication successfull");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You entered wrong OTP");
	}

	@PostMapping("mail")
	public ResponseEntity<String> verifyMail(@RequestBody UserIdVerifyRequest userIdVerifyRequest) {
		verifyService.verifyMail(userIdVerifyRequest);
		return ResponseEntity.status(HttpStatus.OK).body("Please check your email for OTP");
	}
}
