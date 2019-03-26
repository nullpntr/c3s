package com.java.c3s.entity;

import java.util.Date;

import javax.persistence.CascadeType;

//import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;



@Entity(name="booking")
public class Booking {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	
	private Long id;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="deliverydate")
	private Date deliveryDate;
	
	@Column(name="plateno")
	private String plateNo;
	
//	@OneToOne(cascade=CascadeType.PERSIST)
//    @JoinColumn(name="serviceCenterId")
//	private ServiceCenter serviceCenter;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	 @JoinColumn(name="id")
	private User userId;
	
	
	public Booking(){
		
	}
	
	public Booking(Long id,String plateNo){
		this.id = id;
		this.plateNo = plateNo;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public User getUser() {
		return userId;
	}

	public void setUser(User userId) {
		this.userId = userId;
	}
   
   

}
