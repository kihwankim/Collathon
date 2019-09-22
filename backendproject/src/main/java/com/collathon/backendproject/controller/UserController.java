package com.collathon.backendproject.controller;

import com.collathon.backendproject.mode.entity.User;
import com.collathon.backendproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/save")
    public User saveUser(@RequestBody User user) {
        return this.userService.saveUser(user);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/hello")
    public String hello() {
        return "abc";
    }
}
