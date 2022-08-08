package com.management.fresher.exception;

import com.management.fresher.response.ResultResponse;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.BindException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ResultResponse resultResponse = new ResultResponse(HttpStatus.BAD_REQUEST.value(), "JSON parse error");
        return new ResponseEntity<>(resultResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ResultResponse resultResponse = new ResultResponse(HttpStatus.BAD_REQUEST.value(), "Bad request");
        return new ResponseEntity<>(resultResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ResultResponse resultResponse = new ResultResponse(HttpStatus.BAD_REQUEST.value(), "Bad request");
        return new ResponseEntity<>(resultResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResultResponse> handleCustomException(CustomException ex) {
        ResultResponse resultResponse = new ResultResponse(ex.getStatus(), ex.getMessage());
        return new ResponseEntity<>(resultResponse, HttpStatus.valueOf(ex.getStatus()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResultResponse> handleUnwantedException(Exception e) {
        ResultResponse resultResponse = new ResultResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return new ResponseEntity<>(resultResponse, HttpStatus.BAD_REQUEST);
    }
}
