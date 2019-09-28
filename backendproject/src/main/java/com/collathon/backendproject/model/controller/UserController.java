package com.collathon.backendproject.model.controller;

import com.collathon.backendproject.model.domain.User;
import com.collathon.backendproject.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/save")
    public User saveUser(@RequestBody User user) {
        return this.userService.saveService(user);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/login")
    public User login(@RequestParam(value = "id") String id, @RequestParam(value = "pw") String pw) {
        User user = new User(id, pw);

        return this.userService.getService(user);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping(value = "/delete/{id}")
    public void deleteUserData(@PathVariable long id) {
        this.userService.deleteService(id);
    }
}
