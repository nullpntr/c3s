package com.java.c3s.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.c3s.entity.Admin;
import com.java.c3s.entity.Booking;
import com.java.c3s.service.AdminService;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService adService;
	
//	@RequestMapping(value="/Admin/verify",method=RequestMethod.GET)
//	@ResponseBody
//	public String verifyAdmin(@RequestBody Admin admin) {
//		if (adService.addAdmin(admin)==null) {
//			return "Error in adding the elements";
//			}
//		else {
//			return "Admin Details Added Successfully";
//		}
//	}
	
	
	@RequestMapping(value="/Admin/add",method=RequestMethod.POST)
	@ResponseBody
	public String addAdmin(@RequestBody Admin admin) {
		if (adService.addAdmin(admin)==null) {
			return "Error in adding the elements";
			}
		else {
			return "Admin Details Added Successfully";
		}
	}
	
	@RequestMapping(value="/Admin/viewBookings",method=RequestMethod.GET)
	@ResponseBody
	public List<Booking>  viewBookings() {
		return adService.viewBookings();
	}
	
	

}
