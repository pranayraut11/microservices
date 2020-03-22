package com.ecors.api.users.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecors.core.client.MailServiceClient;
import com.ecors.core.dto.ProfileDTO;
import com.ecors.core.dto.UserDTO;
import com.ecors.core.ui.request.SendMailRequest;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private UserService userService;

	@Autowired
	private MailServiceClient mailServiceClient;

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
		mailRequest.setToAddress(createUser.getUsername());
		mailServiceClient.sendMail(mailRequest);
	}

}
