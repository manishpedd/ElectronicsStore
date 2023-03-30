package com.electronics.store.exceptions;

import com.electronics.store.payloads.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandling(ResourceNotFoundException ex) {

        log.info("Exception handler invoked");
        String message = ex.getMessage();
        ApiResponse apimessage = new ApiResponse();
        apimessage.setSuccess(false);
        apimessage.setMessage(ex.getMessage());
        apimessage.setDate(new Date());

        return new ResponseEntity<ApiResponse>(apimessage, HttpStatus.BAD_REQUEST);

    }

    //methodArgumentNotValidException

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handlemethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        Map<String, Object> response = new HashMap<>();
        allErrors.stream().forEach(objectError -> {
         String message =   objectError.getDefaultMessage();
         String field = ((FieldError) objectError).getField();
         response.put(field,message);
        });

        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BadApiResponseException.class)
    public ResponseEntity<ApiResponse> BadApiResponseExceptionHandling(BadApiResponseException ex) {

        log.info("Bad api request");
        String message = ex.getMessage();
        ApiResponse apimessage = new ApiResponse();
        apimessage.setSuccess(false);
        apimessage.setMessage(ex.getMessage());
        apimessage.setDate(new Date());

        return new ResponseEntity<ApiResponse>(apimessage, HttpStatus.BAD_REQUEST);

    }


    }


