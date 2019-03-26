package com.java.c3s.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.c3s.entity.ServiceCenter;

@Repository
public interface ServiceCenterDao extends JpaRepository<ServiceCenter, Long>{
	
}
