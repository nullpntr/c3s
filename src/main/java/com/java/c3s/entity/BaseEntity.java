package com.java.c3s.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@MappedSuperclass
@EntityListeners(EntityListener.class)
public class BaseEntity {

  @Column(name = "sysdeleteflag", nullable = false, columnDefinition = "Integer default 0")
  // @ColumnDefault("0")
  Integer sysDeleteFlag;

  @Version
  @Column(name = "sysupdatedcount")
  Integer sysUpdatedCount;

  @Column(name = "lastupdatedby")
  String lastUpdatedBy;

  @CreationTimestamp
  @Column(name = "syscreatedatetime")
  LocalDateTime sysCreateDateTime;

  @UpdateTimestamp
  @Column(name = "sysupdatedatetime")
  LocalDateTime sysUpdateDateTime;


  public int getSysDeleteFlag() {
    return sysDeleteFlag;
  }

  public void setSysDeleteFlag(int sysDeleteFlag) {
    this.sysDeleteFlag = sysDeleteFlag;
  }

  // public int getSysUpdatedCount() {
  // return sysUpdatedCount;
  // }
  //
  // public void setSysUpdatedCount(int sysUpdatedCount) {
  // this.sysUpdatedCount = sysUpdatedCount;
  // }

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
