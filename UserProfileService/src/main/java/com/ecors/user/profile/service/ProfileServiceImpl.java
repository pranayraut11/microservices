package com.ecors.user.profile.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecors.user.profile.DTO.ProfileDTO;
import com.ecors.user.profile.DTO.UserDTO;
import com.ecors.user.profile.service.client.MailServiceClient;
import com.ecors.user.profile.service.client.UserServiceClient;
import com.ecors.user.profile.vo.CreateUserRequest;
import com.ecors.user.profile.vo.SendMailRequest;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private UserServiceClient userServiceClient;
	@Autowired
	private MailServiceClient mailServiceClient;

	@Override
	public ProfileDTO getUserProfile(String userID) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDTO userDTO = null;

		userDTO = userServiceClient.getUserById(userID);

		ProfileDTO profileDTO = mapper.map(userDTO, ProfileDTO.class);
		return profileDTO;
	}

	@Override
	public void createUserProfile(CreateUserRequest createUser) {
		userServiceClient.createUser(createUser);
		SendMailRequest mailRequest = new SendMailRequest();
		mailRequest.setToAddress(createUser.getEmailID());
		mailServiceClient.sendMail(mailRequest);
	}

}
