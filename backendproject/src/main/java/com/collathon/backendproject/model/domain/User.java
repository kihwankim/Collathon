package com.collathon.backendproject.model.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "user")
public class User {
    @Id
    private long id;
    private String name;
    private String userId;
    private String userPw;
    private long usingBicycle;
}
