package com.java.c3s.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.java.c3s.dao.CustomerDao;
import com.java.c3s.entity.Customer;

@Service
public class CustomerDetailsService implements UserDetailsService {

  @Autowired
  private CustomerDao customerDao;

  @Override
  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException {
    Customer customer = customerDao.findByuserName(username);
    CustomerDetailsImpl userDetails = null;
    if (customer != null) {
      userDetails = new CustomerDetailsImpl();
      userDetails.setCustomer(customer);
    } else {
      throw new UsernameNotFoundException("Such User is not exists");
    }
    return userDetails;

  }

}
