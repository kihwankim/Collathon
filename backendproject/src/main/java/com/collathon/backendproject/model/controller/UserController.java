package com.collathon.backendproject.model.controller;

import com.collathon.backendproject.model.domain.ApiResponseMessage;
import com.collathon.backendproject.model.domain.User;
import com.collathon.backendproject.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/save")
    public ResponseEntity<ApiResponseMessage> saveUser(@RequestBody User user) {
        User result = this.userService.saveService(user);
        if (result != null) {
            ApiResponseMessage message = new ApiResponseMessage("Success", "Login succusess", "", "");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        ApiResponseMessage message = new ApiResponseMessage("Error", "sign up fail", "", "");
        return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/login")
    public User login(@RequestParam(value = "userId") String id, @RequestParam(value = "userPw") String pw) {
        User user = new User(id, pw);

        return this.userService.getService(user);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping(value = "/delete/{id}")
    public void deleteUserData(@PathVariable long id) {
        this.userService.deleteService(id);
    }
}
