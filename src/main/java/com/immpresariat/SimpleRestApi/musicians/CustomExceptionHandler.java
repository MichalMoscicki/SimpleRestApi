package com.immpresariat.SimpleRestApi.musicians;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex){
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setErrorCode(400);
    errorResponse.setErrorMessage("Unproper entity");

    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


//    @ExceptionHandler(Exception.class)
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException ex,
//            HttpHeaders headers, HttpStatus status, WebRequest request) {
//        Map<String, Object> responseBody = new LinkedHashMap<>();
//        responseBody.put("timestamp", new Date());
//        responseBody.put("status", status.value());
//
//        List<FieldError> fieldErrors =  ex.getBindingResult().getFieldErrors();
//        List<String> errors = new ArrayList<>();
//
//        for(FieldError error: fieldErrors){
//        errors.add(error.getDefaultMessage());
//        }
//
//        responseBody.put("errors", errors);
//
//        return new ResponseEntity<>(responseBody, headers, status);
//    }


}
