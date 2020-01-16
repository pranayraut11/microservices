package com.ecors.api.users.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecors.api.users.DTO.UserDTO;
import com.ecors.api.users.service.UserService;
import com.ecors.api.users.ui.request.CreateUserRequest;
import com.ecors.api.users.ui.response.CreateUserResponse;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private Environment environment;

	@Autowired
	private UserService userService;

	@GetMapping("status/check")
	public String status() {
		return "working " + environment.getProperty("local.server.port") + " " + environment.getProperty("fortest");
	}

	@PostMapping(name = "", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest userReq) {

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDTO userDto = mapper.map(userReq, UserDTO.class);
		UserDTO createdUser = userService.createUser(userDto);
		CreateUserResponse userResponse = mapper.map(createdUser, CreateUserResponse.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);

	}

	@GetMapping("/{userID}")
	public UserDTO getUser(@PathVariable String userID) throws Exception {

		return userService.getUserByUserId(userID);
	}

	@GetMapping("/{username}")
	public UserDTO getUserByUsername(@PathVariable String username) throws Exception {
		return userService.getUserByEmailID(username);
	}

}
