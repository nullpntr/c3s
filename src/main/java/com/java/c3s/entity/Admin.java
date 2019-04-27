package com.java.c3s.entity;

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

@Entity(name = "admin")
@Where(clause = "sysDeleteFlag=0")

public class Admin extends BaseEntity {

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
	
  public Set<Roles> getRoles() {
    return roles;
  }

  public void setRoles(Set<Roles> roles) {
    this.roles = roles;
  }

  public Admin(Admin admin) {
    this.userName = admin.getUserName();
    this.password = admin.getPassword();
    this.emailId = admin.getEmailId();
    this.id = admin.getId();
    this.roles = admin.getRoles();
  }
  public void setAdmin(Admin admin) {
    this.userName = admin.getUserName();
    this.password = admin.getPassword();
    this.emailId = admin.getEmailId();
    this.id = admin.getId();
    this.roles = admin.getRoles();
  }

  public Admin() {

  }
}
