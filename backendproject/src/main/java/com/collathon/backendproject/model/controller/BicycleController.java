package com.collathon.backendproject.model.controller;

import com.collathon.backendproject.model.domain.Bicycle;
import com.collathon.backendproject.model.domain.apireponse.ApiResponseFindAllCloseBike;
import com.collathon.backendproject.model.domain.apireponse.ApiResponseMessage;
import com.collathon.backendproject.model.service.BicycleService;
import com.collathon.backendproject.model.service.ServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            List<Bicycle> resultListData = ((BicycleService) this.bicycleService).allBicycleData(Double.NaN, Double.NaN);
            ApiResponseMessage message = new ApiResponseFindAllCloseBike("Success", Long.toString(result.getBicycleNumber()), resultListData, "", "");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        ApiResponseMessage message = new ApiResponseMessage("Error", "There is same id number", "", "");

        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<ApiResponseMessage> deleteUserData(@PathVariable long id) {

        if (this.bicycleService.deleteService(id)) {
            List<Bicycle> result = ((BicycleService) this.bicycleService).allBicycleData(Double.NaN, Double.NaN);
            ApiResponseMessage message = new ApiResponseFindAllCloseBike("Success", "Delete Data succusess", result, "", "");

            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        ApiResponseMessage message = new ApiResponseMessage("Error", "This id is not in db", "", "");

        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/getAll")
    public ResponseEntity<ApiResponseMessage> getAllBicycleData() {
        List<Bicycle> result = ((BicycleService) this.bicycleService).allBicycleData(Double.NaN, Double.NaN);
        if (result != null) {
            ApiResponseMessage message = new ApiResponseFindAllCloseBike("Success", "All data", result, "", "");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        ApiResponseMessage message = new ApiResponseMessage("Error", "error", "", "");

        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/findCloseBicycle")
    public ResponseEntity<ApiResponseMessage> findCloseBicycle(
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude) {
        List<Bicycle> result = ((BicycleService) this.bicycleService).allBicycleData(latitude, longitude);
        if (result != null) {
            ApiResponseMessage message = new ApiResponseFindAllCloseBike("Success", "All data", result, "", "");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }

        ApiResponseMessage message = new ApiResponseMessage("Error", "error", "", "");

        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }
}
