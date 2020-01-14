package com.ecors.mail.service;

import com.ecors.mail.vo.request.MailRequest;

public interface MailService {

	void sendMail(final MailRequest mailRequest);

	void sendMailWithAttachment(final MailRequest mailRequest);

}
