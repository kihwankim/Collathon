package com.collathon.backendproject.model.controller;

import com.collathon.backendproject.model.domain.ApiResponseMessage;
import com.collathon.backendproject.model.domain.Bicycle;
import com.collathon.backendproject.model.service.ServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bicycle")
public class BicycleController {

    @Autowired
    @Qualifier("bicycleService")
    private ServiceInt<Bicycle> bicycleService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/save")
    public ResponseEntity<ApiResponseMessage> saveBicycle(@RequestBody Bicycle bicycle) {
        Bicycle result = this.bicycleService.saveService(bicycle);
        if (result != null) {
            ApiResponseMessage message = new ApiResponseMessage("Success", "Insert bicycle", "", "");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }

        ApiResponseMessage message = new ApiResponseMessage("Error", "There is same id number", "", "");
        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<ApiResponseMessage> deleteUserData(@PathVariable long id) {

        if (this.bicycleService.deleteService(id)) {
            ApiResponseMessage message = new ApiResponseMessage("Success", "Delete Data succusess", "", "");

            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        ApiResponseMessage message = new ApiResponseMessage("Error", "This id is not in db", "", "");

        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/rent")
    public ResponseEntity<ApiResponseMessage> rentBicycle() {

        return null;
    }
}
