package com.ecors.api.users.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ecors.api.users.DTO.UserDTO;
import com.ecors.api.users.enums.MailType;
import com.ecors.api.users.service.client.MailServiceClient;
import com.ecors.api.users.ui.request.OTPVerifyRequest;
import com.ecors.api.users.ui.request.SendMailRequest;
import com.ecors.api.users.ui.request.UserIdVerifyRequest;
import com.ecors.api.users.utility.OTPGenerator;

@Service
public class VerifyServiceImpl implements VerifyService {
	@Autowired
	private UserService userService;

	@Autowired
	private MailServiceClient mailServiceClient;

	private static final String OTP = "OTP";

	@Override
	public UserDTO verifyOTP(OTPVerifyRequest otpVerifyRequest) {
		Assert.notNull(otpVerifyRequest.getUsername(), "Invalid username");
		Assert.isTrue(Integer.parseInt(otpVerifyRequest.getOtp()) > 0, "Invalid OTP");
		return userService.getUserByEmailID(otpVerifyRequest.getUsername());
	}

	/**
	 * Save emailid in DB then send mail with OTP
	 */

	@Override
	public void verifyMail(UserIdVerifyRequest userIdVerifyRequest) {
		String otp = OTPGenerator.generateAsString();
		userService.createBasicUser(userIdVerifyRequest.getUsername(),otp);
		SendMailRequest mailRequest = new SendMailRequest();
		mailRequest.setToAddress(userIdVerifyRequest.getUsername());
		mailRequest.setMailType(MailType.EMAIL_VERIFICATION);
		Map<String, String> additionalInfo = new HashMap<>();
		additionalInfo.put(OTP, otp);
		mailRequest.setAdditionalInfo(additionalInfo);
		mailServiceClient.sendMail(mailRequest);
	}

}
