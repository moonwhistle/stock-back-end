package com.example.investment_api.global.exception.exceptionhandler;

import com.example.investment_api.global.exception.exceptionhandler.dto.ErrorResponse;

import com.example.investment_api.global.exception.exceptions.GlobalErrorCode;
import com.example.investment_api.global.exception.exceptions.GlobalException;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ErrorResponse> handleException(GlobalException e) {
        GlobalErrorCode globalErrorCode = e.getGlobalErrorCode();
        ErrorResponse errorResponse = new ErrorResponse(globalErrorCode.getCustomCode(), globalErrorCode.getMessage());
        return ResponseEntity.status(globalErrorCode.getHttpStatus()).body(errorResponse);
    }
}
