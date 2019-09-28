package com.collathon.backendproject.model.repository;

import java.util.Optional;

public interface Dao<E> {
    public E save(E user);

    public Optional<E> getOne(E data);

    public void deleteDataFromId(long id);

    public E getOneById(long id);
}
