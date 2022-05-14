package com.example.demo.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

public class ContactEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 30)
    private String contactId;

    @NotBlank
    private String mobile;

    private String skype;

    /*@OneToOne
    @JoinColumn(name = "users_id")
    private UserEntity user;*/


    public Long getId() {
        return id;
    }

    public String getContactId() {
        return contactId;
    }

    public String getMobile() {
        return mobile;
    }

    public String getSkype() {
        return skype;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

   /* public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }*/
}
