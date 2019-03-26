package com.java.c3s.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	@Column(name="id")
	private Long id;
	
	@Column(name = "username")
	private String userName;
	
	@Column(name = "password")
	private String password;

	public User() {
		
	}
	public User(Long id,String userName,String password) {
		this.id = id;
		this.userName = userName;
		this.password = password;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	

}
