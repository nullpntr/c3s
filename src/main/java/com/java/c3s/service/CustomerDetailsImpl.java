package com.java.c3s.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.java.c3s.entity.Customer;


public class CustomerDetailsImpl extends Customer implements UserDetails {

  /**
   * 
   */
  private static final long serialVersionUID = -4320033453519484949L;
  // public CustomerDetailsImpl(final Customer customer) {
  // super(customer);
  // }
  private Customer customer;
//  public CustomerDetailsImpl(final Customer customer) {
//    this.customer = customer;
//
//  }
//  public CustomerDetailsImpl() {
//
//  }




  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

    return super.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
        .collect(Collectors.toList());
  }

  @Override
  public String getPassword() {

    // customer.setCustomer(customer);
    return super.getPassword();
  }

  @Override
  public String getUsername() {
    return super.getUserName();
  }

  @Override
  public boolean isAccountNonExpired() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isEnabled() {
    // TODO Auto-generated method stub
    return true;
  } 

}
