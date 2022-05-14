package com.example.demo.responses;

import java.util.List;

public class UserResponse {
	
	private String userId;
	private String firstname;
	private String lastname;
	private String email;
	private List<AddressRespense> addresses;



	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setAddresses(List<AddressRespense> addresses) {
		this.addresses = addresses;
	}

	public List<AddressRespense> getAddresses() {
		return addresses;
	}
}
