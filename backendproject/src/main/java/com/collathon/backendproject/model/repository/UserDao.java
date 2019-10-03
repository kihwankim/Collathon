package com.collathon.backendproject.model.repository;

import com.collathon.backendproject.model.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDao implements Dao<User> {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public User save(User data) {
        data.setUsingBicycle(-1);
        return this.mongoTemplate.save(data);
    }

    @Override
    public Optional<User> getOne(User user) {
        Query query = new Query(Criteria.where("userId").is(user.getUserId()));
        User findData = this.mongoTemplate.findOne(query, User.class);
        if (findData == null) {
            return Optional.empty();
        }

        return Optional.of(findData);
    }

    @Override
    public void deleteDataFromId(long id) {
        this.mongoTemplate.remove(new Query(Criteria.where("_id").is(id)), User.class);
    }

    @Override
    public User getOneById(long id) {
        return this.mongoTemplate.findById(id, User.class);
    }

    @Override
    public boolean rent(User changeData) {
//        Query query = new Query(Criteria.where("_id").is(id));
        return false;
    }
}
