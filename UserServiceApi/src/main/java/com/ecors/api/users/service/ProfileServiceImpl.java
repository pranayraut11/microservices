package com.ecors.api.users.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import com.ecors.api.users.DTO.ProfileDTO;
import com.ecors.api.users.DTO.UserDTO;
import com.ecors.api.users.entity.UserEntity;
import com.ecors.api.users.enums.MailType;
import com.ecors.api.users.repository.UserRepository;
import com.ecors.api.users.service.client.MailServiceClient;
import com.ecors.api.users.ui.request.SendMailRequest;
import com.ecors.api.users.utility.OTPGenerator;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private UserService userService;
	@Autowired
	private MailServiceClient mailServiceClient;

	@Autowired
	private UserRepository userRepository;

	@Override
	public ProfileDTO getUserProfile(String userID) throws Exception {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDTO userDTO = null;

		userDTO = userService.getUserByUserId(userID);

		ProfileDTO profileDTO = mapper.map(userDTO, ProfileDTO.class);
		return profileDTO;
	}

	@Override
	public void createUserProfile(UserDTO createUser) {
		userService.createUser(createUser);
		SendMailRequest mailRequest = new SendMailRequest();
		mailRequest.setToAddress(createUser.getEmailID());
		mailServiceClient.sendMail(mailRequest);
	}

	@Override
	public boolean verifyOTP(String userName, int otp) {
		Assert.notNull(userName, "Invalid username");
		Assert.isTrue(otp > 0, "Invalid OTP");
		UserDTO userDTO = userService.getUserByEmailID(userName);
		if (userDTO.getOTP() == otp) {

			return true;
		}
		return false;
	}

	/**
	 * Save emailid in DB then send mail with OTP
	 */

	@Override
	public void verifyMail(String userName) {
		UserEntity user = new UserEntity();
		user.setEmailID(userName);
		user.setOTP(OTPGenerator.generate());
		userRepository.save(user);
		SendMailRequest mailRequest = new SendMailRequest();
		mailRequest.setToAddress(userName);
		mailRequest.setMailType(MailType.EMAIL_VERIFICATION);
		mailServiceClient.sendMail(mailRequest);
	}

}
