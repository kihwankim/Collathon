package com.collathon.backendproject.model.service;

public interface ServiceInt<E> {
    public E saveService(E data);

    public E getService(E data);

    public boolean deleteService(long id);
}
