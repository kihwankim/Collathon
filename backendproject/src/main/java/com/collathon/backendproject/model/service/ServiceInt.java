package com.collathon.backendproject.model.service;

import com.collathon.backendproject.model.domain.Bicycle;
import com.collathon.backendproject.model.domain.User;

public interface ServiceInt<E> {
    public E saveService(E data);

    public E getService(E data);

    public boolean deleteService(long id);

    public boolean rent(long userId, long bicycleNumber);

    public E getDataFromId(long id);

    public boolean returnBicycle(User user, Bicycle bicycle);
}
