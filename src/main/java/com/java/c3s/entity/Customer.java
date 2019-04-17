package com.java.c3s.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Where;


@Entity(name = "customer")
@Where(clause = "sysDeleteFlag=0")
public class Customer extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @Column(name = "username")
  private String userName;

  @Column(name = "password")
  private String password;

  @Column(name = "emailid")
  private String emailId;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "roleid"))
  private Set<Roles> roles;

  @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Booking> bookings;

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

  public String getEmailId() {
    return emailId;
  }

  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }

  public Customer(Customer customer) {
    this.userName = customer.getUserName();
    this.password = customer.getPassword();
    this.emailId = customer.getEmailId();
    this.id = customer.getId();
    this.roles = customer.getRoles();
  }
  public void setCustomer(Customer customer) {
    this.userName = customer.getUserName();
    this.password = customer.getPassword();
    this.emailId = customer.getEmailId();
    this.id = customer.getId();
    this.roles = customer.getRoles();
  }
  public Customer() {

  }

  public Set<Roles> getRoles() {
    return roles;
  }

  public void setRoles(Set<Roles> roles) {
    this.roles = roles;
  }
  // public String getRoles() {
  // return roles;
  // }
  //
  // public void setRoles(String roles) {
  // this.roles = roles;
  // }

}
