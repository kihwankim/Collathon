package com.collathon.backendproject.model.repository;

import com.collathon.backendproject.model.domain.Bicycle;

import java.util.Optional;

public class BicyvleDao implements Dao<Bicycle> {
    @Override
    public Bicycle save(Bicycle user) {
        return null;
    }

    @Override
    public Optional<Bicycle> getOne(Bicycle data) {
        return Optional.empty();
    }

    @Override
    public void deleteDataFromId(long id) {

    }
}
