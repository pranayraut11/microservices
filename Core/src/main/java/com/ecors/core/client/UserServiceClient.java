package com.ecors.core.client;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecors.core.dto.AddressDTO;
import com.ecors.core.dto.UserDTO;
import com.ecors.core.enums.AddressType;
import com.ecors.core.ui.request.LoginRequest;
import com.ecors.core.ui.response.GenericResponse;

import feign.FeignException;
import feign.hystrix.FallbackFactory;

@FeignClient(name = "userservice", fallbackFactory = UserServiceFallback.class)
public interface UserServiceClient {

	@PostMapping("login")
	public ResponseEntity<Void> login(@RequestBody LoginRequest login);

	@GetMapping("users")
	public ResponseEntity<GenericResponse<UserDTO>> getUser(HttpServletRequest request) throws Exception;

	@GetMapping("addresses")
	public ResponseEntity<GenericResponse<List<AddressDTO>>> getUserAddresses(@RequestParam AddressType type);
}

@Component
class UserServiceFallback implements FallbackFactory<UserServiceClient> {

	@Override
	public UserServiceClient create(Throwable cause) {
		// TODO Auto-generated method stub
		return new UserServiceFallbackService(cause);
	}

}

class UserServiceFallbackService implements UserServiceClient {

	private Throwable cause = null;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	UserServiceFallbackService(Throwable cause) {
		this.cause = cause;
	}

	@Override
	public ResponseEntity<Void> login(LoginRequest login) {
		if ((cause instanceof FeignException) && ((FeignException) cause).status() == 404) {
			logger.error(cause.getLocalizedMessage());
		} else {
			logger.error(cause.getLocalizedMessage());
		}
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@Override
	public ResponseEntity<GenericResponse<UserDTO>> getUser(HttpServletRequest request) throws Exception {
		if ((cause instanceof FeignException) && ((FeignException) cause).status() == 404) {
			logger.error(cause.getLocalizedMessage());
		} else {
			logger.error(cause.getLocalizedMessage());
		}
		return new ResponseEntity<GenericResponse<UserDTO>>(HttpStatus.OK);

	}

	@Override
	public ResponseEntity<GenericResponse<List<AddressDTO>>> getUserAddresses(AddressType type) {
		if ((cause instanceof FeignException) && ((FeignException) cause).status() == 404) {
			logger.error(cause.getLocalizedMessage());
		} else {
			logger.error(cause.getLocalizedMessage());
		}
		return new ResponseEntity<GenericResponse<List<AddressDTO>>>(HttpStatus.OK);

	}

}
