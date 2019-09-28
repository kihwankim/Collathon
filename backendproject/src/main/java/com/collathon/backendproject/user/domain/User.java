package com.collathon.backendproject.user.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
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
}
