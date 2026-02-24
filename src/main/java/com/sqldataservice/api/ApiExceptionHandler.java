package com.sqldataservice.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sqldataservice.api.validation.InvalidQueryException;
import com.sqldataservice.api.validation.QueryValidationError;

@RestControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(InvalidQueryException.class)
  public ResponseEntity<ErrorResponse> handleInvalidQuery(InvalidQueryException exception) {
    var response = new ErrorResponse(exception.getMessage(), exception.getErrors());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  public record ErrorResponse(String message, List<QueryValidationError> errors) {
  }
}
