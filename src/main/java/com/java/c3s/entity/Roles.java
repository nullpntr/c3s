package com.java.c3s.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Roles {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "roleid")
  private Long roleid;


  @Column(name = "role")
  private String role;

  // @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch =
  // FetchType.LAZY)
  // private List<Customer> customer;

  public Roles() {

  }
  public Roles(Roles roles) {
    this.roleid = roles.getRoleId();
    this.role = roles.getRole();
  }
  public Long getRoleId() {
    return roleid;
  }

  public void setRoleId(Long roleId) {
    this.roleid = roleId;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String roles) {
    this.role = roles;
  }

}
