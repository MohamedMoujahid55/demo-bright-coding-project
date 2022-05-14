package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.*;

import com.example.demo.shared.dto.UserDto;

@Entity(name="addresses")
public class AddressEntity implements Serializable{

	private static final long serialVersionUID = 6798638742721221557L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length=30, nullable=false)
	private String addressId;
	
	@Column(length=20, nullable=false)
	private String city ;
	
	@Column(length=20, nullable=false)
	private String country;
	
	@Column(length=50, nullable=false)
	private String street;
	
	@Column(length=7, nullable=false)
	private String postal;
	
	@Column(length=20, nullable=false)
	private String type;
	
	@ManyToOne(targetEntity = UserEntity.class)
	@JoinColumn(name="users_id") // user_id is the foreign key here 
	private UserDto user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}
	
	
}
