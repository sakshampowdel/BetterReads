package com.betterreads.backend.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(BookNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleBookNotFound(BookNotFoundException ex, HttpServletRequest request) {
    ErrorResponse er = new ErrorResponse(LocalDateTime.now(), 404, HttpStatus.NOT_FOUND.getReasonPhrase(),
        ex.getMessage(), request.getRequestURI());

    return new ResponseEntity<ErrorResponse>(er, HttpStatus.NOT_FOUND);
  }
}
