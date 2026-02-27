package com.GenNext.employee_management.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ApiErrorResponse<T> {
    private boolean success;
    private String message;
    private String errorCode;
    private T data;
    private String uri;
    private LocalDateTime timeStamp;
}
