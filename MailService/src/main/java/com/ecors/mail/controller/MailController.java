package com.ecors.mail.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecors.mail.service.MailService;
import com.ecors.mail.vo.request.MailRequest;

@RestController
@RequestMapping("mail")
public class MailController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MailService mailService;

	@PostMapping("send")
	public ResponseEntity<String> sendMail(@RequestBody MailRequest mailReq) {
		mailService.sendMail(mailReq);
		LOGGER.info("Mail sent successfully");
		return ResponseEntity.status(HttpStatus.OK).body("Mail sent successfully");
	}

}
