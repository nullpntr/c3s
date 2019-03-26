package com.java.c3s.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.c3s.dao.UserDao;
import com.java.c3s.entity.User;

@Service
public class UserService {
	
	@Autowired
	UserDao uDao;

	public User addUser(User user) {
		return uDao.save(user);
	}
}
