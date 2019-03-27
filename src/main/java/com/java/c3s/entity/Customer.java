package com.java.c3s.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity(name = "customer")

public class Customer extends User {
  // @Id
  // @GeneratedValue(strategy = GenerationType.AUTO)
  // @Column(name = "id")
  // private Long id;
  @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Booking> bookings;

}
