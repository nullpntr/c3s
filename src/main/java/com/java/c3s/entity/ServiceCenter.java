package com.java.c3s.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.java.c3s.constant.ServiceCenterType;

@Entity(name="servicecenter")
public class ServiceCenter {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="address")
	private String address;
	
	@Column(name="type")
	private ServiceCenterType type;

	public ServiceCenter(){
		
	}
	
	public ServiceCenter(Long id,String name,String address,ServiceCenterType type){
		this.id = id;
		this.name = name;
		this.address = address;
		this.type = type;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ServiceCenterType getType() {
		return type;
	}

	public void setType(ServiceCenterType type) {
		this.type = type;
	}
	
}
