package com.java.c3s.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

  public Customer addCustomer(Customer customer) {
    customer.setSysDeleteFlag(0);
    // Set<Roles> role = customer.getRoles();
    // Optional<Roles> rData = rDao.findById(role.getRoleId());
    // Set<Roles>Roles rDatum = rData.get();
    // customer.setRoles(rDatum);
    return cDao.save(customer);
  }

  public List<Customer> viewCustomer() {
    return cDao.findAll();
  }

  public Optional<Customer> findById(Long id) {
    return cDao.findById(id);
  }

  public Customer editCustomer(Optional<Customer> customerDetails,
      Customer customer) {
    Customer customerDetail = customerDetails.get();
    if (customer.getUserName() != null) {
      customerDetail.setUserName(customer.getUserName());
    }
    if (customer.getPassword() != null) {
      customerDetail.setPassword(customer.getPassword());
    }
    if (customer.getEmailId() != null) {
      customerDetail.setEmailId(customer.getEmailId());
    }
    return cDao.save(customerDetail);
  }

  public void deleteCustomer(Long id) {
    Optional<Customer> customerDetails = findById(id);
    Customer customerDetail = customerDetails.get();
    customerDetail.setSysDeleteFlag(1);
    cDao.save(customerDetail);

  }

}
