package com.ecors.api.users.controller;

import javax.servlet.http.HttpServletResponse;

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
import com.ecors.core.dto.UserDTO;
import com.ecors.core.ui.response.GenericResponse;

@RestController
@RequestMapping("verify")
public class VerifyController {

	@Autowired
	private VerifyService verifyService;

	

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
	public ResponseEntity<GenericResponse<Void> > verifyMail(@RequestBody UserIdVerifyRequest userIdVerifyRequest) {
		verifyService.verifyMail(userIdVerifyRequest);
		GenericResponse<Void> genericResponse = new GenericResponse<Void>(null, "Authentication successfull", true);
		return ResponseEntity.status(HttpStatus.OK).body(genericResponse);
	}
}
