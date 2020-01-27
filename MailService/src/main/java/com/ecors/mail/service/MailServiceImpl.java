package com.ecors.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ecors.mail.vo.request.MailRequest;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender mailSender;

	private static final String OTP = "OTP";

	@Override
	public void sendMail(MailRequest mailRequest) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(mailRequest.getToAddress());
		message.setSubject("Sample Subject");
		Assert.notNull(mailRequest.getAdditionalInfo().get(OTP), "OTP must not be null");
		message.setText("OTP for Signup : " + mailRequest.getAdditionalInfo().get(OTP));
		mailSender.send(message);
	}

	@Override
	public void sendMailWithAttachment(MailRequest mailRequest) {
		// TODO Auto-generated method stub

	}

}
