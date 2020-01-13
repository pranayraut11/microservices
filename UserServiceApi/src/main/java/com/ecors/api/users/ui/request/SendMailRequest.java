package com.ecors.api.users.ui.request;

import com.ecors.api.users.enums.MailType;

public class SendMailRequest {
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
