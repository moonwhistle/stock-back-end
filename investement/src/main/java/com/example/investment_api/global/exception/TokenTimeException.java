package com.example.investment_api.global.exception;

import com.example.investment_api.global.exception.exceptions.GlobalErrorCode;
import com.example.investment_api.global.exception.exceptions.GlobalException;

public class TokenTimeException extends GlobalException {

    public TokenTimeException() {
        super(GlobalErrorCode.TOKEN_TIME_EXCEPTION);
    }
}
