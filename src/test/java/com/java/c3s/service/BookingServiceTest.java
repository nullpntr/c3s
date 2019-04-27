package com.java.c3s.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.java.c3s.dao.BookingDao;
import com.java.c3s.dao.CustomerDao;
import com.java.c3s.dao.ServiceCenterDao;
import com.java.c3s.entity.Booking;
import com.java.c3s.entity.Customer;
import com.java.c3s.entity.ServiceCenter;

public class BookingServiceTest {

  @Mock
  private BookingDao bookingDao;

  @Mock
  private ServiceCenterDao serviceCenterDao;

  @Mock
  private CustomerDao customerDao;

  @InjectMocks
  private BookingService service;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void shouldAddBooking() {

    List<String> errors = new ArrayList<>();
    Booking b = new Booking();
    LocalDateTime deliveryDateTime = LocalDateTime.of(2019, 04, 10, 12, 30, 00);
    b.setDeliveryDateTime(deliveryDateTime);
    Long id = (long) 225;
    b.setId(id);
    Customer customer = new Customer();
    Long customer_id = (long) 96;
    customer.setId(customer_id);
    b.setCustomer(customer);
    String plateNo = "TN 23 AJ 0909";
    b.setPlateNo(plateNo);
    ServiceCenter sc1 = new ServiceCenter();
    sc1.setId((long) 21);
    b.setServiceCenter(sc1);

    // ServiceCenter sc = b.getServiceCenter();
    ServiceCenter sc = new ServiceCenter();

    Optional<ServiceCenter> osc = Optional.of(sc);
    Mockito.when(serviceCenterDao.findById(Mockito.any())).thenReturn(osc);

    // Customer cust = b.getCustomer();
    Customer cust = new Customer();
    Optional<Customer> c = Optional.of(cust);
    Mockito.when(customerDao.findById(Mockito.anyLong())).thenReturn(c);
    Mockito.when(bookingDao.save(b)).thenReturn(b);
    Booking result = service.addBooking(b, errors);
    assertNotNull(result.getId());
  }

  @Test
  public void shouldNotAddBookingNoCustpassed() {

    List<String> errors = new ArrayList<>();
    Booking b = new Booking();
    LocalDateTime deliveryDateTime = LocalDateTime.of(2019, 04, 10, 12, 30, 00);
    b.setDeliveryDateTime(deliveryDateTime);
    Long id = (long) 225;
    b.setId(id);
    String plateNo = "TN 23 AJ 0909";
    b.setPlateNo(plateNo);

    ServiceCenter sc1 = new ServiceCenter();
    sc1.setId((long) 21);
    b.setServiceCenter(sc1);

    ServiceCenter sc_new = new ServiceCenter();
    Optional<ServiceCenter> osc = Optional.of(sc_new);
    Mockito.when(serviceCenterDao.findById(Mockito.any())).thenReturn(osc);
    Customer cust = new Customer();
    Optional<Customer> c = Optional.of(cust);
    Mockito.when(customerDao.findById(Mockito.anyLong())).thenReturn(c);
    Mockito.when(bookingDao.save(b)).thenReturn(b);
    Booking result = service.addBooking(b, errors);
    List<String> expected = new ArrayList();
    expected.add("Customer details was not passed from the request");
    assertEquals(errors, expected);
  }

  @Test
  public void shouldNotAddBookingNoScPassed() {
    List<String> errors = new ArrayList<>();
    Booking b = new Booking();
    LocalDateTime deliveryDateTime = LocalDateTime.of(2019, 04, 10, 12, 30, 00);
    b.setDeliveryDateTime(deliveryDateTime);
    Long id = (long) 225;
    b.setId(id);
    Customer customer = new Customer();
    Long customer_id = (long) 96;
    customer.setId(customer_id);
    b.setCustomer(customer);
    String plateNo = "TN 23 AJ 0909";
    b.setPlateNo(plateNo);



    ServiceCenter sc_new = new ServiceCenter();

    Optional<ServiceCenter> osc = Optional.of(sc_new);
    Mockito.when(serviceCenterDao.findById(Mockito.any())).thenReturn(osc);

    Customer cust = new Customer();
    Optional<Customer> c = Optional.of(cust);
    Mockito.when(customerDao.findById(Mockito.anyLong())).thenReturn(c);
    Mockito.when(bookingDao.save(b)).thenReturn(b);
    Booking result = service.addBooking(b, errors);
    List<String> expected = new ArrayList();
    expected.add("service was not passed from the request");
    assertEquals(errors, expected);
  }

  @Test
  public void shouldshowAll() {
    List<Booking> bookings = new ArrayList();
    Mockito.when(bookingDao.findAll()).thenReturn(bookings);
    List<Booking> result = service.showAll();
    assertNotNull(result);
  }

  @Test
  public void shouldfindById() {
    Booking b = new Booking();
    // b.setId((long)33);
    Optional<Booking> ob = Optional.of(b);
    Mockito.when(bookingDao.findById(Mockito.anyLong())).thenReturn(ob);
    Optional<Booking> result = service.findById((long) 33);
    assertNotNull(result);
  }

  @Test
  public void shouldEditBookingDetail() {
    Booking bookingDetails = new Booking();
    Customer cust = new Customer();
    cust.setId((long)21);
    bookingDetails.setCustomer(cust);
    bookingDetails.setPlateNo("TN 23 MC 0123");
    
    Customer c = new Customer();
    Optional<Customer> oc = Optional.of(c);
    Booking b = new Booking();
    Optional<Booking> ob = Optional.of(bookingDetails);
    Mockito.when(customerDao.findById(Mockito.anyLong())).thenReturn(oc);
    Mockito.when(bookingDao.save(Mockito.any())).thenReturn(b);
    Booking result = service.editBookingDetail(ob, bookingDetails);
    assertNotNull(result);

  }

  @Test
  public void shouldEditDeliveryDate() {
    Booking bookingDetail = new Booking();
    Optional<Booking> ob = Optional.of(bookingDetail);
    LocalDateTime deliveryDateTime = LocalDateTime.of(2019, 8, 12, 12, 50, 00);
    bookingDetail.setDeliveryDateTime(deliveryDateTime);
    Mockito.when(bookingDao.save(Mockito.any())).thenReturn(bookingDetail);
    Booking result = service.editDeliveryDate(ob, bookingDetail);
    assertNotNull(result);
  }

  @Test
  public void shouldDeleteBookingDetail() {
    Booking bookingDetail = new Booking();
    Long id = (long) 21;
    Optional<Booking> ob = Optional.of(bookingDetail);

    Mockito.when(bookingDao.save(Mockito.any())).thenReturn(bookingDetail);
    service.deleteBookingDetail(ob, id);
    Booking booking = ob.get();
    assertEquals(booking.getSysDeleteFlag(), 1);
  }

  @Test
  public void shouldfindByServiceCenterId() {
    // ServiceCenter sc = new ServiceCenter();
    //
    // sc.setId(sc_id);
    // Long sc_id = null;

    Long sc_id = (long) 21;
    ServiceCenter sc1 = new ServiceCenter();
    Optional<ServiceCenter> os = Optional.of(sc1);
    Mockito.when(serviceCenterDao.findById(Mockito.anyLong())).thenReturn(os);
    List<Booking> booking = new ArrayList();
    
    Mockito.when(bookingDao.findByServiceCenter(Mockito.any()))
        .thenReturn(booking);
    List<Booking> result = service.findByServiceCenterId(sc_id);
    assertNotNull(result);
    // int value = result.size();
    // assertNotEquals(0, value);
  }

  @Test
  public void shouldfindByCustomerId() {
    Long c_id = 21L;
    ServiceCenter sc1 = new ServiceCenter();
    Customer c = new Customer();
    Optional<Customer> oc = Optional.of(c);
    Mockito.when(customerDao.findById(Mockito.anyLong())).thenReturn(oc);
    List<Booking> bookings = new ArrayList();
    bookings.add(new Booking());

    Mockito.when(bookingDao.findByCustomer(Mockito.any())).thenReturn(bookings);
    List<Booking> result = service.findByCustomerId(c_id);
    assertEquals(1, result.size());
  }

  @Test
  public void shouldfindByBookingTime() {
    LocalDateTime bookingDateTime = LocalDateTime.of(2019, 4, 12, 13, 00, 00);
    List<Booking> booking = new ArrayList();
    Mockito.when(bookingDao.findByBookingDateTime(Mockito.any()))
        .thenReturn(booking);
    List<Booking> result = service.findByBookingTime(bookingDateTime);
    assertNotNull(result);
  }
  
  @Test
  public void shouldfindByDeliveryTime() {
    LocalDateTime deliveryDateTime = LocalDateTime.of(2029, 4, 4, 12, 03, 44);
    List<Booking> booking = new ArrayList();
    Mockito.when(bookingDao.findByDeliveryDateTime(Mockito.any()))
        .thenReturn(booking);
    List<Booking> result = service.findByDeliveryTime(deliveryDateTime);
    assertNotNull(result);
  }
}
