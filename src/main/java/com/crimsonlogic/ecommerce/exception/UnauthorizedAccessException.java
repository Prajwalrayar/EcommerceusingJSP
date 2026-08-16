package com.crimsonlogic.ecommerce.exception;

/**
 * Thrown when a user attempts to
 * access an unauthorized operation.
 */
public class UnauthorizedAccessException extends RuntimeException {

    public UnauthorizedAccessException(String message) {
        super(message);
    }

}