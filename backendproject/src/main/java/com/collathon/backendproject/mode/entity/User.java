package com.collathon.backendproject.mode.entity;

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
    private int id;
    private long sequence;
    private String name;
    private String userId;
    private String userPw;

    public User(String name, String userId, String userPw) {
        this.name = name;
        this.userId = userId;
        this.userPw = userPw;
    }
}
