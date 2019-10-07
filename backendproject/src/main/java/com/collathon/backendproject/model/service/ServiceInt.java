package com.collathon.backendproject.model.service;

public interface ServiceInt<E> {
    public E saveService(E data);

    public E getService(E data);

    public boolean deleteService(long id);

    public boolean rent(long userId, long bicycleNumber);

    public E getDataFromId(long id);

    public boolean returnBicycle(E component);

    public boolean modify(E component);
}
