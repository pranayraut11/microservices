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

@Service
public class VerifyServiceImpl implements VerifyService {
	@Autowired
	private UserService userService;

	@Autowired
	private MailServiceClient mailServiceClient;

	private static final String OTP = "OTP";

	@Override
	public boolean verifyOTP(OTPVerifyRequest otpVerifyRequest) {
		Assert.notNull(otpVerifyRequest.getEmailId(), "Invalid username");
		Assert.isTrue(Integer.parseInt(otpVerifyRequest.getOTP()) > 0, "Invalid OTP");
		UserDTO userDTO = userService.getUserByEmailID(otpVerifyRequest.getEmailId());
		if (userDTO.getOTP().equals(otpVerifyRequest.getOTP())) {
			return true;
		}
		return false;
	}

	/**
	 * Save emailid in DB then send mail with OTP
	 */

	@Override
	public void verifyMail(UserIdVerifyRequest userIdVerifyRequest) {
		UserDTO userDTO = userService.createBasicUser(userIdVerifyRequest.getUserLoginId());
		SendMailRequest mailRequest = new SendMailRequest();
		mailRequest.setToAddress(userIdVerifyRequest.getUserLoginId());
		mailRequest.setMailType(MailType.EMAIL_VERIFICATION);
		Map<String, String> additionalInfo = new HashMap<>();
		additionalInfo.put(OTP, userDTO.getOTP());
		mailRequest.setAdditionalInfo(additionalInfo);
		mailServiceClient.sendMail(mailRequest);
	}

}
