package com.ecors.api.users.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "User", uniqueConstraints = @UniqueConstraint(columnNames = "userId"))
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;

	@Column(name = "password")
	private String password;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "emailId")
	private String emailID;

	private String OTP;

	public String getOTP() {
		return OTP;
	}

	public void setOTP(String oTP) {
		OTP = oTP;
	}

	@OneToMany(mappedBy = "user")
	Set<LoginDetails> loginDetails;

	public Set<LoginDetails> getLoginDetails() {
		return loginDetails;
	}

	public void setLoginDetails(Set<LoginDetails> loginDetails) {
		this.loginDetails = loginDetails;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
