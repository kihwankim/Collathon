package com.collathon.backendproject.model.controller;

import com.collathon.backendproject.model.domain.Bicycle;
import com.collathon.backendproject.model.domain.User;
import com.collathon.backendproject.model.domain.apireponse.ApiResponseMessage;
import com.collathon.backendproject.model.service.ServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

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

        if (beforeBicycleData != null && beforeUserData != null &&
                beforeUserData.getUsingBicycle() == -1 && beforeBicycleData.getNowUsingPersonId() == -1) {
            boolean resultOfBicycle = this.bicycleService.rent(userId, bicycleNumber);
            boolean resultOfUser = this.userService.rent(userId, bicycleNumber);

            if (resultOfBicycle && resultOfUser) {
                ApiResponseMessage message = new ApiResponseMessage("Success", "Rent success", "", "");

                return new ResponseEntity<>(message, HttpStatus.OK);
            }

            this.bicycleService.modify(beforeBicycleData);
            this.userService.modify(beforeUserData);
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
            @RequestParam("bicycleNumber") long bicycleNumber,
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude) {


        User beforeUserData = this.userService.getDataFromId(userId);
        Bicycle beforeBicycleData = this.bicycleService.getDataFromId(bicycleNumber);

        List<String> beforeUsedUserList = beforeBicycleData.getLastUserId();
        if (beforeUsedUserList == null) {
            beforeUsedUserList = new LinkedList<>();
        }

        beforeUsedUserList.add(beforeUserData.getUserId());
        if (beforeUsedUserList.size() > 5 && !beforeUsedUserList.remove(beforeUserData.getUserId())) {
            beforeUsedUserList.remove(0);
        }

        Bicycle bicycle = Bicycle.builder()
                .bicycleNumber(bicycleNumber)
                .latitude(latitude)
                .longitude(longitude)
                .startDate(null)
                .endDate(null)
                .lastUserId(beforeUsedUserList)
                .build();

        User user = User.builder()
                .id(userId)
                .build();
        if (beforeBicycleData != null && beforeUserData != null) {
            if (this.bicycleService.returnBicycle(bicycle) && this.userService.returnBicycle(user)) {
                ApiResponseMessage message = new ApiResponseMessage("Success", "Return success", "", "");

                return new ResponseEntity<>(message, HttpStatus.OK);
            }
            this.bicycleService.modify(beforeBicycleData);
            this.userService.modify(beforeUserData);
        }

        ApiResponseMessage message = new ApiResponseMessage("Error", "Return Fail", "", "");

        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }
}