package com.collathon.backendproject.model.repository;

import com.collathon.backendproject.model.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDao implements Dao<User> {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public User save(User user) {
        user.setUsingBicycle(-1);
        return this.mongoTemplate.save(user);
    }

    @Override
    public Optional<User> getOne(User user) {
        System.out.println(user.getUserId());
        Query query = new Query(Criteria.where("userId").is(user.getUserId()));
        User findData = this.mongoTemplate.findOne(query, User.class);
        return Optional.of(findData);
    }

    @Override
    public void deleteDataFromId(long id) {
//        this.mongoTemplate.remove(new Query(), User.class);
    }
}
