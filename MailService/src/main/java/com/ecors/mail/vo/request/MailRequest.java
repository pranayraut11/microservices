package com.ecors.mail.vo.request;

import com.ecors.mail.enums.MailType;

public class MailRequest {

	private String toAddress;
	private MailType mailType;

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public MailType getMailType() {
		return mailType;
	}

	public void setMailType(MailType mailType) {
		this.mailType = mailType;
	}

}
