package com.ecors.user.profile.service.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecors.user.profile.vo.SendMailRequest;

import feign.FeignException;
import feign.hystrix.FallbackFactory;

@FeignClient(name = "mailservice", fallbackFactory = MailServiceFallbackService.class)
public interface MailServiceClient {

	@PostMapping("sendMail")
	ResponseEntity<String> sendMail(@RequestBody SendMailRequest mailRequest);
}

@Component
class MailServiceFallbackService implements FallbackFactory<MailServiceClient> {

	@Override
	public MailServiceClient create(Throwable cause) {
		// TODO Auto-generated method stub
		return new MailServiceClientService(cause);
	}

}

class MailServiceClientService implements MailServiceClient {

	private Throwable cause;
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public MailServiceClientService(Throwable cause) {
		this.cause = cause;
	}

	@Override
	public ResponseEntity<String> sendMail(SendMailRequest mailRequest) {
		if (cause instanceof FeignException) {
			LOGGER.error(cause.getLocalizedMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Service not running");
	}

}
