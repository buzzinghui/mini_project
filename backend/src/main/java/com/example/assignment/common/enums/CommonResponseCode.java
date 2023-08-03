package com.example.assignment.common.enums;

import com.example.assignment.common.interfaces.BaseResponseCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Schema(title = "Common Response Code")
public enum CommonResponseCode implements BaseResponseCode {

    SUCCESS("200", "Success"),
    PARAMETER_VALIDATION_FAILED("400", "Parameter Validation Failed"),
    UNAUTHORIZED("401", "Unauthorized"),

    INTERNAL_ERROR("500", "Internal Error");

    private final String enumValue;
    private final String message;

}
