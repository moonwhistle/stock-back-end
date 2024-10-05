package com.example.investment_api.global.exception.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum GlobalErrorCode {

    EXIST_TOKEN_HEADER_EXCEPTION(HttpStatus.NOT_FOUND, "T001","토큰을 찾을 수 없습니다."),
    TOKEN_TIME_EXCEPTION(HttpStatus.CONFLICT, "T002", "토큰 시간이 만료되었습니다."),
    TOKEN_VERIFY_EXCEPTION(HttpStatus.CONFLICT, "T003", "토큰을 검증할 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String customCode;
    private final String message;

    GlobalErrorCode(HttpStatus httpStatus, String customCode, String message) {
        this.httpStatus = httpStatus;
        this.customCode = customCode;
        this.message = message;
    }
}
