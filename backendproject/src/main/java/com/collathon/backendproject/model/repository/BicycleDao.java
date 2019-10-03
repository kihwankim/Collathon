package com.collathon.backendproject.model.repository;

import com.collathon.backendproject.model.domain.Bicycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BicycleDao implements Dao<Bicycle> {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Bicycle save(Bicycle data) {
        return this.mongoTemplate.save(data);
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

    @Override
    public boolean updateForRentFromId(Bicycle changeData) {
        Bicycle isThereData = this.getOneById(changeData.getBicycleNumber());
        if (isThereData != null) {
            this.mongoTemplate.save(isThereData);
        }
        return false;
    }

    public List<Bicycle> allBicycleData(double latitude, double longitude) {
        List<Bicycle> bicycleList = this.mongoTemplate.findAll(Bicycle.class);
        return bicycleList.stream()
                .filter(data -> latitude - 0.03 < data.getLatitude())
                .filter(data -> data.getLatitude() < latitude + 0.03)
                .filter(data -> longitude - 0.015 < data.getLongitude())
                .filter(data -> data.getLongitude() < longitude + 0.015)
                .collect(Collectors.toList());
    }
}
