package com.collathon.backendproject.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoCofig {

    @Bean
    public MongoTemplate createMongoTemplate() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        String databaseName = "colathon";

        return new MongoTemplate(mongoClient, databaseName);
    }
}