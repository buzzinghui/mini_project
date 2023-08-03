package com.example.assignment.common.exception;

import com.example.assignment.common.interfaces.BaseResponseCode;
import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

    private final BaseResponseCode responseCode;

    public ApplicationException(BaseResponseCode responseCode) {
        this(responseCode, responseCode.getMessage(), null);
    }

    public ApplicationException(BaseResponseCode responseCode, String message) {
        this(responseCode, message, null);
    }

    public ApplicationException(BaseResponseCode responseCode, String message, Throwable cause) {
        super(message, cause);
        this.responseCode = responseCode;
    }

}
