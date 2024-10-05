package com.example.investment_api.global.exception;

import com.example.investment_api.global.exception.exceptions.GlobalErrorCode;
import com.example.investment_api.global.exception.exceptions.GlobalException;

public class TokenVerifyException extends GlobalException {

    public TokenVerifyException() {
        super(GlobalErrorCode.TOKEN_VERIFY_EXCEPTION);
    }
}
