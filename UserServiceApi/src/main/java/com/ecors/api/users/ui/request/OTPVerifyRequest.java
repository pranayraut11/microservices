package com.ecors.api.users.ui.request;

public class OTPVerifyRequest extends UserIdVerifyRequest {

	private String otp;

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	

}
