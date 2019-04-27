package com.java.c3s.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.java.c3s.entity.Booking;
import com.java.c3s.entity.Customer;
import com.java.c3s.entity.ServiceCenter;
import com.java.c3s.entity.User;
import com.java.c3s.service.BookingService;
import com.java.c3s.service.CustomerService;
import com.java.c3s.service.ServiceCenterService;
import com.java.c3s.service.UserService;

@Controller
public class HomeController {

  @Autowired
  private ServiceCenterService scService;

  @Autowired
  private BookingService bookingService;

  @Autowired
  private CustomerService customerService;

  @Autowired
  private UserService userService;

  @PostMapping(value = "user/add")
  @ResponseBody
  public String addUser(@RequestBody User user) {
    String pwd = user.getPassword();
    String encryptPwd = passwordEncoder.encode(pwd);
    user.setPassword(encryptPwd);

    Authentication authentication = SecurityContextHolder.getContext()
        .getAuthentication();
    String name = authentication.getName();

    if (name == "anonymousUser") {
      name = user.getUserName();
    }
    if (userService.addUser(user, name) == null) {
      return "Something is wrong";
    } else {

      return "User Added Successfully";
    }
  }
  // To view all available service centers
  @GetMapping(value = "/servicecenter/view")
  @ResponseBody
  public List<ServiceCenter> getServiceCenters() {
    return scService.findAll();
  }



  // To make the booking for the car wash

  @PostMapping(value = "/customer/booking/add")
  @ResponseBody
  public List<String> addBooking(@RequestBody Booking booking) {
    List<String> errors = new ArrayList<>();
    if (bookingService.addBooking(booking, errors) == null) {
      return errors;
    } else {

      return Arrays.asList("Bookings Added Successfully");
    }
  }

  // To edit the booking
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

  // To cancel the booking
  @DeleteMapping(value = "booking/delete/{id}")
  @ResponseBody
  public String deleteBooking(@PathVariable Long id) {
    Optional<Booking> bookingDetail = bookingService.findById(id);
    if (bookingDetail.isPresent()) {
      bookingService.deleteBookingDetail(bookingDetail, id);
      return "Successfully Deleted";
    } else {
      return "Invalid Booking Id";
    }
  }

  // To change the service center
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


  // To resgister the customer
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @PostMapping(value = "/customer/add")
  @ResponseBody
  public String addCustomer(@RequestBody Customer customer) {
    String pwd = customer.getPassword();
    String encryptPwd = passwordEncoder.encode(pwd);
    customer.setPassword(encryptPwd);

    Authentication authentication = SecurityContextHolder.getContext()
        .getAuthentication();
    String name = authentication.getName();

    if (name == "anonymousUser") {
      name = customer.getUserName();
    }
    if (customerService.addCustomer(customer, name) == null) {
      return "Something is wrong";
    } else {

      return "Customer Added Successfully";
    }
  }


  // To edit the customer Details
  @PutMapping(value = "/customer/edit/{id}")
  @ResponseBody
  public String editCustomer(@RequestBody Customer customer,
      @PathVariable Long id) {
    Optional<Customer> customerDetails = customerService.findById(id);
    Authentication authentication = SecurityContextHolder.getContext()
        .getAuthentication();
    String name = authentication.getName();
    if (customerDetails.isPresent()) {
      customerService.editCustomer(customerDetails,
          customer, name);
      return "Customer Details successfully modified";
    } else {
      return "There is no such Customer Id";
    }
  }

  @PutMapping(value = "/user/edit/{id}")
  @ResponseBody
  public String editUser(@RequestBody User user, @PathVariable Long id) {
    Optional<User> userDetails = userService.findById(id);
    Authentication authentication = SecurityContextHolder.getContext()
        .getAuthentication();
    String name = authentication.getName();
    if (userDetails.isPresent()) {
      userService.editUser(userDetails, user, name);
      return "User Details successfully modified";
    } else {
      return "There is no such User Id";
    }
  }
}
