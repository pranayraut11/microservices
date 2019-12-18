package com.ecors.user.profile.service.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecors.user.profile.DTO.UserDTO;

import feign.FeignException;
import feign.hystrix.FallbackFactory;

@FeignClient(name = "userservice", fallbackFactory = UserServiceFallbackFactory.class)
public interface UserServiceClient {

	@GetMapping("/users/{userID}")
	public UserDTO getUserById(@PathVariable String userID);

}

@Component
class UserServiceFallbackFactory implements FallbackFactory<UserServiceClient> {

	@Override
	public UserServiceClient create(Throwable cause) {
		// TODO Auto-generated method stub
		return new UserServiceFallback(cause);
	}

}

class UserServiceFallback implements UserServiceClient {
	private final Throwable cause;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	UserServiceFallback(Throwable cause) {
		this.cause = cause;
	}

	@Override
	public UserDTO getUserById(String userID) {

		if ((cause instanceof FeignException) && ((FeignException) cause).status() == 404) {
			logger.error(cause.getLocalizedMessage());
		}else {
			logger.error(cause.getLocalizedMessage());
		}
		// TODO Auto-generated method stub
		return new UserDTO();
	}

}
