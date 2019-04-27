package com.java.c3s.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.java.c3s.dao.RoleDao;
import com.java.c3s.dao.UserDao;
import com.java.c3s.entity.User;

@Service
public class UserService {
  @Autowired
  UserDao uDao;

  @Autowired
  RoleDao rDao;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  public User addUser(User user, String name) {
    user.setSysDeleteFlag(0);
    user.setLastUpdatedBy(name);

    if (uDao.findByuserName(user.getUserName()) == null) {

      return uDao.save(user);
    } else {
      return null;
    }

  }

  public List<User> viewUser() {
    return uDao.findAll();
  }

  public Optional<User> findById(Long id) {
    return uDao.findById(id);
  }

  public User editUser(Optional<User> userDetails, User user, String name) {

    User userDetail = userDetails.get();

    if (user.getUserName() != null
        && uDao.findByuserName(user.getUserName()) == null) {

      userDetail.setUserName(user.getUserName());
    }
    if (user.getPassword() != null) {
      String pwd = user.getPassword();
      String encryptPwd = passwordEncoder.encode(pwd);
      userDetail.setPassword(encryptPwd);
    }
    if (user.getEmailId() != null) {
      userDetail.setEmailId(user.getEmailId());
    }
    if (user.getRoles() != null) {
      userDetail.setRoles(user.getRoles());
    }
    userDetail.setLastUpdatedBy(name);
    return uDao.save(userDetail);
  }

  public void deleteUser(Long id) {
    Optional<User> userDetails = findById(id);
    User userDetail = userDetails.get();
    userDetail.setSysDeleteFlag(1);
    uDao.save(userDetail);

  }


}
