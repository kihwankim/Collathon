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

    public User(String name, String userId, String userPw) {
        this.name = name;
        this.userId = userId;
        this.userPw = userPw;
        this.usingBicycle = -1;
    }

    public User(String userId, String userPw) {
        this.userId = userId;
        this.userPw = userPw;
    }

    public User(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.userId = user.getUserId();
        this.userPw = user.getUserPw();
        this.usingBicycle = user.getUsingBicycle();
    }

    public User() {
    }
}
