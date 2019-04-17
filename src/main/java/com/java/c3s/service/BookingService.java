package com.java.c3s.service;

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


	public Booking addBooking(Booking booking) {
    Customer customerDetail = booking.getCustomerId();
    ServiceCenter serviceId = booking.getServiceCenter();
    Optional<Customer> customerData = customerDao
        .findById(customerDetail.getId());
    Customer customerDatum = customerData.get();
    Optional<ServiceCenter> scData = serviceCenterDao
        .findById(serviceId.getId());
    ServiceCenter scDatum = scData.get();
    booking.setCustomerId(customerDatum);
    booking.setServiceCenter(scDatum);
    booking.setSysDeleteFlag(0);
		return bookingDao.save(booking);

	}
	
	public List<Booking> showAll() {
		return bookingDao.findAll();
	}

  public Optional<Booking> findById(Long id) {
    return bookingDao.findById(id);
  }

  public Booking editBookingDetail(Optional<Booking> bookingDetails,
      Booking booking) {
    Booking bookingDetail = bookingDetails.get();
    if (booking.getBookingDateTime() != null) {
      bookingDetail.setBookingDateTime(booking.getBookingDateTime());
    }
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
    if (booking.getCustomerId() != null) {
      Customer customerDetail = booking.getCustomerId();

      Optional<Customer> customerData = customerDao
          .findById(customerDetail.getId());
      Customer customerDatum = customerData.get();
      bookingDetail.setCustomerId(customerDatum);
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

  public List<Booking> findByServiceCenter(Long serviceCenterId) {
    List<Booking> bookings = new ArrayList<>();
    Optional<ServiceCenter> sc = serviceCenterDao.findById(serviceCenterId);
    if (sc != null) {
      bookings = bookingDao.findByServiceCenter(sc.get());
    }
    return bookings;

  }

  public List<Booking> findByCustomer(Long customerId) {
    List<Booking> bookings = new ArrayList<>();
    Optional<Customer> c = customerDao.findById(customerId);
    if (c != null) {
      bookings = bookingDao.findByCustomer(c.get());
    }
    return bookings;
  }



  public Booking SetVariablesExceptServiceCenter(
      Optional<Booking> bookingDetails, Booking booking1) {
    Booking bookingValues = bookingDetails.get();
    booking1.setId(bookingValues.getId());
    booking1.setBookingDateTime(bookingValues.getBookingDateTime());
    booking1.setDeliveryDateTime(bookingValues.getDeliveryDateTime());
    booking1.setCustomerId(bookingValues.getCustomerId());
    booking1.setPlateNo(bookingValues.getPlateNo());

    return booking1;
  }

}
