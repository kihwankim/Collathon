package com.collathon.backendproject.model.domain.apireponse;

import lombok.Data;

import java.util.Map;

@Data
public class ApiResponseLoginMessage extends ApiResponseMessage {
    Map<?, ?> data;

    public ApiResponseLoginMessage(String status, String message, Map<?, ?> data, String errorCode, String errorMessage) {
        super(status, message, errorCode, errorMessage);
        this.data = data;
    }
}
