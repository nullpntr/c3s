package com.java.c3s.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  public Admin addAdmin(Admin admin, String user) {
    admin.setSysDeleteFlag(0);
    admin.setLastUpdatedBy(user);
    if (aDao.findByuserName(admin.getUserName()) == null) {
    return aDao.save(admin);
    } else {
      return null;
    }
	}
	
	public List<Booking> viewBookings() {
    return bDao.findAll();
  }

  public List<Admin> viewAdmin() {
    return aDao.findAll();
  }

  public Admin editAdmin(Optional<Admin> adminDetails, Admin admin,
      String name) {
    Admin adminDetail = adminDetails.get();
    // adminDetail.setId(admin.getId());

    if (admin.getUserName() != null
        && aDao.findByuserName(admin.getUserName()) == null) {
      adminDetail.setUserName(admin.getUserName());
    }
    if (admin.getPassword() != null) {
      String pwd = admin.getPassword();
      String encryptPwd = passwordEncoder.encode(pwd);
      adminDetail.setPassword(encryptPwd);

    }
    if (admin.getEmailId() != null) {
      adminDetail.setEmailId(admin.getEmailId());
    }
    adminDetail.setLastUpdatedBy(name);
    return aDao.save(adminDetail);
  }
  public Admin deleteAdmin(Long id) {
    Optional<Admin> adminDetails = findById(id);
    if (adminDetails.isPresent()) {
    Admin adminDetail = adminDetails.get();
    adminDetail.setSysDeleteFlag(1);
    return aDao.save(adminDetail);
    } else {
      return null;
    }

  }

  public Optional<Admin> findById(Long id) {
    return aDao.findById(id);
  }
}
