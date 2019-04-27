package com.java.c3s.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.java.c3s.entity.Customer;
import com.java.c3s.entity.ServiceCenter;
import com.java.c3s.exception.DataNotFoundException;
import com.java.c3s.service.AdminService;
import com.java.c3s.service.BookingService;
import com.java.c3s.service.CustomerService;
import com.java.c3s.service.ServiceCenterService;

@Controller
public class AdminController {
	
	@Autowired
  private AdminService adminService;
	
  @Autowired
  private BookingService bookingService;
	
  @Autowired
  private ServiceCenterService scService;

  @Autowired
  private CustomerService customerService;
	
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  // To add the new admin
  @PostMapping(value = "/admin/add")
  @ResponseBody
  public String addAdmin(@RequestBody Admin admin) {

    String pwd = admin.getPassword();
    String encryptPwd = passwordEncoder.encode(pwd);
    admin.setPassword(encryptPwd);

    Authentication authentication = SecurityContextHolder.getContext()
        .getAuthentication();
    String name = authentication.getName();

    if (adminService.addAdmin(admin, name) == null) {

      return "Something is wrong";
    } else {
      return "Successfully added";
    }
  }


  // To view the admin details
  @GetMapping(value = "/admin/view")
  @ResponseBody
  public List<Admin> viewAdmin() {

    Authentication authentication = SecurityContextHolder.getContext()
        .getAuthentication();
    String name = authentication.getName();

    return adminService.viewAdmin();

  }

  // To edit the admin details
  @PutMapping(value = "/admin/edit/{id}")
  @ResponseBody
  public String editAdmin(@RequestBody Admin admin, @PathVariable Long id) {
    Optional<Admin> adminDetails = adminService.findById(id);
    Authentication authentication = SecurityContextHolder.getContext()
        .getAuthentication();
    String name = authentication.getName();
    if (adminDetails.isPresent()) {
      adminService.editAdmin(adminDetails, admin, name);
      return "Admin Details successfully modified";
    } else {
      return "There is no such Admin Id";
    }
  }

  // To delete the admin details
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

  // To view the bookings by its id
  @GetMapping(value = "/admin/booking/{id}")
  @ResponseBody
  public Booking viewBooking(@PathVariable Long id)
      throws DataNotFoundException {
    Optional<Booking> bookingDetails = bookingService.findById(id);
    // if(bookingDetails.isPresent()) {if
    String entity = "booking id";
    return bookingDetails
        .orElseThrow(() -> new DataNotFoundException(id, entity));
  }


  // To view the servicecenter by service center id
  @GetMapping(value = "/admin/servicecenter/{id}")
  @ResponseBody
  public ServiceCenter viewServiceCenter(@PathVariable Long id)
      throws DataNotFoundException {
    Optional<ServiceCenter> serviceCenterDetails = scService.findById(id);
    String entity = "service center id";
    return serviceCenterDetails
        .orElseThrow(() -> new DataNotFoundException(id, entity));
  }

  // To view the bookings done at particular service center
  @GetMapping(value = "/admin/booking/servicecenter/{scId}")
  @ResponseBody
  public List<Booking> viewBookingByScID(@PathVariable Long scId)
      throws DataNotFoundException {
    List<Booking> bookingDetails = bookingService.findByServiceCenterId(scId);

    return bookingDetails;

  }

  // To view the booking done by the particular customer
  @GetMapping(value = "/admin/booking/customer/{customerId}")
  @ResponseBody
  public List<Booking> viewBookingByCustomerID(@PathVariable Long customerId) {
    return bookingService.findByCustomerId(customerId);
  }

  // To edit Delivery DateTime
  @PutMapping(value = "/admin/booking/edit/{bookingId}")
  @ResponseBody
  public String editDeliveryDateTimeById(@PathVariable Long bookingId,
      @RequestBody Booking booking) {
    Optional<Booking> bookingDetails = bookingService.findById(bookingId);
    if (bookingDetails.isPresent()) {
      bookingService.editDeliveryDate(bookingDetails, booking);
      return "Successfully edited";
    } else {
      return "Can't able to edit,Booking Id is Invalid";
    }
  }

  // To add new service centers
  @PostMapping(value = "/admin/servicecenter/add")
  @ResponseBody
  public String addServiceCenter(@RequestBody ServiceCenter serviceCenter) {
    if (scService.addServiceCenter(serviceCenter) == null) {
      return "Something is wrong!!";
    } else {

      return "ServiceCenter is added successfully";
    }
  }
  // To update service center details
  @PutMapping("/admin/servicecenter/edit/{id}")
  @ResponseBody
  public String updateServiceCenter(@RequestBody ServiceCenter serviceCenter,
      @PathVariable Long id) {
    Optional<ServiceCenter> serviceCenterDetail = scService.findById(id);

    if (!serviceCenterDetail.isPresent())
      return "No such id is found";
    else {
      serviceCenter.setId(id);
      Optional<ServiceCenter> sc = scService.findById(id);

      scService.editServiceCenter(serviceCenter, sc);
      return "Successfully updated";
    }
  }

  // To delete the particular service center
  @DeleteMapping(value = "/admin/servicecenter/delete/{id}")
  @ResponseBody
  public String deleteServiceCenter(@PathVariable Long id) {
    Optional<ServiceCenter> serviceCenterDetail = scService.findById(id);
    if (!serviceCenterDetail.isPresent())
      return "Invalid servicecenter id";
    else {
      scService.deleteServiceCenter(id);
      return "Successfully deleted";
    }
  }

  // To view all bookings
  @GetMapping(value = "/admin/booking/view")
  @ResponseBody
  public List<Booking> getbookings() {
    return bookingService.showAll();
  }

  // To view the Bookings based on Booking date
  @GetMapping(value = "/admin/booking/viewbybookingdate/{bookingTime}")
  @ResponseBody
  public List<Booking> viewByBookingTime(
      @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:MM:SS") LocalDateTime bookingTime) {

    bookingTime = (LocalDateTime) bookingTime;
    return bookingService.findByBookingTime(bookingTime);
  }

  // To view Bookings based on Delivery Date and Time
  @GetMapping(value = "/admin/booking/viewbydeliverydate/{deliveryTime}")
  @ResponseBody
  public List<Booking> viewByDeliveryTime(
      @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:MM:SS") LocalDateTime deliveryTime) {

    deliveryTime = (LocalDateTime) deliveryTime;
    return bookingService.findByDeliveryTime(deliveryTime);
  }

  // To view the customer of the particular booking id
  @GetMapping(value = "/admin/customerofbooking/{bookingId}")
  @ResponseBody
  public Customer viewCustomerOfBooking(@PathVariable Long bookingId) {
    Optional<Booking> bookingValue = bookingService.findById(bookingId);
    Booking bookings = bookingValue.get();
    return bookings.getCustomer();
  }
  // To view all customers
  @GetMapping(value = "/admin/customer/view")
  @ResponseBody
  public List<Customer> viewCustomer() {
    return customerService.viewCustomer();
  }

  // To delete the customer
  @DeleteMapping(value = "/admin/customer/delete/{id}")
  @ResponseBody
  public String deleteCustomer(@PathVariable Long id) {
    Optional<Customer> customerDetails = customerService.findById(id);
    if (customerDetails.isPresent()) {
      customerService.deleteCustomer(id);
      return "Successfully Deleted the details ";
    } else {
      return "There is no such Customer id";
    }
  }

}
