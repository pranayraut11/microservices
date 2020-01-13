package com.ecors.api.users.service;

import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ecors.api.users.DTO.UserDTO;
import com.ecors.api.users.entity.UserEntity;
import com.ecors.api.users.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;

	}

	@Override
	public UserDTO createUser(UserDTO userDto) {
		userDto.setUserID(UUID.randomUUID().toString());
		userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity = mapper.map(userDto, UserEntity.class);
		userRepository.save(userEntity);
		return mapper.map(userEntity, UserDTO.class);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findUserByEmailID(username);
		return new User(userEntity.getEmailID(), userEntity.getPassword(), true, true, true, true, new ArrayList<>());
	}

	@Override
	public UserDTO getUserByEmailID(String email) {
		UserEntity userEntity = userRepository.findUserByEmailID(email);
		return new ModelMapper().map(userEntity, UserDTO.class);
	}

	@Override
	public UserDTO getUserByUserID(String userID) {
		UserEntity userEntity = userRepository.findUserByUserID(userID);
		if (userEntity != null)
			return new ModelMapper().map(userEntity, UserDTO.class);
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
	}

}
