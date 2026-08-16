package com.crimsonlogic.ecommerce.exception;

/**
 * Thrown when user input validation fails.
 *
 * Examples:
 * - Invalid Email
 * - Invalid Password
 * - Invalid Phone Number
 * - Invalid Name
 */
public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }

}
