package com.collathon.backendproject.sequence.exception;

public class SequenceException extends RuntimeException {
    private String errMsg;

    public SequenceException(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getErrMsg() {
        return this.errMsg;
    }
}
