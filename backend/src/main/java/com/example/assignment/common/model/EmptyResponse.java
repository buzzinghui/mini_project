package com.example.assignment.common.model;

import com.example.assignment.common.enums.CommonResponseCode;
import com.example.assignment.common.interfaces.BaseResponseCode;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class EmptyResponse extends BaseResponse<Void> {

    private static final EmptyResponse SUCCESS_RESPONSE = EmptyResponse.builder()
            .code(CommonResponseCode.SUCCESS)
            .build();

    public static EmptyResponse success() {
        return SUCCESS_RESPONSE;
    }

    @Hidden
    @Override
    public Void getResult() {
        return null;
    }
}
