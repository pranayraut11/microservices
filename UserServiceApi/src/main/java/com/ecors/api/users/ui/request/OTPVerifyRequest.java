package com.ecors.api.users.ui.request;

public class OTPVerifyRequest extends UserIdVerifyRequest {

	private String OTP;

	public String getOTP() {
		return OTP;
	}

	public void setOTP(String oTP) {
		OTP = oTP;
	}

}
