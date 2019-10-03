package com.collathon.backendproject.model.service;

import com.collathon.backendproject.model.domain.User;
import com.collathon.backendproject.model.repository.Dao;
import com.collathon.backendproject.sequence.dao.SequenceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements ServiceInt<User> {
    private static final String USER_SEQ_KEY = "user";
    @Autowired
    @Qualifier("userDao")
    private Dao<User> userDao;
    @Autowired
    private SequenceDao sequenceDao;


    @Override
    public User saveService(User user) {
        user.setId(this.sequenceDao.getNextUserId(UserService.USER_SEQ_KEY));
        Optional<User> findUser = this.userDao.getOne(user);
        if (findUser.isPresent()) {
            return null;
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
    public boolean deleteService(long id) {
        if (this.userDao.getOneById(id) != null) {
            this.userDao.deleteDataFromId(id);

            return true;
        }

        return false;
    }

    @Override
    public boolean rent(long userId, long bicycleNumber) {
        return false;
    }

    @Override
    public User getDataFromId(long id) {
        return this.userDao.getOneById(id);
    }
}
