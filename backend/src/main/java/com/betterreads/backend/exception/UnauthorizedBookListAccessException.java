package com.betterreads.backend.exception;

public class UnauthorizedBookListAccessException extends RuntimeException {
  public UnauthorizedBookListAccessException(String message) {
    super(message);
  }
}
