package com.collathon.backendproject.model.domain;

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

<<<<<<< HEAD:backendproject/src/main/java/com/collathon/backendproject/user/domain/User.java
=======
    public User(String userId, String userPw) {
        this.userId = userId;
        this.userPw = userPw;
    }

    public User() {
    }
>>>>>>> ed952b3a7df7aa7da3332abd1afa54b4522566c4:backendproject/src/main/java/com/collathon/backendproject/model/domain/User.java
}
