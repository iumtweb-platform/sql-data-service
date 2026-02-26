package com.sqldataservice.api.validation;

import java.util.List;

public class InvalidQueryException extends RuntimeException {

  private final List<QueryValidationError> errors;

  public InvalidQueryException(String message, List<QueryValidationError> errors) {
    super(message);
    this.errors = List.copyOf(errors);
  }

  public List<QueryValidationError> getErrors() {
    return errors;
  }
}
