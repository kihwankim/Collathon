package com.collathon.backendproject.model.repository;

import com.collathon.backendproject.model.domain.Bicycle;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BicycleDao implements Dao<Bicycle> {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Bicycle save(Bicycle data) {
        data.setNowUsingPersonId(-1);
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

    public List<Bicycle> allBicycleData() {
        return this.mongoTemplate.findAll(Bicycle.class);
    }

    @Override
    public boolean rent(Bicycle changeData) {
        Query query = new Query(Criteria.where("_id").is(changeData.getBicycleNumber()));
        Update update = new Update()
                .set("nowUsingPersonId", changeData.getNowUsingPersonId())
                .set("startDate", changeData.getStartDate())
                .set("endDate", changeData.getEndDate());
        UpdateResult result = this.mongoTemplate.updateFirst(query, update, Bicycle.class);

        return result.wasAcknowledged();
    }

}
