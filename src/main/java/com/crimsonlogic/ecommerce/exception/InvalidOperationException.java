package com.crimsonlogic.ecommerce.exception;

/**
 * Thrown when an invalid business
 * operation is attempted.
 */
public class InvalidOperationException extends RuntimeException {

    // Parameterized Constructor.
    public InvalidOperationException(String message) {
        super(message);
    }

}
