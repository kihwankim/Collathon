package com.collathon.backendproject.model.controller;

import com.collathon.backendproject.model.domain.apireponse.ApiResponseMessage;
import com.collathon.backendproject.model.domain.Bicycle;
import com.collathon.backendproject.model.domain.User;
import com.collathon.backendproject.model.service.ServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rental")
public class RentController {

    @Autowired
    @Qualifier("bicycleService")
    private ServiceInt<Bicycle> bicycleService;

    @Autowired
    @Qualifier("userService")
    private ServiceInt<User> userService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/rent")
    public ResponseEntity<ApiResponseMessage> rentBicycle(@RequestParam("userId") long userId, @RequestParam("bicycleNumber") long bicycleNumber) {


        ApiResponseMessage message = new ApiResponseMessage("Success", "hello", "", "");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/return")
    public ResponseEntity<ApiResponseMessage> returnBicycle(@RequestParam("userId") long userId, @RequestParam("bicycleNumber") long bicycleNumber) {


        ApiResponseMessage message = new ApiResponseMessage("Success", "hello", "", "");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}