package com.collathon.backendproject.model.domain.apireponse;

import lombok.Data;

import java.util.Map;

@Data
public class ApiResponseMessage {
    private String errorCode;
    private String errorMessage;
    private String message;
    private String status;

    public ApiResponseMessage() {
    }


    public ApiResponseMessage(String status, String message, String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.status = status;
        this.message = message;
        this.errorMessage = errorMessage;
    }
}
