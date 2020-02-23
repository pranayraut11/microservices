package com.ecors.api.users.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class LoginDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer loginDetailsId;

	@ManyToOne
	@JoinColumn(name = "id")
	private User user;

	private LocalDateTime loginTime;
	private LocalDateTime loginOutTime;

	public Integer getLoginDetailsId() {
		return loginDetailsId;
	}

	public void setLoginDetailsId(Integer loginDetailsId) {
		this.loginDetailsId = loginDetailsId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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

}
