package com.sqldataservice.api.validation;

public record QueryValidationError(String field, String reason) {
}
