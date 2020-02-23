package com.ecors.api.users.service;

import com.ecors.api.users.DTO.UserDTO;
import com.ecors.api.users.ui.request.OTPVerifyRequest;
import com.ecors.api.users.ui.request.UserIdVerifyRequest;

public interface VerifyService {
	public UserDTO verifyOTP(OTPVerifyRequest otpVerifyRequest);

	public void verifyMail(UserIdVerifyRequest userIdVerifyRequest);

}
