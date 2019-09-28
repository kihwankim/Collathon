package com.collathon.backendproject.user.repository;

import com.collathon.backendproject.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
public class UserDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public User saveUser(User user) {
        return this.mongoTemplate.save(user);
    }

    public Optional<User> getUser(User user) {
        return Optional.of(this.mongoTemplate.
                findOne(new Query(where("getUserId").is(user.getUserId())), User.class));
    }

    public void deleteUser(long userId) {
//        this.mongoTemplate.remove(new Query(), User.class);
    }
}
