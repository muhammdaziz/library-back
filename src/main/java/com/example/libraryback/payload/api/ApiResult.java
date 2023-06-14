package com.example.libraryback.payload.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult<E> {

    private E data;

    private String message;

    private boolean success;

    private List<ErrorData> errors;

    private ApiResult() {
        this.success = true;
    }

    private ApiResult(E data) {
        this.data = data;
        this.success = true;
    }

    private ApiResult(String message, E data) {
        this.data = data;
        this.success = true;
        this.message = message;
    }

    private ApiResult(List<ErrorData> errors) {
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof ApiResult<?> that))
            return false;

        return this.isSuccess() == that.isSuccess() &&
                Objects.equals(this.getData(), that.getData()) &&
                Objects.equals(this.getErrors(), that.getErrors()) &&
                Objects.equals(this.getMessage(), that.getMessage());
    }

    public static <T> ApiResult<T> successResponse() {
        return new ApiResult<>();
    }

    public static <T> ApiResult<T> successResponse(T data) {
        return new ApiResult<>(data);
    }

    public static <T> ApiResult<T> successResponse(String message) {
        return new ApiResult<>(message, null);
    }

    public static <T> ApiResult<T> successResponse(String message, T data) {
        return new ApiResult<>(message, data);
    }


    public static ApiResult<List<ErrorData>> failResponse(List<ErrorData> errors) {
        return new ApiResult<>(errors);
    }

    public static ApiResult<List<ErrorData>> failResponse(String msg, Integer code) {

        List<ErrorData> errorDataList = List.of(new ErrorData(msg, code));

        return failResponse(errorDataList);
    }

}
