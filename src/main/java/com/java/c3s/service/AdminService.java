package com.java.c3s.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.c3s.dao.AdminDao;
import com.java.c3s.dao.BookingDao;
import com.java.c3s.entity.Admin;
import com.java.c3s.entity.Booking;

@Service
public class AdminService {
	
	@Autowired
  private AdminDao aDao;
	
	@Autowired
  private BookingDao bDao;
	
	public Admin addAdmin(Admin admin) {
    admin.setSysDeleteFlag(0);
    return aDao.save(admin);
	}
	
	public List<Booking> viewBookings() {
    return bDao.findAll();
  }

  public List<Admin> viewAdmin() {
    return aDao.findAll();
  }

  public Admin editAdmin(Optional<Admin> adminDetails, Admin admin) {
    Admin adminDetail = adminDetails.get();
    // adminDetail.setId(admin.getId());
    if (admin.getUserName() != null) {
      adminDetail.setUserName(admin.getUserName());
    }
    if (admin.getPassword() != null) {
      adminDetail.setPassword(admin.getPassword());
    }
    if (admin.getEmailId() != null) {
      adminDetail.setEmailId(admin.getEmailId());
    }
    return aDao.save(adminDetail);
  }
  public Admin deleteAdmin(Long id) {
    Optional<Admin> adminDetails = findById(id);
    Admin adminDetail = adminDetails.get();
    adminDetail.setSysDeleteFlag(1);
    return aDao.save(adminDetail);

  }

  public Optional<Admin> findById(Long id) {
    return aDao.findById(id);
  }
}
