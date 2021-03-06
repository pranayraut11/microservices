package com.ecors.api.users.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecors.api.users.DTO.UserContext;
import com.ecors.api.users.entity.User;
import com.ecors.api.users.entity.UserRole;
import com.ecors.api.users.repository.UserRepository;
import com.ecors.core.client.MailServiceClient;
import com.ecors.core.dto.AddressDTO;
import com.ecors.core.dto.ProfileDTO;
import com.ecors.core.dto.UserDTO;
import com.ecors.core.enums.MailType;
import com.ecors.core.enums.UserType;
import com.ecors.core.exception.NotFoundException;
import com.ecors.core.ui.request.SendMailRequest;
import com.ecors.core.utility.ModelMapperUtils;

@Service
public class UserServiceImpl implements UserService {


	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private MailServiceClient mailServiceClient;

	@Autowired
	private AddressService addressService;



	@Autowired
	public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
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
		User userEntity = getUserByUsername(userDto.getUsername());
		ModelMapperUtils.map(userDto, userEntity);
		userEntity.setPassword((bCryptPasswordEncoder.encode(userDto.getPassword())));
		userRepository.save(userEntity);
		SendMailRequest mailRequest = new SendMailRequest();
		mailRequest.setToAddress(userDto.getUsername());
		mailRequest.setMailType(MailType.SIGNUP);
		mailServiceClient.sendMail(mailRequest);
		return ModelMapperUtils.map(userEntity, UserDTO.class);
	}

	private User getUserByUsername(String username) {
		Optional<User> userEntityOptional = userRepository.findUserByUsername(username);
		if (userEntityOptional.isPresent()) {
			return userEntityOptional.get();
		}
		throw new NotFoundException("User");
	}

	@Override
	@Transactional
	public UserContext loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userEntity = userRepository.findUserByUsername(username);
		String password = null;
		if (userEntity.isPresent()) {
			User user = userEntity.get();

			if (user.getOTP() != null && !user.getOTP().isEmpty()) {
				password = user.getOTP();
			} else {
				password = user.getPassword();
			}
			List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
			Set<UserRole> roles = user.getUserRoles();
			list.add(new SimpleGrantedAuthority("ROLE_" + roles.stream().findFirst().get().getType().name()));

			return new UserContext(userEntity.get().getUsername(), password, true, true, true, true, list,
					user.getUserId());
		}
		return null;

	}

	@Override
	public UserDTO getUserByEmailID(String email) {
		Optional<User> userEntity = userRepository.findUserByUsername(email);
		if (userEntity.isPresent()) {
			return ModelMapperUtils.map(userEntity.get(), UserDTO.class);
		}
		throw new NotFoundException("User");
	}

	@Override
	public UserDTO getUserByUserId(String userID) {
		UserDTO userDTO = ModelMapperUtils.map(getUser(userID), UserDTO.class);
		userDTO.setAddress(ModelMapperUtils.map(addressService.getAllByUserId(getUser(userID)), AddressDTO.class));
		return userDTO;
	}

	/**
	 * Creates user in database using provided emailID only.
	 * 
	 * @param emailid
	 * @return User's basic data userId,OTP,emailID as username
	 */
	public UserDTO createBasicUser(String emailid, String otp) {
		String encodedOTP = bCryptPasswordEncoder.encode(otp);
		Optional<User> userEntity = userRepository.findUserByUsername(emailid);
		if (userEntity.isPresent()) {
			userEntity.get().setOTP(encodedOTP);
			User user = userRepository.save(userEntity.get());
			return ModelMapperUtils.map(userRepository.save(user), UserDTO.class);
		}
		User user = new User();
		user.setUsername(emailid);
		UserRole role = new UserRole();
		role.setType(UserType.USER);
		role.setUser(user);
		user.setOTP(encodedOTP);
		Set<UserRole> roles = new HashSet<>();
		roles.add(role);
		user.setUserId(UUID.randomUUID().toString());
		user.setUserRoles(roles);
		return ModelMapperUtils.map(userRepository.save(user), UserDTO.class);
	}

	public User getUser(String userId) {
		Optional<User> userEntity = userRepository.findUserByUserId(userId);
		if (userEntity.isPresent()) {
			return userEntity.get();
		}
		throw new NotFoundException("User");
	}

	public User getUserFromUUID(String userId) {
		Optional<User> userEntity = userRepository.findUserByUserId(userId);
		if (userEntity.isPresent()) {
			return userEntity.get();
		}
		throw new NotFoundException("User");
	}

	@Override
	public List<AddressDTO> getAddressesByUser(String userId) {
		return addressService.getAllByUserId(getUser(userId));
	}

	

	@Override
	public ProfileDTO saveOrUpdateUserProfile(ProfileDTO profileDto, User user) {
		ModelMapperUtils.map(profileDto, user);
		userRepository.save(user);
		return ModelMapperUtils.map(user, profileDto);
	}

}
