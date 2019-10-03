package com.collathon.backendproject.model.service;

import com.collathon.backendproject.model.domain.Bicycle;
import com.collathon.backendproject.model.repository.BicycleDao;
import com.collathon.backendproject.model.repository.Dao;
import com.collathon.backendproject.sequence.dao.SequenceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BicycleService implements ServiceInt<Bicycle> {
    private static final String BICYCLE_SEQ_KEY = "bicycle";
    @Autowired
    @Qualifier("bicycleDao")
    private Dao<Bicycle> bicycleDao;
    @Autowired
    private SequenceDao sequenceDao;

    @Override
    public Bicycle saveService(Bicycle data) {
        data.setBicycleNumber(this.sequenceDao.getNextUserId(BicycleService.BICYCLE_SEQ_KEY));

        Optional<Bicycle> findBicycle = this.bicycleDao.getOne(data);
        if (findBicycle.isPresent()) {
            return null;
        }

        return this.bicycleDao.save(data);
    }

    @Override
    public Bicycle getService(Bicycle data) {
        Optional<Bicycle> result = this.bicycleDao.getOne(data);
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }

    @Override
    public boolean deleteService(long id) {
        if (this.bicycleDao.getOneById(id) != null) {
            this.bicycleDao.deleteDataFromId(id);

            return true;
        }
        return false;
    }

    public List<Bicycle> allBicycleData(double latitude, double longitude) {
        return ((BicycleDao) this.bicycleDao).allBicycleData(latitude, longitude);
    }
}
