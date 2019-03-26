package com.java.c3s.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.c3s.dao.AdminDao;
import com.java.c3s.dao.BookingDao;
import com.java.c3s.entity.Admin;
import com.java.c3s.entity.Booking;

@Service
public class AdminService {
	
	@Autowired
	private AdminDao ad;
	
	@Autowired
	private BookingDao bd;
	
	public Admin addAdmin(Admin admin) {
		return ad.save(admin);
	}
	
	public List<Booking> viewBookings() {
		return bd.findAll();
	}
//	public String verifyAdmin(Admin admin) {
//		return bd.findById(admin.getUserName());
//		
//	}
}
