package com.collathon.backendproject.model.service;

import com.collathon.backendproject.sequence.dao.SequenceDao;
import com.collathon.backendproject.model.domain.User;
import com.collathon.backendproject.model.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements ServiceInt<User> {
    private static final String USER_SEQ_KEY = "user";
    @Autowired
    private UserDao userDao;
    @Autowired
    private SequenceDao sequenceDao;


    @Override
    public User saveService(User user) {
        user.setId(this.sequenceDao.getNextUserId(UserService.USER_SEQ_KEY));
        Optional<User> findUser = this.userDao.getOne(user);
        if(findUser.isPresent()){

        }

        return this.userDao.save(user);
    }

    @Override
    public User getService(User user) {
        Optional<User> result = this.userDao.getOne(user);
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }

    @Override
    public void deleteService(long id) {
        this.userDao.deleteDataFromId(id);
    }
}
