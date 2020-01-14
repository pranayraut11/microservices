package com.ecors.mail.service;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ecors.mail.vo.request.MailRequest;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void sendMail(MailRequest mailRequest) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(mailRequest.getToAddress());
		message.setSubject("Sample Subject");
		message.setText("OTP for Signup : " + RandomUtils.nextInt(9999));
		mailSender.send(message);
	}

	@Override
	public void sendMailWithAttachment(MailRequest mailRequest) {
		// TODO Auto-generated method stub

	}

}
