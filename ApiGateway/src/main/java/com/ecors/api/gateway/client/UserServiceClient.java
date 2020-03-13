package com.ecors.api.gateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecors.core.ui.response.GenericResponse;

@FeignClient(name = "userservice")
public interface UserServiceClient {
	@GetMapping("{uuid}/isloggedIn")
	public ResponseEntity<GenericResponse<Boolean>> isLoggedIn(@PathVariable String uuid);

}
