package com.collathon.backendproject.repository;

import com.collathon.backendproject.mode.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Integer> {
}
