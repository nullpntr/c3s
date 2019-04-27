package com.java.c3s.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.c3s.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

  User findByuserName(String username);

}
