package com.java.c3s.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.c3s.dao.BookingDao;
import com.java.c3s.entity.Booking;

@Service
public class BookingService {

	@Autowired
	private BookingDao bookingDao;
	
	public Booking addBooking(Booking booking) {
		return bookingDao.save(booking);
		
	}
}
