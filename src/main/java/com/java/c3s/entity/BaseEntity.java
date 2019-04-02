package com.java.c3s.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;


@MappedSuperclass
@EntityListeners(EntityListener.class)
public class BaseEntity {

  @Column(name = "sysdeleteflag")
  int sysDeleteFlag = 0;

  @Version
  @Column(name = "sysupdatedcount")
  int sysUpdatedCount;

  @Column(name = "lastupdatedby")
  String lastUpdatedBy;

  @Column(name = "syscreatedatetime")
  LocalDateTime sysCreateDateTime;

  @Column(name = "sysupdatedatetime")
  LocalDateTime sysUpdateDateTime;

  public int getSysDeleteFlag() {
    return sysDeleteFlag;
  }

  public void setSysDeleteFlag(int sysDeleteFlag) {
    this.sysDeleteFlag = sysDeleteFlag;
  }

  public int getSysUpdatedCount() {
    return sysUpdatedCount;
  }

  public void setSysUpdatedCount(int sysUpdatedCount) {
    this.sysUpdatedCount = sysUpdatedCount;
  }

  public String getLastUpdatedBy() {
    return lastUpdatedBy;
  }

  public void setLastUpdatedBy(String lastUpdatedBy) {
    this.lastUpdatedBy = lastUpdatedBy;
  }

  public LocalDateTime getSysCreateDateTime() {
    return sysCreateDateTime;
  }

  @PrePersist
  public void setSysCreateDateTime() {
    this.sysCreateDateTime = LocalDateTime.now();
  }

  public LocalDateTime getSysUpdateDateTime() {
    return sysUpdateDateTime;
  }

  @PreUpdate
  public void setSysUpdateDateTime() {
    this.sysUpdateDateTime = LocalDateTime.now();
  }

  public BaseEntity() {
  }

}
