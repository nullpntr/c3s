package com.java.c3s.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Where;

@Entity(name = "booking")
@Where(clause = "sysDeleteFlag=0")
public class Booking extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @Column(name = "bookingdatetime")
  private LocalDateTime bookingDateTime;


  @Column(name = "deliverydatetime")
  private LocalDateTime deliveryDateTime;

  @Column(name = "plateno")
  private String plateNo;


  @OneToOne
  @JoinColumn(name = "servicecenterid")
  private ServiceCenter serviceCenter;


  @OneToOne
  @JoinColumn(name = "customerid")
  private Customer customer;

  public Booking() {

  }

  public Booking(Long id, String plateNo) {
    this.id = id;
    this.plateNo = plateNo;
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
  public LocalDateTime getRegistrationDateTime() {
    return bookingDateTime;
  }

  public void setRegistrationDateTime(LocalDateTime bookingDateTime) {
    this.bookingDateTime = bookingDateTime;
  }

  public LocalDateTime getDeliveryDateTime() {
    return deliveryDateTime;
  }

  public void setDeliveryDateTime(LocalDateTime deliveryDateTime) {
    this.deliveryDateTime = deliveryDateTime;
  }

  public LocalDateTime getBookingDateTime() {
    return bookingDateTime;
  }

  public void setBookingDateTime(LocalDateTime bookingDateTime) {
    this.bookingDateTime = bookingDateTime;
  }

  public ServiceCenter getServiceCenter() {
    return serviceCenter;
  }

  public void setServiceCenter(ServiceCenter serviceCenter) {
    this.serviceCenter = serviceCenter;
  }

  public Customer getCustomerId() {
    return customer;
  }

  public void setCustomerId(Customer customer) {
    this.customer = customer;
  }

}
