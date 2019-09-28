package com.collathon.backendproject.user.controller;

import com.collathon.backendproject.user.domain.User;
import com.collathon.backendproject.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public User saveUser(@RequestBody User user) {
        return this.userService.saveUser(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        return null;
    }

    @GetMapping("/hello")
    public String hello() {
        return "abc";
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteUserData(@PathVariable long id) {
        this.userService.deleteUser(id);
    }
}
