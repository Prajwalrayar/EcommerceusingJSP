package com.crimsonlogic.ecommerce.exception;

/**
 * Thrown when the requested resource
 * cannot be found.
 */
public class ResourceNotFoundException extends RuntimeException {

    // Parameterized Constructor.

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
