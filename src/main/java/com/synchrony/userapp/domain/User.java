package com.synchrony.userapp.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name="USER_ID")
    private String userId;

    @Column(name="USER_NAME",nullable = false)
    private String userName;

    @Column(name="PASSWORD",nullable = false)
    private String password;

    @Column(name = "CREATED_ON")
    private Date createdOn;

    @OneToMany(mappedBy="user",fetch= FetchType.LAZY,cascade = CascadeType.PERSIST)
    private List<Image> image = new ArrayList<Image>();

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
