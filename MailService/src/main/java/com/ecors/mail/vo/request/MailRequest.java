package com.ecors.mail.vo.request;

import java.util.Map;

import com.ecors.mail.enums.MailType;

public class MailRequest {

	private String toAddress;
	private MailType mailType;
	private Map<String, String> additionalInfo;

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

	public Map<String, String> getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(Map<String, String> additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

}
