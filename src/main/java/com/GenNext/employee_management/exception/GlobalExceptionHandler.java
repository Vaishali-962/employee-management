package com.GenNext.employee_management.exception;

import com.sun.net.httpserver.HttpServer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
//    For EmployeeException
    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<ApiErrorResponse<String>> handleEmployeeException(EmployeeException exception,
                                                                            HttpServletRequest request){
        // 1️⃣ Create the Response
        ApiErrorResponse<String> apiErrorResponse = ApiErrorResponse.<String>builder()
                .success(false)
                .message("Client Error")
                .errorCode(exception.getErrorCode())
                .data(exception.getErrorMessage())
                .uri(request.getRequestURI())
                .timeStamp(LocalDateTime.now())
                .build();

        // 2️⃣ Create Response Entity
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse<Object>> handleValidationException(
            MethodArgumentNotValidException exception, HttpServletRequest request){

        // 1️⃣ Extract the validation error
        Map<String, String> errorData = exception
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        fe->fe.getField(),
                        fe->fe.getDefaultMessage()
                        )
                );

        // 2️⃣ Create ApiErrorResponse
        ApiErrorResponse<Object> apiErrorResponse = ApiErrorResponse
                .<Object>builder()
                .success(false)
                .message("Client Error")
                .errorCode("VALIDATION_ERROR")
                .data(errorData)
                .uri(request.getRequestURI())
                .timeStamp(LocalDateTime.now())
                .build();

        // 3️⃣ Response Entity
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResponse);
    }
}
