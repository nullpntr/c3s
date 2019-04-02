package com.java.c3s.entity;

import javax.persistence.PrePersist;

public class EntityListener {
  @PrePersist
  void setSystemCreateDateTime() {

  }

  public EntityListener() {

  }

}
