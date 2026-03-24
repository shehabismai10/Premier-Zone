package com.pl.premier_zone.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //this watch every error in our project
public class GlobalExceptionHandler {

    // catch the validation errors (400 bad request)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    //catch any other error than 400 (500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", "An unexpected error occurred: " + ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // 1. حدد إن الميثود دي لـ PlayerNotFoundException بس
@ExceptionHandler(PlayerNotFoundException.class)
public ResponseEntity<Map<String, String>> handlePlayerNotFound(PlayerNotFoundException ex) {
    Map<String, String> errorResponse = new HashMap<>();
    
    // 2. رجع الرسالة زي ما هي بدون إضافات "Unexpected"
    errorResponse.put("message", ex.getMessage());
    
    // 3. رجع 404 لأن اللاعب مش موجود
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
}

}
