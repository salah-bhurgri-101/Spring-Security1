package com.spring.exception;

import com.spring.dto.ApiResponse;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobelExceptionHandler {

@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
    String message = ex.getMessage();
    ApiResponse apiResponse  = new ApiResponse(message , false);
    return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
}

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex){
        String message = ex.getMessage();
        ApiResponse apiResponse  = new ApiResponse(message , false);
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String , String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String , String> resp =new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            resp.put(fieldName, message);
        });

        return new ResponseEntity<Map<String,String>>(resp , HttpStatus.BAD_REQUEST);
    }

}
