package com.example.assignment.config;

import com.example.assignment.common.enums.CommonResponseCode;
import com.example.assignment.common.exception.ApplicationException;
import com.example.assignment.common.model.EmptyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebInputException;

import javax.naming.AuthenticationException;
import java.net.BindException;
import java.nio.file.AccessDeniedException;

@ControllerAdvice
@RequiredArgsConstructor
@Log4j2
public class RestAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public EmptyResponse bindException(BindException e) {
        log.error("BAD_REQUEST {}", e::toString);
        return EmptyResponse.builder()
                .code(CommonResponseCode.PARAMETER_VALIDATION_FAILED)
                .message(CommonResponseCode.PARAMETER_VALIDATION_FAILED.getMessage())
                .debugMessage(e.toString())
                .build();
    }

    @ExceptionHandler({ServerWebInputException.class, HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public EmptyResponse bindException(NestedRuntimeException e) {
        log.error("BAD_REQUEST {}", e::toString);
        return EmptyResponse.builder()
                .code(CommonResponseCode.PARAMETER_VALIDATION_FAILED)
                .message(CommonResponseCode.PARAMETER_VALIDATION_FAILED.getMessage())
                .debugMessage(e.toString())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public EmptyResponse exception(Exception e) {
        log.error("INTERNAL_SERVER_ERROR {}", e::toString);
        return EmptyResponse.builder()
                .code(CommonResponseCode.INTERNAL_ERROR)
                .message(CommonResponseCode.INTERNAL_ERROR.getMessage())
                .debugMessage(e.toString())
                .build();
    }

    @ExceptionHandler(ApplicationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public EmptyResponse applicationException(ApplicationException e) {
        log.error("APPLICATION_EXCEPTION {}", e::toString);
        return EmptyResponse.builder()
                .code(e.getResponseCode())
                .message(e.getResponseCode().getMessage())
                .debugMessage(e.toString())
                .build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public EmptyResponse accessDeniedException(AccessDeniedException e) {
        log.error("Access Denied Exception {}", e::toString);
        return EmptyResponse.builder()
                .code(CommonResponseCode.UNAUTHORIZED)
                .message(CommonResponseCode.UNAUTHORIZED.getMessage())
                .debugMessage(e.toString())
                .build();
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public EmptyResponse authenticationException(AuthenticationException e) {
        log.error("Authentication Exception {}", e::toString);
        return EmptyResponse.builder()
                .code(CommonResponseCode.UNAUTHORIZED)
                .message(CommonResponseCode.UNAUTHORIZED.getMessage())
                .debugMessage(e.toString())
                .build();
    }

}
