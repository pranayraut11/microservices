package com.ecors.api.users.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecors.api.users.DTO.UserDTO;
import com.ecors.api.users.entity.UserEntity;
import com.ecors.api.users.enums.MailType;
import com.ecors.api.users.exception.UserNotFoundException;
import com.ecors.api.users.repository.UserRepository;
import com.ecors.api.users.service.client.MailServiceClient;
import com.ecors.api.users.ui.request.SendMailRequest;
import com.ecors.api.users.utility.OTPGenerator;
import com.ecors.core.utility.ModelMapperUtils;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private MailServiceClient mailServiceClient;

	@Autowired
	private UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;

	}

	@Value("${default.password}")
	private String defaultPassword;

	/**
	 * Create user details in DB , first checks if user is exists or not
	 * 
	 */

	@Override
	public UserDTO createUser(UserDTO userDto) {

		Optional<UserEntity> userEntityOptional = userRepository.findUserByUsername(userDto.getUsername());

		if (userEntityOptional.isPresent()) {
			UserEntity userEntity = userEntityOptional.get();
			ModelMapperUtils.map(userDto, userEntity);
			userEntity.setPassword((bCryptPasswordEncoder.encode(userDto.getPassword())));
			userRepository.save(userEntity);
			SendMailRequest mailRequest = new SendMailRequest();
			mailRequest.setToAddress(userDto.getUsername());
			mailRequest.setMailType(MailType.SIGNUP);
			mailServiceClient.sendMail(mailRequest);
			return ModelMapperUtils.map(userEntity, UserDTO.class);

		}
		throw new UserNotFoundException();

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> userEntity = userRepository.findUserByUsername(username);
		UserDetails usere = userEntity.map(user -> new User(userEntity.get().getUsername(),
				userEntity.get().getPassword(), true, true, true, true, new ArrayList<>())).get();
		return usere;
	}

	@Override
	public UserDTO getUserByEmailID(String email) {
		Optional<UserEntity> userEntity = userRepository.findUserByUsername(email);
		return ModelMapperUtils.map(userEntity.get(), UserDTO.class);
	}

	@Override
	public UserDTO getUserByUserId(String userID) {
		Optional<UserEntity> userEntity = userRepository.findUserByUserId(userID);
		return ModelMapperUtils.map(userEntity.get(), UserDTO.class);

	}

	/**
	 * Creates user in database using provided emailID only.
	 * 
	 * @param emailid
	 * @return User's basic data userId,OTP,emailID as username
	 */
	public UserDTO createBasicUser(String emailid) {
		String otp = OTPGenerator.generateAsString();
		UserEntity user = new UserEntity();
		user.setUsername(emailid);
		user.setOTP(otp);
		user.setUserId(UUID.randomUUID().toString());
		return ModelMapperUtils.map(userRepository.save(user), UserDTO.class);
	}

}
