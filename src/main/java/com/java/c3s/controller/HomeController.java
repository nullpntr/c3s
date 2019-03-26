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
import com.java.c3s.entity.ServiceCenter;
import com.java.c3s.entity.User;
import com.java.c3s.service.ServiceCenterService;
import com.java.c3s.service.UserService;
import com.java.c3s.service.AdminService;
import com.java.c3s.service.BookingService;



@Controller
public class HomeController {
	
	@Autowired
	private ServiceCenterService scService;
	
	@Autowired
	private BookingService bkService;
	
	@Autowired
	private UserService uService;
	
	
	
	
	@RequestMapping(value="/service-centers/add", method=RequestMethod.POST)
	public String addServiceCenter(@RequestBody ServiceCenter serviceCenter) {
		if(scService.addServiceCenter(serviceCenter) == null) {
			return "Something is wrong!!";
		} else {
			return "ServiceCenter is added successfully";
		}
	}
	
	@RequestMapping(value="/booking/add",method=RequestMethod.POST)
	@ResponseBody
	public String addBooking(@RequestBody Booking booking) {
		if(bkService.addBooking(booking)== null){
			
			return "Something is wrong";
		}
		else
		{
			return "Bookings Added Successfully";
		}
	}
	
	
	@RequestMapping(value="/user/add",method=RequestMethod.POST)
	@ResponseBody
	public String addUser(@RequestBody User user) {
		if(uService.addUser(user)== null){
			
			return "Something is wrong";
		}
		else
		{
			return "User Added Successfully";
		}
	}
	
	
	
	@RequestMapping(value="/service-centers", method=RequestMethod.GET)
	@ResponseBody
	public List<ServiceCenter> getServiceCenters() {
		return scService.findAll();
	}
	
	
	
	/**
	 * 1. add a request mapping with POST type and add a service center to your existing list
	 * 2. update an existing service center
	 * 3. delete an existing service center
	 */

}
