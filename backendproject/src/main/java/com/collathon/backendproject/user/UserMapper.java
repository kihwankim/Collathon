package com.collathon.backendproject.user;

import com.collathon.backendproject.user.domain.User;

import java.util.List;

public interface UserMapper {
    public User readUser(String username);
    public List<String> readAuthority(String username);
}
