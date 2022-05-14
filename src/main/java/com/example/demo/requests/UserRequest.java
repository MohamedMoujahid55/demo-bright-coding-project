package com.example.demo.requests;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRequest {
	
	@NotBlank(message="This field can not be null !")
	@Size(min=3, message="Firstname should be min 3 caracters !")
	private String firstname;
	
	@NotBlank(message="This field can not be null !")
	@Size(min=3, message="Firstname should be min 3 caracters !")
	private String lastname;
	
	@NotBlank(message="This field can not be null !")
	@Email(message="Email format required example@gmail.com ! ")
	private String email;
	
	@NotBlank(message="This field can not be null !")
	@Size(min=4, max=10, message="Password should be between 4 and 10 caracteres")
	private String password;
	
	private List<AddressRequest> addresses;
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<AddressRequest> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<AddressRequest> addresses) {
		this.addresses = addresses;
	}
	
	
}
