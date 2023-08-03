package com.example.assignment.common.enums;

import com.example.assignment.common.interfaces.BaseResponseCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Schema(title = "Application Response Code")
public enum ApplicationResponseCode implements BaseResponseCode {

    APPLICATION_GENERAL_ERROR("A_1000", "Application General Error"),
    APPLICATION_INVALID_LOGIN("A_1001", "User not found"),
    APPLICATION_INVALID_PASSWORD("A_1002", "User password incorrect"),
    APPLICATION_INVALID_NEW_CONFIRM_PASSWORD("A_1003", "New password and confirmed password do not match")
    ;

    private final String enumValue;
    private final String message;
}
