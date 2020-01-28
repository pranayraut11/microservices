package com.ecors.api.users.service;

import java.util.ArrayList;
import java.util.Optional;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ecors.api.users.DTO.UserDTO;
import com.ecors.api.users.entity.UserEntity;
import com.ecors.api.users.enums.MailType;
import com.ecors.api.users.exception.UserNotFoundException;
import com.ecors.api.users.repository.UserRepository;
import com.ecors.api.users.service.client.MailServiceClient;
import com.ecors.api.users.service.client.UserServiceClient;
import com.ecors.api.users.ui.request.LoginRequest;
import com.ecors.api.users.ui.request.SendMailRequest;
import com.ecors.api.users.utility.OTPGenerator;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private MailServiceClient mailServiceClient;

	@Autowired
	private UserServiceClient userServiceClient;

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

		Optional<UserEntity> userEntityOptional = userRepository.findUserByEmailID(userDto.getEmailID());

		if (userEntityOptional.isPresent()) {
			UserEntity userEntity = userEntityOptional.get();
			ModelMapper mapper = new ModelMapper();
			mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)
					.setPropertyCondition(Conditions.isNotNull());
			mapper.map(userDto, userEntity);
			userEntity.setPassword((bCryptPasswordEncoder.encode(userDto.getPassword())));
			userRepository.save(userEntity);
			SendMailRequest mailRequest = new SendMailRequest();
			mailRequest.setToAddress(userDto.getEmailID());
			mailRequest.setMailType(MailType.SIGNUP);
			mailServiceClient.sendMail(mailRequest);

			LoginRequest loginReq = new LoginRequest();
			loginReq.setEmailID(userDto.getEmailID());
			loginReq.setPassword(userDto.getPassword());
			ResponseEntity<Void> loginResponse = userServiceClient.login(loginReq);
			UserDTO userReponse = mapper.map(userEntity, UserDTO.class);
			userReponse.setToken(loginResponse.getHeaders().get("token").toString());
			return userReponse;
		}
		throw new UserNotFoundException();

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> userEntity = userRepository.findUserByEmailID(username);
		UserDetails usere = userEntity.map(user -> new User(userEntity.get().getEmailID(),
				userEntity.get().getPassword(), true, true, true, true, new ArrayList<>())).get();
		return usere;
	}

	@Override
	public UserDTO getUserByEmailID(String email) {
		Optional<UserEntity> userEntity = userRepository.findUserByEmailID(email);
		return convertUserEntityToUserDTO(userEntity);
	}

	@Override
	public UserDTO getUserByUserId(String userID) {
		Optional<UserEntity> userEntity = userRepository.findUserByUserId(userID);
		return convertUserEntityToUserDTO(userEntity);

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
		user.setEmailID(emailid);
		user.setOTP(otp);
		return convertUserEntityToUserDTO(Optional.ofNullable(userRepository.save(user)));
	}

	private UserDTO convertUserEntityToUserDTO(Optional<UserEntity> userEntity) {
		return userEntity.map(user -> new ModelMapper().map(user, UserDTO.class))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
	}

}
