package com.collathon.backendproject.model.repository;

import com.collathon.backendproject.model.domain.Bicycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BicycleDao implements Dao<Bicycle> {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Bicycle save(Bicycle data) {
        return <Bicycle>this.mongoTemplate.save(data);
    }

    @Override
    public Optional<Bicycle> getOne(Bicycle data) {
        Bicycle bicycle = this.mongoTemplate.findOne(
                new Query(Criteria.where("_id").is(data.getBicycleNumber())), Bicycle.class);

        if (bicycle == null) {
            return Optional.empty();
        }

        return Optional.of(bicycle);
    }

    @Override
    public void deleteDataFromId(long id) {
        this.mongoTemplate.remove(new Query(Criteria.where("_id").is(id)), Bicycle.class);
    }

    @Override
    public Bicycle getOneById(long id) {
        return this.mongoTemplate.findById(id, Bicycle.class);
    }
}
