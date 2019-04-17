package com.java.c3s.service;

import java.util.List;
import java.util.Optional;

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
    centerDetails.setSysDeleteFlag(0);
		return scDao.save(centerDetails);
	}
  public Optional<ServiceCenter> findById(Long id) {

    return scDao.findById(id);
  }
  public ServiceCenter editServiceCenter(ServiceCenter serviceCenter,
      Optional<ServiceCenter> sc) {
    ServiceCenter singleServiceCenter = sc.get();
    if (serviceCenter.getName() != null) {
      singleServiceCenter.setName(serviceCenter.getName());
    }
    if (serviceCenter.getAddress() != null) {
      singleServiceCenter.setAddress(serviceCenter.getAddress());
    }
    if (serviceCenter.getType() != null) {
      singleServiceCenter.setType(serviceCenter.getType());
    }
    return scDao.save(singleServiceCenter);
  }
  public void deleteServiceCenter(Long id) {
    Optional<ServiceCenter> servicecenterDetails = findById(id);
    ServiceCenter scDetail = servicecenterDetails.get();
    scDetail.setSysDeleteFlag(1);
    scDao.save(scDetail);
  }

}
