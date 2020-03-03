package com.ecors.api.users.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ecors.api.users.DTO.UserDTO;
import com.ecors.api.users.service.UserService;
import com.ecors.api.users.ui.request.CreateOrder;
import com.ecors.api.users.ui.request.CreateUserRequest;
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

	@PostMapping("/product/order")
	public ResponseEntity<GenericResponse<Void>> buyProducts(@RequestBody List<Integer> order,
			HttpServletRequest request) {

		userService.createOrder(order, getUserID(request));

		return null;

	}

	private String getUserID(HttpServletRequest request) {
		String userID = JWTUtility.getUserId(request, environment.getProperty("token-secret"),
				environment.getProperty("autherization.token.header"),
				environment.getProperty("autherization.token.header.prefix"));
		if (userID == null) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to perform this action");
		}
		return userID;
	}

}
