package com.java.c3s.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.c3s.dao.ServiceCenterDao;
import com.java.c3s.entity.ServiceCenter;

@Service
public class ServiceCenterService {
	
	@Autowired
	private ServiceCenterDao scDao;
	
	public List<ServiceCenter> findAll() {
		return scDao.findAll();
	}
	public ServiceCenter addServiceCenter(ServiceCenter centerDetails) {
		return scDao.save(centerDetails);
	}

}
