package com.collathon.backendproject.model.controller;

import com.collathon.backendproject.model.domain.User;
import com.collathon.backendproject.model.domain.apireponse.ApiResponseLoginMessage;
import com.collathon.backendproject.model.domain.apireponse.ApiResponseMessage;
import com.collathon.backendproject.model.service.ServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("userService")
    private ServiceInt<User> userService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/save")
    public ResponseEntity<ApiResponseMessage> saveUser(@RequestBody User user) {
        User result = this.userService.saveService(user);
        if (result != null) {
            ApiResponseMessage message = new ApiResponseMessage("Success", "Sign up succusess", "", "");

            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        ApiResponseMessage message = new ApiResponseMessage("Error", "sign up fail", "", "");
        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/login")
    public ResponseEntity<ApiResponseMessage> login(@RequestBody User user) {
        User resultUser = this.userService.getService(user);

        if (resultUser != null) {
            Map<String, Long> mapData = new HashMap<>();
            mapData.put("id", resultUser.getId());
            mapData.put("usingBicycle", resultUser.getUsingBicycle());

            ApiResponseMessage message = new ApiResponseLoginMessage("Success", "Login success", mapData, "", "");

            return new ResponseEntity<>(message, HttpStatus.OK);
        }

        ApiResponseMessage message = new ApiResponseMessage("Error", "sign in fail", "", "");

        return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<ApiResponseMessage> deleteUserData(@PathVariable long id) {

        if (this.userService.deleteService(id)) {
            ApiResponseMessage message = new ApiResponseMessage("Success", "Delete Data succusess", "", "");

            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        ApiResponseMessage message = new ApiResponseMessage("Error", "This id is not in db", "", "");

        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }
}
