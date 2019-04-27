package com.java.c3s.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
  private String bookingDateTime;


  // private LocalDateTime deliveryDateTime;
  //
  // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
  //
  // String formatDateTime = deliveryDateTime.format(formatter);
  // LocalDateTime formatedDateTime = LocalDateTime.parse(formatDateTime);
  // public LocalDateTime formatedDateTime;
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
  public String getRegistrationDateTime() {
    return bookingDateTime;
  }

  public void setRegistrationDateTime(LocalDateTime bookingDateTime) {
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter
        .ofPattern("yyyy-MM-dd HH:mm:ss");

    String formatDateTime = now.format(formatter);
    this.bookingDateTime = formatDateTime;
  }

  public LocalDateTime getDeliveryDateTime() {
    return deliveryDateTime;
  }

  public void setDeliveryDateTime(LocalDateTime deliveryDateTime) {
    this.deliveryDateTime = deliveryDateTime;
  }

  public String getBookingDateTime() {
    return bookingDateTime;
  }

  public void setBookingDateTime() {
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter
        .ofPattern("yyyy-MM-dd HH:mm:ss");

    String formatDateTime = now.format(formatter);
    this.bookingDateTime = formatDateTime;

  }

  public ServiceCenter getServiceCenter() {
    return serviceCenter;
  }

  public void setServiceCenter(ServiceCenter serviceCenter) {
    this.serviceCenter = serviceCenter;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

}
