package com.ecors.api.users.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.ecors.api.users.DTO.UserDTO;
import com.ecors.api.users.entity.UserEntity;
import com.ecors.api.users.enums.MailType;
import com.ecors.api.users.repository.UserRepository;
import com.ecors.api.users.service.client.MailServiceClient;
import com.ecors.api.users.ui.request.OTPVerifyRequest;
import com.ecors.api.users.ui.request.SendMailRequest;
import com.ecors.api.users.ui.request.UserIdVerifyRequest;
import com.ecors.api.users.utility.OTPGenerator;

public class VerifyServiceImpl implements VerifyService {
	@Autowired
	private UserService userService;

	@Autowired
	private MailServiceClient mailServiceClient;

	private static final String OTP = "OTP";

	@Autowired
	private UserRepository userRepository;

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
		String otp = OTPGenerator.generateAsString();
		UserEntity user = new UserEntity();
		user.setEmailID(userIdVerifyRequest.getUserLoginId());
		user.setOTP(otp);
		userRepository.save(user);
		SendMailRequest mailRequest = new SendMailRequest();
		mailRequest.setToAddress(userIdVerifyRequest.getUserLoginId());
		mailRequest.setMailType(MailType.EMAIL_VERIFICATION);
		Map<String, String> additionalInfo = new HashMap<>();
		additionalInfo.put(OTP, otp);
		mailRequest.setAdditionalInfo(additionalInfo);
		mailServiceClient.sendMail(mailRequest);
	}

}
