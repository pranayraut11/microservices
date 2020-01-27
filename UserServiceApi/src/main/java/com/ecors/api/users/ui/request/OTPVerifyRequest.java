package com.ecors.api.users.ui.request;

public class OTPVerifyRequest {

	private String emailId;
	private String OTP;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getOTP() {
		return OTP;
	}

	public void setOTP(String oTP) {
		OTP = oTP;
	}

}
