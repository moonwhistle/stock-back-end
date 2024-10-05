package com.example.investment_api.global.exception.exceptions;

import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException {

    private final GlobalErrorCode globalErrorCode;

    public GlobalException(GlobalErrorCode globalErrorCode) {
        super(globalErrorCode.getCustomCode() + ": " + globalErrorCode.getMessage());
        this.globalErrorCode = globalErrorCode;
    }
}
