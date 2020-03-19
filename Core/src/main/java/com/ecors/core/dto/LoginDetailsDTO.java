package com.ecors.core.dto;

import java.time.LocalDateTime;

public class LoginDetailsDTO {
	private LocalDateTime loginTime;
	private LocalDateTime loginOutTime;
	private String uuid;
	private boolean isLoggedIn;
	private String token;

	public LocalDateTime getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(LocalDateTime loginTime) {
		this.loginTime = loginTime;
	}

	public LocalDateTime getLoginOutTime() {
		return loginOutTime;
	}

	public void setLoginOutTime(LocalDateTime loginOutTime) {
		this.loginOutTime = loginOutTime;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
