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
    public ResponseEntity<ApiResponseMessage> rentBicycle(
            @RequestParam("userId") long userId,
            @RequestParam("bicycleNumber") long bicycleNumber) {

        User beforeUserData = this.userService.getDataFromId(userId);
        Bicycle beforeBicycleData = this.bicycleService.getDataFromId(bicycleNumber);

        if (beforeUserData.getUsingBicycle() != -1 || beforeBicycleData.getNowUsingPersonId() != -1) {
            boolean resultOfBicycle = this.bicycleService.rent(userId, bicycleNumber);
            boolean resultOfUser = this.userService.rent(userId, bicycleNumber);

            if (resultOfBicycle && resultOfUser) {
                ApiResponseMessage message = new ApiResponseMessage("Error", "he have already been using bicycle", "", "");

                return new ResponseEntity<>(message, HttpStatus.OK);
            } else {
                // 원상태로 돌리기 -> 반납 코드 작성 후 반납 코드 응용할 것
            }
        }

        ApiResponseMessage message = new ApiResponseMessage("Error",
                "You have already been using bicycle or This bicycle is already using",
                "", "");

        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/return")
    public ResponseEntity<ApiResponseMessage> returnBicycle(
            @RequestParam("userId") long userId,
            @RequestParam("bicycleNumber") long bicycleNumber) {

        ApiResponseMessage message = new ApiResponseMessage("Success", "hello", "", "");
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}