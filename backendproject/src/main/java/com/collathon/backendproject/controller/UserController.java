package com.collathon.backendproject.controller;

import com.collathon.backendproject.domain.User;
import com.collathon.backendproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/save")
    public User saveUser(@RequestBody User user) {
        return this.userService.saveUser(user);
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
