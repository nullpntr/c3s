package com.java.c3s.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.c3s.entity.Booking;
import com.java.c3s.entity.Customer;
import com.java.c3s.entity.ServiceCenter;
import com.java.c3s.service.BookingService;
import com.java.c3s.service.CustomerService;
import com.java.c3s.service.ServiceCenterService;

@Controller
public class HomeController {

  @Autowired
  private ServiceCenterService scService;

  @Autowired
  private BookingService bookingService;

  @Autowired
  private CustomerService customerService;

  // Service Centers
  @PostMapping(value = "/servicecenter/add")
  @ResponseBody
  public String addServiceCenter(@RequestBody ServiceCenter serviceCenter) {
    if (scService.addServiceCenter(serviceCenter) == null) {
      return "Something is wrong!!";
    } else {

      return "ServiceCenter is added successfully";
    }
  }

  @GetMapping(value = "/servicecenter/view")
  @ResponseBody
  public List<ServiceCenter> getServiceCenters() {
    return scService.findAll();
  }

  @PutMapping("/servicecenter/edit/{id}")
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

  @DeleteMapping(value = "/servicecenter/delete/{id}")
  @ResponseBody
  public String deleteServiceCenter(@PathVariable Long id) {
    Optional<ServiceCenter> serviceCenterDetail = scService.findById(id);
    if (!serviceCenterDetail.isPresent())
      return "Wrong data,give the correct id to delete";
    else {
      scService.deleteServiceCenter(id);
      return "Successfully deleted";
    }
  }

  // booking

  @PostMapping(value = "/booking/add")
  @ResponseBody
  public String addBooking(@RequestBody Booking booking) {
    if (bookingService.addBooking(booking) == null) {
      return "Something is wrong";
    } else {

      return "Bookings Added Successfully";
    }
  }

  @GetMapping(value = "/booking/view")
  @ResponseBody
  public List<Booking> getbookings() {
    return bookingService.showAll();
  }

  @PutMapping(value = "/booking/edit/{id}")
  @ResponseBody
  public String editBooking(@RequestBody Booking booking,
      @PathVariable Long id) {
    Optional<Booking> bookingDetails = bookingService.findById(id);
    if (bookingDetails.isPresent()) {

      bookingService.editBookingDetail(bookingDetails, booking);
      return "Successfully Edited";
    } else {
      return "No such booking is found";
    }
    }

  @DeleteMapping(value = "booking/delete/{id}")
  @ResponseBody
  public String deleteBooking(@PathVariable Long id) {
    Optional<Booking> bookingDetail = bookingService.findById(id);
    if (bookingDetail.isPresent()) {
      bookingService.deleteBookingDetail(bookingDetail, id);
      return "Successfully Deleted";
    } else {
      return "Enter the right booking id to delete";
    }
  }

  @PutMapping(value = "booking/changeservicecenter/{id}")
  @ResponseBody
  public String changeServiceCenter(@RequestBody Booking booking,
      @PathVariable Long id) {
    Optional<Booking> bookingDetail = bookingService.findById(id);
    if (bookingDetail.isPresent()) {

      bookingService.editBookingDetail(bookingDetail, booking);
      return "Successfully edited";
    } else {
      return "Changing Service center is failed";
    }
  }

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;
  // Customer
  @PostMapping(value = "/customer/add")
  @ResponseBody
  public String addCustomer(@RequestBody Customer customer) {
    String pwd = customer.getPassword();
    String encryptPwd = passwordEncoder.encode(pwd);
    customer.setPassword(encryptPwd);
    if (customerService.addCustomer(customer) == null) {
      return "Something is wrong";
    } else {

      return "Customer Added Successfully";
    }
  }

  @GetMapping(value = "/customer/view")
  @ResponseBody
  public List<Customer> viewCustomer() {
    return customerService.viewCustomer();
  }

  @PutMapping(value = "/customer/edit/{id}")
  @ResponseBody
  public String editCustomer(@RequestBody Customer customer,
      @PathVariable Long id) {
    Optional<Customer> customerDetails = customerService.findById(id);
    if (customerDetails.isPresent()) {
      customerService.editCustomer(customerDetails,
          customer);
      return "Customer Details successfully modified";
    } else {
      return "There is no such Customer Id";
    }
  }

  @DeleteMapping(value = "/customer/delete/{id}")
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



  /**
   * 1. add a request mapping with POST type and add a service center to your
   * existing list 2. update an existing service center 3. delete an existing
   * service center
   */

}
