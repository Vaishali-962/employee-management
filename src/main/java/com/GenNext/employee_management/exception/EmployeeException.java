package com.GenNext.employee_management.exception;

import lombok.Getter;

@Getter
public class EmployeeException extends RuntimeException{
    private String errorCode;
    private String errorMessage;

    public EmployeeException(String errorCode, String errorMessage){
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public EmployeeException(String errorMessage){
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
