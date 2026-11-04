package com.example.session08.exception;

import com.example.session08.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String,String>>>
    handleValidationException(MethodArgumentNotValidException ex){
        Map<String,String> errors = new LinkedHashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> {
                    errors.putIfAbsent(
                            error.getField(),
                            error.getDefaultMessage()
                    );
                });

        ApiResponse<Map<String,String>> response = new ApiResponse<>(
                "FAIL",
                "Dữ liệu không hợp lệ",
                errors
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiResponse<Map<String,String>>>
    handleNotFoundException(NoSuchElementException ex){
        Map<String,String> errors = new LinkedHashMap<>();

        errors.put("departmentId", ex.getMessage());

        ApiResponse<Map<String,String>> response = new ApiResponse<>(
                "FAIL",
                "Không tìm thấy phòng ban",
                errors
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Map<String,String>>>
    handleNotFoundException(ResourceNotFoundException ex){
        Map<String,String> errors = new LinkedHashMap<>();
        errors.put("message", ex.getMessage());

        ApiResponse<Map<String,String>> response = new ApiResponse<>(
                "FAIL",
                "Dữ liệu không hợp lệ",
                errors
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiResponse<Map<String,String>>>
    handleDuplicateResource(DuplicateResourceException ex){
        Map<String,String> errors = new LinkedHashMap<>();
        errors.put("message", ex.getMessage());

        ApiResponse<Map<String,String>> response = new ApiResponse<>(
                "FAIL",
                "Dữ liệu trùng lặp",
                errors
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
