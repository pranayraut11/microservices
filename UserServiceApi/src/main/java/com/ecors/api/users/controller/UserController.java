package com.ecors.api.users.controller;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecors.api.users.entity.LoginDetails;
import com.ecors.api.users.entity.User;
import com.ecors.api.users.service.AuthenticationService;
import com.ecors.api.users.service.UserService;
import com.ecors.api.users.ui.request.CreateUserRequest;
import com.ecors.core.dto.ProfileDTO;
import com.ecors.core.dto.UserDTO;
import com.ecors.core.ui.response.GenericResponse;
import com.ecors.core.ui.response.Response;
import com.ecors.core.utility.JWTUtility;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private Environment environment;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationService authenticationService;

	@GetMapping("status/check")
	public String status() {
		return "working " + environment.getProperty("local.server.port") + " " + environment.getProperty("fortest");
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<GenericResponse<Void>> createUser(@RequestBody CreateUserRequest userReq) {

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDTO userDto = mapper.map(userReq, UserDTO.class);
		UserDTO createdUser = userService.createUser(userDto);
		GenericResponse<Void> response = null;
		if (createdUser != null) {
			response = new GenericResponse<Void>(null, "User created successfully", true);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			response = new GenericResponse<Void>(null, "User creation failed", false);
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
		}

	}

	@GetMapping("/{userID}")
	public ResponseEntity<GenericResponse<UserDTO>> getUser(@PathVariable String userID) throws Exception {

		Response<UserDTO> response = new Response<>();
		response.setResult(userService.getUserByUserId(userID));
		GenericResponse<UserDTO> genericResponse = new GenericResponse<UserDTO>(response, "Data retrived successfully",
				true);
		return ResponseEntity.status(HttpStatus.OK).body(genericResponse);
	}

	@PostMapping("signup")
	public void signup(@RequestBody CreateUserRequest userRequest) {

	}


	@GetMapping("validate")
	public ResponseEntity<GenericResponse<Void>> verifyUser() {
		GenericResponse<Void> genericResponse = new GenericResponse<Void>(null, "Verified successfully", true);
		return ResponseEntity.status(HttpStatus.OK).body(genericResponse);
	}

	@GetMapping("{uuid}/logindetails")
	public ResponseEntity<GenericResponse<LoginDetails>> logout(@PathVariable String uuid) {
		Response<LoginDetails> result = new Response<>();
		result.setResult(authenticationService.getLoginDetails(uuid));
		GenericResponse<LoginDetails> genericResponse = new GenericResponse<LoginDetails>(result,
				"Verified successfully", true);
		return ResponseEntity.status(HttpStatus.OK).body(genericResponse);
	}

	@PostMapping("logout")
	public ResponseEntity<Void> logout(HttpServletRequest request) {
		String uuid = JWTUtility.extractUUIDFromHttpRequest(request);
		authenticationService.logout(uuid);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PutMapping
	public ResponseEntity<GenericResponse<ProfileDTO>> updateUserProfile(@RequestBody ProfileDTO userProfile,
			HttpServletRequest httpServletRequest) {
		Response<ProfileDTO> result = new Response<>();
		User user = authenticationService.getUser(httpServletRequest);
		result.setResult(userService.saveOrUpdateUserProfile(userProfile, user));
		GenericResponse<ProfileDTO> genericResponse = new GenericResponse<ProfileDTO>(result, "Verified successfully",
				true);
		return ResponseEntity.status(HttpStatus.OK).body(genericResponse);
	}

}
