package com.sqldataservice.api.validation;

public class NotFoundException extends RuntimeException {
  public NotFoundException(String type, String id) {
    super("Element of type " + type + " with id " + id + " not found");
  }

}
