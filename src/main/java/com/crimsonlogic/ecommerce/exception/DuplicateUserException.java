package com.crimsonlogic.ecommerce.exception;

/**
 * Thrown when a user tries to register
 * with an email or phone number that
 * already exists.
 */
public class DuplicateUserException extends RuntimeException {

    public DuplicateUserException(String message) {
        super(message);
    }

}
