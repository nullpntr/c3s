package com.java.c3s.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.c3s.dao.CustomerDao;
import com.java.c3s.entity.Customer;

@Service
public class CustomerService {

  @Autowired
  CustomerDao cDao;
  public Customer addCustomer(Customer customer) {
    return cDao.save(customer);
  }
  public List<Customer> viewCustomer() {
    return cDao.findAll();
  }
  public Optional<Customer> findById(Long id) {
    return cDao.findById(id);
  }
  public Customer editCustomer(Customer customer) {
    return cDao.save(customer);
  }
  public void deleteCustomer(Long id) {
    cDao.deleteById(id);

  }




}
