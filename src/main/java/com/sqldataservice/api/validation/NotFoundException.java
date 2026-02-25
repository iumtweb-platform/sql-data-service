package com.sqldataservice.api.validation;

public class NotFoundException extends RuntimeException {
  public NotFoundException(String type, String value) {
    super("Element of type '" + type + "' with value '" + value + "' not found");
  }

}
