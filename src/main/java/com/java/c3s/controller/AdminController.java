package com.java.c3s.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.c3s.entity.Admin;
import com.java.c3s.entity.Booking;
import com.java.c3s.entity.ServiceCenter;
import com.java.c3s.service.AdminService;
import com.java.c3s.service.BookingService;
import com.java.c3s.service.ServiceCenterService;

@Controller
public class AdminController {
	
	@Autowired
  private AdminService adminService;
	
  @Autowired
  private BookingService bookingService;
	
  @Autowired
  private ServiceCenterService scService;
	

  @PostMapping(value = "/admin/add")
  @ResponseBody
  public String addAdmin(@RequestBody Admin admin) {
    if (adminService.addAdmin(admin) == null) {

      return "Something is wrong";
    } else {
      return "Successfully added";
    }
  }

  @GetMapping(value = "/admin/view")
  @ResponseBody
  public List<Admin> viewAdmin() {
    return adminService.viewAdmin();
  }

  @PutMapping(value = "/admin/edit/{id}")
  @ResponseBody
  public String editAdmin(@RequestBody Admin admin, @PathVariable Long id) {
    Optional<Admin> adminDetails = adminService.findById(id);
    if (adminDetails.isPresent()) {
      adminService.editAdmin(adminDetails, admin);
      return "Admin Details successfully modified";
    } else {
      return "There is no such Admin Id";
    }
  }

  @DeleteMapping(value = "/admin/delete/{id}")
  @ResponseBody
  public String deleteAdmin(@PathVariable Long id) {
    Optional<Admin> adminDetails = adminService.findById(id);
    if (adminDetails.isPresent()) {
      adminService.deleteAdmin(id);
      return "Successfully Deleted the details ";
    } else {
      return "There is no such Admin id";
    }
  }

  @GetMapping(value = "/admin/booking/{id}")
  @ResponseBody
  public Optional<Booking> viewBooking(@PathVariable Long id) {
    Optional<Booking> bookingDetails = bookingService.findById(id);
    return bookingDetails;
  }

  @GetMapping(value = "/admin/servicecenter/{id}")
  @ResponseBody
  public Optional<ServiceCenter> viewServiceCenter(@PathVariable Long id) {
    Optional<ServiceCenter> serviceCenterDetails = scService.findById(id);
    return serviceCenterDetails;
  }

  @GetMapping(value = "/admin/booking/servicecenter/{scId}")
  @ResponseBody
  public List<Booking> viewBookingByScID(@PathVariable Long scId) {
    return bookingService.findByServiceCenter(scId);
  }

  @GetMapping(value = "/admin/booking/customer/{customerId}")
  @ResponseBody
  public List<Booking> viewBookingByCustomerID(@PathVariable Long customerId) {
    return bookingService.findByCustomer(customerId);
  }

  // Editing Delivery DateTime
  @PutMapping(value = "/admin/booking/edit/{bookingId}")
  @ResponseBody
  public String editDeliveryDateTimeById(@PathVariable Long bookingId,
      @RequestBody Booking booking) {
    Optional<Booking> bookingDetails = bookingService.findById(bookingId);
    if (bookingDetails.isPresent()) {
      bookingService.editDeliveryDate(bookingDetails, booking);
      return "Successfully edited";
    } else {
      return "Can't able to edit,Error exists";
    }

  }

}
