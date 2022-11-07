package com.swith.backend.global.exception.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.xml.bind.ValidationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({SwithException.class})
    protected ResponseEntity<ErrorResponse> handleSwithException(SwithException ex) {
        return new ResponseEntity<ErrorResponse>(
                new ErrorResponse(ex.getErrorCode().getStatus(), ex.getErrorCode().getMessage()),
                HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    @ExceptionHandler({NullPointerException.class})
    protected ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException ex) {
        return new ResponseEntity<>(new ErrorResponse(400, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ValidationException.class})
    protected ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex) {
        return new ResponseEntity<>(new ErrorResponse(400, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
