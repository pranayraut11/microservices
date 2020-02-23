package com.ecors.api.users.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String userId;

	private String password;

	private String firstName;

	private String lastName;

	@Column(name = "username")
	private String username;

	private String OTP;

	private Boolean isNewUser;

	/*
	 * @OneToMany(mappedBy = "user") private Set<UserRole> userRoles;
	 */

	@OneToMany(mappedBy = "user")
	private Set<Address> address;

	



	public Set<Address> getAddress() {
		return address;
	}

	public void setAddress(Set<Address> address) {
		this.address = address;
	}

	/*
	 * public Set<UserRole> getUserRoles() { return userRoles; }
	 * 
	 * public void setUserRoles(Set<UserRole> userRoles) { this.userRoles =
	 * userRoles; }
	 */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Boolean getIsNewUser() {
		return isNewUser;
	}

	public void setIsNewUser(Boolean isNewUser) {
		this.isNewUser = isNewUser;
	}

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserId() {
		return userId;
	}

}
