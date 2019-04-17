package com.java.c3s.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.c3s.entity.Roles;

public interface RoleDao extends JpaRepository<Roles, Long> {

}
