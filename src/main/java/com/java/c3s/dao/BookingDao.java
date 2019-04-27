package com.java.c3s.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.c3s.entity.Booking;
import com.java.c3s.entity.Customer;
import com.java.c3s.entity.ServiceCenter;

@Repository
public interface BookingDao extends JpaRepository<Booking,Long>{

  List<Booking> findByServiceCenter(ServiceCenter sc);
  List<Booking> findByCustomer(Customer c);
  List<Booking> findByBookingDateTime(LocalDateTime bdt);
  List<Booking> findByDeliveryDateTime(LocalDateTime deliveryDateTime);

}
