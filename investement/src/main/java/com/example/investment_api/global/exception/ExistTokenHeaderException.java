package com.example.investment_api.global.exception;

import com.example.investment_api.global.exception.exceptions.GlobalErrorCode;
import com.example.investment_api.global.exception.exceptions.GlobalException;

public class ExistTokenHeaderException extends GlobalException {

    public ExistTokenHeaderException() {
        super(GlobalErrorCode.EXIST_TOKEN_HEADER_EXCEPTION);
    }
}
