package com.ecors.api.users.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecors.api.users.service.AuthenticationService;
import com.ecors.api.users.service.VerifyService;
import com.ecors.api.users.ui.request.OTPVerifyRequest;
import com.ecors.api.users.ui.request.UserIdVerifyRequest;
import com.ecors.core.dto.UserDTO;
import com.ecors.core.ui.response.GenericResponse;
import com.ecors.core.utility.JWTUtility;

@RestController
@RequestMapping("authentication")
public class AuthController {

	@Autowired
	private VerifyService verifyService;

	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("otp")
	public ResponseEntity<GenericResponse<Void>> verifyOTP(@RequestBody OTPVerifyRequest otpRequest,
			HttpServletResponse response) {
		UserDTO userDTO = verifyService.verifyOTP(otpRequest);
		if (userDTO.getOtp().equals(otpRequest.getOtp())) {
			GenericResponse<Void> genericResponse = new GenericResponse<Void>(null, "Authentication successfull", true);
			return ResponseEntity.status(HttpStatus.OK).body(genericResponse);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

	}

	@PostMapping("userLoginID")
	public ResponseEntity<GenericResponse<Void>> verifyEmailId(@RequestBody UserIdVerifyRequest userIdVerifyRequest) {
		verifyService.verifyMail(userIdVerifyRequest);
		GenericResponse<Void> genericResponse = new GenericResponse<Void>(null, "Authentication successfull", true);
		return ResponseEntity.status(HttpStatus.OK).body(genericResponse);
	}

	@PostMapping("logout")
	public ResponseEntity<Void> logout(HttpServletRequest request) {
		String uuid = JWTUtility.extractUUIDFromHttpRequest(request);
		authenticationService.logout(uuid);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping("validate")
	public ResponseEntity<GenericResponse<Void>> verifyUser() {
		GenericResponse<Void> genericResponse = new GenericResponse<Void>(null, "Verified successfully", true);
		return ResponseEntity.status(HttpStatus.OK).body(genericResponse);
	}

}
