package com.collathon.backendproject.service;

import com.collathon.backendproject.repository.dao.UserDao;
import com.collathon.backendproject.mode.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User saveUser(User user) {
        return this.userDao.saveUser(user);
    }

    public User getUser(User user) {
        return this.userDao.getUser(user).get();
    }

    public void deleteUser(User user) {
        this.userDao.deleteUser(user);
    }
}
