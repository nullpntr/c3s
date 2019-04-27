package com.java.c3s.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.c3s.dao.BookingDao;
import com.java.c3s.dao.CustomerDao;
import com.java.c3s.dao.ServiceCenterDao;
import com.java.c3s.entity.Booking;
import com.java.c3s.entity.Customer;
import com.java.c3s.entity.ServiceCenter;

@Service
public class BookingService {

	@Autowired
	private BookingDao bookingDao;
	
  @Autowired
  private ServiceCenterDao serviceCenterDao;

  @Autowired
  private CustomerDao customerDao;


  public Booking addBooking(Booking booking, List<String> errors) {
    Customer customerDetail = booking.getCustomer();
    ServiceCenter serviceCenter = booking.getServiceCenter();

    // find and set service center data
    if (serviceCenter != null) {
      Optional<ServiceCenter> scData = serviceCenterDao
          .findById(serviceCenter.getId());
      if (scData.isPresent()) {
        ServiceCenter scDatum = scData.get();
        booking.setServiceCenter(scDatum);
      } else {
        errors.add("invalid service center id/data not found.");
      }
    } else {
      errors.add("service was not passed from the request");

    }

    if (customerDetail != null) {
      Optional<Customer> customerData = customerDao
          .findById(customerDetail.getId());
      if (customerData.isPresent()) {
        Customer customerDatum = customerData.get();
        booking.setCustomer(customerDatum);
      } else {
        errors.add("Invalid Customer Id/data,can't be able to identify");

      }
    } else {
      errors.add("Customer details was not passed from the request");
    }

    if (errors.size() > 0) {
      return null;
    } else {
    booking.setBookingDateTime();


    booking.setSysDeleteFlag(0);
      return bookingDao.save(booking);
    }
  }
	
	public List<Booking> showAll() {
		return bookingDao.findAll();
	}

  public Optional<Booking> findById(Long id) {
    if (bookingDao.findById(id) == null) {
      return null;
    } else {
    return bookingDao.findById(id);
    }
  }


  public Booking editBookingDetail(Optional<Booking> bookingDetails,
      Booking booking) {
    Booking bookingDetail = bookingDetails.get();
    if (booking.getDeliveryDateTime() != null) {
      bookingDetail.setDeliveryDateTime(booking.getDeliveryDateTime());
    }
    if (booking.getPlateNo() != null) {
      bookingDetail.setPlateNo(booking.getPlateNo());
    }
    if (booking.getServiceCenter() != null) {
      ServiceCenter serviceId = booking.getServiceCenter();
      Optional<ServiceCenter> scData = serviceCenterDao
          .findById(serviceId.getId());
      ServiceCenter scDatum = scData.get();
      bookingDetail.setServiceCenter(scDatum);
    }
    if (booking.getCustomer() != null) {
      Customer customerDetail = booking.getCustomer();

      Optional<Customer> customerData = customerDao
          .findById(customerDetail.getId());
      if (customerData != null) {
      Customer customerDatum = customerData.get();
      bookingDetail.setCustomer(customerDatum);
      } else {
        return null;
      }
    }
    return bookingDao.save(bookingDetail);
  }

  public Booking editDeliveryDate(Optional<Booking> bookingDetails,
      Booking booking) {
    Booking bookingDetail = bookingDetails.get();
    bookingDetail.setDeliveryDateTime(booking.getDeliveryDateTime());
    return bookingDao.save(bookingDetail);
  }

  public void deleteBookingDetail(Optional<Booking> bookingDetails, Long id) {
    Booking bookingDetail = bookingDetails.get();
    bookingDetail.setSysDeleteFlag(1);
    bookingDao.save(bookingDetail);
  }

  public List<Booking> findByServiceCenterId(Long serviceCenterId) {
    // List<Booking> bookings = new ArrayList<>();

    Optional<ServiceCenter> sc = serviceCenterDao.findById(serviceCenterId);
    if (sc != null) {
      List<Booking> bookings = bookingDao.findByServiceCenter(sc.get());
      return bookings;
    }
    else {
      return null;
    }

  }

  public List<Booking> findByCustomerId(Long customerId) {
    List<Booking> bookings = new ArrayList<>();
    Optional<Customer> c = customerDao.findById(customerId);
    if (c != null) {
      bookings = bookingDao.findByCustomer(c.get());
      return bookings;
    }
    else {
      return null;
    }
  }



  // public Booking SetVariablesExceptServiceCenter(
  // Optional<Booking> bookingDetails, Booking booking1) {
  // Booking bookingValues = bookingDetails.get();
  // booking1.setId(bookingValues.getId());
  // booking1.setBookingDateTime();
  // booking1.setDeliveryDateTime(bookingValues.getDeliveryDateTime());
  // booking1.setCustomer(bookingValues.getCustomer());
  // booking1.setPlateNo(bookingValues.getPlateNo());
  //
  // return booking1;
  // }

  public List<Booking> findByBookingTime(LocalDateTime bookingDateTime) {

    List<Booking> bookings = bookingDao.findByBookingDateTime(bookingDateTime);

    return bookings;
  }

  public List<Booking> findByDeliveryTime(LocalDateTime deliveryDateTime) {

    List<Booking> bookings = bookingDao
        .findByDeliveryDateTime(deliveryDateTime);

    return bookings;
  }

}
