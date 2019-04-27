package com.java.c3s.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.java.c3s.dao.CustomerDao;
import com.java.c3s.dao.RoleDao;
import com.java.c3s.entity.Customer;

@Service
public class CustomerService {

  @Autowired
  CustomerDao cDao;

  @Autowired
  RoleDao rDao;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;


  public Customer addCustomer(Customer customer, String name) {
    customer.setSysDeleteFlag(0);
    customer.setLastUpdatedBy(name);

    if (cDao.findByuserName(customer.getUserName()) == null) {

      return cDao.save(customer);
    } else {
      return null;
    }

  }

  public List<Customer> viewCustomer() {
    return cDao.findAll();
  }

  public Optional<Customer> findById(Long id) {
    return cDao.findById(id);
  }

  public Customer editCustomer(Optional<Customer> customerDetails,
      Customer customer, String name) {

    Customer customerDetail = customerDetails.get();

    if (customer.getUserName() != null
        && cDao.findByuserName(customer.getUserName()) == null) {

      customerDetail.setUserName(customer.getUserName());
    }
    if (customer.getPassword() != null) {
      String pwd = customer.getPassword();
      String encryptPwd = passwordEncoder.encode(pwd);
      customerDetail.setPassword(encryptPwd);
    }
    if (customer.getEmailId() != null) {
      customerDetail.setEmailId(customer.getEmailId());
    }
    if (customer.getRoles() != null) {
      customerDetail.setRoles(customer.getRoles());
    }
    customerDetail.setLastUpdatedBy(name);
    return cDao.save(customerDetail);
  }

  public void deleteCustomer(Long id) {
    Optional<Customer> customerDetails = findById(id);
    Customer customerDetail = customerDetails.get();
    customerDetail.setSysDeleteFlag(1);
    cDao.save(customerDetail);

  }

}
