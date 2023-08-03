package com.example.assignment.common.exception;

import com.example.assignment.common.interfaces.BaseResponseCode;

public class UnauthorizedException extends ApplicationException {

    public UnauthorizedException(BaseResponseCode responseCode) {
        super(responseCode, responseCode.getMessage());
    }

    public UnauthorizedException(BaseResponseCode responseCode, String message) {
        super(responseCode, message);
    }

}
