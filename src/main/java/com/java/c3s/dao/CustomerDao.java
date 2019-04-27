package com.java.c3s.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.c3s.entity.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long> {



  Customer findByuserName(String username);

}
