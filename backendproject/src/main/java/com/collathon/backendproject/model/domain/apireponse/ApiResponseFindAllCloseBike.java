package com.collathon.backendproject.model.domain.apireponse;

import lombok.Data;

import java.util.List;

@Data
public class ApiResponseFindAllCloseBike extends ApiResponseMessage {
    private List<?> data;

    public ApiResponseFindAllCloseBike(String status, String message, List<?> data, String errorCode, String errorMessage) {
        super(status, message, errorCode, errorMessage);
        this.data = data;
    }
}
