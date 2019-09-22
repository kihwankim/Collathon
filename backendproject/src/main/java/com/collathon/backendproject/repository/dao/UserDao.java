package com.collathon.backendproject.repository.dao;

import com.collathon.backendproject.mode.entity.User;
import com.collathon.backendproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDao {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    MongoOperations mongoOperations;

//    public int getNextUserId(String key) {
//        Query query = new Query(Criteria.where("_id").is(key));
//
//        Update update = new Update();
//        update.inc("sequence", 1);
//
//        FindAndModifyOptions findAndModifyOptions = new FindAndModifyOptions();
//        findAndModifyOptions.returnNew(true);
//
//        SequenceI
//    }

    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    public Optional<User> getUser(User user) {
        return this.userRepository.findOne(Example.of(user));
    }

    public void deleteUser(User user) {
        this.userRepository.delete(user);
    }
}
