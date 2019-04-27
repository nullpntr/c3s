package com.java.c3s.exception;

public class DataNotFoundException extends Exception {

  // public DataNotFoundException(Long id, String entity) {
  //
  // // System.out.println(id + "is not found");
  // }

  public DataNotFoundException(Long id, String entity) {
    // TODO Auto-generated constructor stub
    super(id + " is not a " + entity);
  }


}
