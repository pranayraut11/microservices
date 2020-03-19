package com.ecors.api.gateway.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecors.core.dto.LoginDetailsDTO;
import com.ecors.core.ui.response.GenericResponse;

import feign.FeignException;
import feign.hystrix.FallbackFactory;

@FeignClient(name = "userservice")
public interface UserServiceClient {
	@GetMapping("{uuid}/logindetails")
	public ResponseEntity<GenericResponse<LoginDetailsDTO>> getLoginDetails(@PathVariable String uuid);

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
		public ResponseEntity<GenericResponse<LoginDetailsDTO>> getLoginDetails(String uuid) {
			if ((cause instanceof FeignException) && ((FeignException) cause).status() == 404) {
				logger.error(cause.getLocalizedMessage());
			} else {
				logger.error(cause.getLocalizedMessage());
			}
			return new ResponseEntity<GenericResponse<LoginDetailsDTO>>(HttpStatus.OK);
		}
	}
}