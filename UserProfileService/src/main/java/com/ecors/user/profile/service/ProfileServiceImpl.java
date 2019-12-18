package com.ecors.user.profile.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecors.user.profile.DTO.ProfileDTO;
import com.ecors.user.profile.DTO.UserDTO;
import com.ecors.user.profile.service.client.UserServiceClient;

@Service
public class ProfileServiceImpl implements ProfileService {

	// private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserServiceClient userServiceClient;

	@Override
	public ProfileDTO getUserProfile(String userID) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDTO userDTO = null;

		userDTO = userServiceClient.getUserById(userID);

		ProfileDTO profileDTO = mapper.map(userDTO, ProfileDTO.class);
		return profileDTO;
	}

}
