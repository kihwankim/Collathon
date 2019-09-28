package com.collathon.backendproject.user.service;

import com.collathon.backendproject.sequence.dao.SequenceDao;
import com.collathon.backendproject.user.domain.User;
import com.collathon.backendproject.user.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final String USER_SEQ_KEY = "user";
    @Autowired
    private UserDao userDao;
    @Autowired
    private SequenceDao sequenceDao;


    public User saveUser(User user) {
        user.setId(this.sequenceDao.getNextUserId(UserService.USER_SEQ_KEY));
        return this.userDao.saveUser(user);
    }

    public User getUser(User user) {
        return this.userDao.getUser(user).get();
    }

    public void deleteUser(long id) {
        this.userDao.deleteUser(id);
    }
}
