package com.java.c3s.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.java.c3s.dao.AdminDao;
import com.java.c3s.dao.CustomerDao;
import com.java.c3s.entity.Admin;
import com.java.c3s.entity.Customer;

@Service
public class UsersDetailsService implements UserDetailsService {

  @Autowired
  private CustomerDao customerDao;
  
  @Autowired
  private AdminDao adminDao;

  @Override
  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException {
    Customer customer = customerDao.findByuserName(username);

    // CustomerDetailsImpl userDetailsCustomer = null;
    if (customer == null) {

      AdminDetailsImpl userDetailsAdmin = null;
      Admin admin = adminDao.findByuserName(username);

      if(admin != null) {
        userDetailsAdmin = new AdminDetailsImpl();
        userDetailsAdmin.setAdmin(admin);
        
      }
      else {
        throw new UsernameNotFoundException("Such User is not exists");
      }
      return userDetailsAdmin;
    }

    else {
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


}
