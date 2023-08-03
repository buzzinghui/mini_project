package com.example.assignment.common.model;

import com.example.assignment.common.enums.CommonResponseCode;
import com.example.assignment.common.interfaces.BaseResponseCode;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class BaseResponse<T> {

    @Schema(description = "Response Code", required = true)
    private BaseResponseCode code;

    @Schema(description = "Localised message to show to the user, if any")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @Schema(description = "Response result")
    private T result;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String debugMessage;

    public static <T> BaseResponse<T> success() {
        return BaseResponse.<T>builder()
                .code(CommonResponseCode.SUCCESS)
                .build();
    }

    public static <T> BaseResponse<T> success(T result) {
        return BaseResponse.<T>builder()
                .code(CommonResponseCode.SUCCESS)
                .result(result)
                .build();
    }

    public static <T> BaseResponse<T> fail(T result) {
        return BaseResponse.<T>builder()
                .code(CommonResponseCode.PARAMETER_VALIDATION_FAILED)
                .result(result)
                .build();
    }

}
