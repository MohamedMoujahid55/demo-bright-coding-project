package com.example.demo.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity(name="users")
public class UserEntity implements Serializable{

	private static final long serialVersionUID = -9146487759870644513L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false)
	private String userId;
	
	@Column(nullable=false, length=50)
	private String firstname;
	
	@Column(nullable=false, length=50)
	private String lastname;
	
	@Column(nullable=false, length = 120, unique=true)
	private String email;
	
	@Column(nullable=false)
	private String encryptedPassword;
	
	@Column(nullable=true)
	private String emailVerficationToken;
	
	@Column(nullable=false)
	private Boolean emailVerficationstatus = false;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL) //cascade make us apply (create, modify, delete) on the user table
	private List<AddressEntity> addresses;

	//@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	//private ContactEntity contact;



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getEmailVerficationToken() {
		return emailVerficationToken;
	}

	public void setEmailVerficationToken(String emailVerficationToken) {
		this.emailVerficationToken = emailVerficationToken;
	}

	public Boolean getEmailVerficationstatus() {
		return emailVerficationstatus;
	}

	public void setEmailVerficationstatus(Boolean emailVerficationstatus) {
		this.emailVerficationstatus = emailVerficationstatus;
	}

	public List<AddressEntity> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressEntity> addresses) {
		this.addresses = addresses;
	}

	/*public void setContact(ContactEntity contact) {
		this.contact = contact;
	}

	public ContactEntity getContact() {
		return contact;
	}*/
}
