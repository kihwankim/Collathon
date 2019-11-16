package com.collathon.backendproject.model.service;

import com.collathon.backendproject.model.domain.Bicycle;
import com.collathon.backendproject.model.repository.BicycleDao;
import com.collathon.backendproject.model.repository.Dao;
import com.collathon.backendproject.sequence.dao.SequenceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public boolean rent(long userId, long bicycleNumber) {
        Calendar nowCal = Calendar.getInstance();
        nowCal.setTime(new Date());
        Calendar returnCal = (Calendar) nowCal.clone();
        returnCal.add(Calendar.MINUTE, 30);

        Bicycle bicycle = Bicycle.builder()
                .bicycleNumber(bicycleNumber)
                .nowUsingPersonId(userId)
                .startDate(nowCal)
                .startDate(returnCal)
                .build();

        return this.bicycleDao.rent(bicycle);
    }

    private Date calculateDate(Date nowDate, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        calendar.add(calendar.MINUTE, minute);
        Date date = new Date(calendar.getTimeInMillis());

        return date;
    }

    @Override
    public Bicycle getDataFromId(long id) {
        return this.bicycleDao.getOneById(id);
    }

    @Override
    public boolean returnBicycle(Bicycle bicycle) {
        if (bicycle != null && bicycle.getLastUserId() != null) {
            while (bicycle.getLastUserId().size() > 5) {
                bicycle.getLastUserId().remove(0);
            }
        }

        return this.bicycleDao.returnBicycle(bicycle);
    }

    @Override
    public boolean modify(Bicycle component) {
        return this.bicycleDao.modifyBefore(component) != null;
    }

    public List<Bicycle> allBicycleData(double latitude, double longitude) {
        List<Bicycle> allBicycleList = ((BicycleDao) this.bicycleDao).allBicycleData();

        if (Double.isNaN(latitude)) {
            return allBicycleList;
        }

        return allBicycleList.stream()
                .filter(data -> latitude - 0.03 < data.getLatitude())
                .filter(data -> data.getLatitude() < latitude + 0.03)
                .filter(data -> longitude - 0.015 < data.getLongitude())
                .filter(data -> data.getLongitude() < longitude + 0.015)
                .collect(Collectors.toList());
    }
}
