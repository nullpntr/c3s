package com.java.c3s.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.c3s.entity.Admin;

@Repository
public interface AdminDao extends JpaRepository<Admin,String>{

}
