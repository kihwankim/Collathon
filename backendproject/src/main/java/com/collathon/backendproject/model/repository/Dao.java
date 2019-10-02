package com.collathon.backendproject.model.repository;

import java.io.IOException;
import java.util.Optional;

public interface Dao<E> {
    public E save(E user) throws IOException;

    public Optional<E> getOne(E data);

    public void deleteDataFromId(long id);

    public E getOneById(long id);
}
