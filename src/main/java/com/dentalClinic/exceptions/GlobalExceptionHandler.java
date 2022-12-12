package com.dentalClinic.exceptions;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = Logger.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFound(NotFoundException e, WebRequest request) {
        LOGGER.error(e.getMessage());
        return new ResponseEntity<>("ERROR: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ErrorRequestException.class)
    public ResponseEntity<?> errorRequest(ErrorRequestException e, WebRequest request) {
        LOGGER.error(e.getMessage());
        return new ResponseEntity<>("ERROR: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
