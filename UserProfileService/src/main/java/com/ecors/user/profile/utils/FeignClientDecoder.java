package com.ecors.user.profile.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignClientDecoder implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {

		switch (response.status()) {
		case 400:

			break;

		case 404:
			if (methodKey.contains("getUserById")) {
				return new ResponseStatusException(HttpStatus.valueOf(response.status()), "User not found");
			}
			break;

		default:
			break;
		}
		return null;
	}

}
