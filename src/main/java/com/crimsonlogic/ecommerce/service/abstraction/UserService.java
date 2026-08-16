package com.crimsonlogic.ecommerce.service.abstraction;

import com.crimsonlogic.ecommerce.exception.ValidationException;
import com.crimsonlogic.ecommerce.model.abstraction.User;
import com.crimsonlogic.ecommerce.util.PasswordUtil;
import com.crimsonlogic.ecommerce.util.ValidationUtil;

/**
 * Generic abstract service containing common
 * operations shared by all users.
 *
 * @param <T> User type
 */
public abstract class UserService<T extends User> {

    /**
     * Validates common profile information
     * shared by Customer and Seller.
     *
     * Common fields:
     * - Name
     * - Email
     * - Phone
     *
     * @param user User
     */
    protected void validateCommonProfile(T user) {

        if (user == null) {

            throw new ValidationException(
                    "User information is required.");
        }

        ValidationUtil.validateUserName(
                user.getUserName());

        ValidationUtil.validateEmail(
                user.getUserEmail());

        ValidationUtil.validatePhone(
                user.getUserPhNo());
    }


    /**
     * Generates a new encrypted password after
     * validating the current password,
     * new password and confirmation password.
     *
     * This method is shared by Customer and Seller.
     *
     * @param user User
     * @param currentPassword Current password
     * @param newPassword New password
     * @param confirmPassword Confirm password
     * @return Encrypted new password
     */
    protected String generateNewPassword(
            T user,
            String currentPassword,
            String newPassword,
            String confirmPassword) {

        if (user == null) {

            throw new ValidationException(
                    "User information is required.");
        }


        if (currentPassword == null ||
                currentPassword.trim().isEmpty()) {

            throw new ValidationException(
                    "Current password is required.");
        }


        if (newPassword == null ||
                newPassword.trim().isEmpty()) {

            throw new ValidationException(
                    "New password is required.");
        }


        if (confirmPassword == null ||
                confirmPassword.trim().isEmpty()) {

            throw new ValidationException(
                    "Confirm password is required.");
        }


        /*
         * Verify current password against
         * the BCrypt password stored in database.
         */
        if (!PasswordUtil.verifyPassword(
                currentPassword,
                user.getUserPassword())) {

            throw new ValidationException(
                    "Current password is incorrect.");
        }


        /*
         * Validate new password using the
         * application's common validation rules.
         */
        ValidationUtil.validatePassword(
                newPassword);


        /*
         * New password must not be the
         * same as the current password.
         */
        if (PasswordUtil.verifyPassword(
                newPassword,
                user.getUserPassword())) {

            throw new ValidationException(
                    "New password cannot be the same as current password.");
        }


        /*
         * Confirm password must match.
         */
        if (!newPassword.equals(confirmPassword)) {

            throw new ValidationException(
                    "Passwords do not match.");
        }


        /*
         * Encrypt only after all validations
         * are successful.
         */
        return PasswordUtil.encryptPassword(
                newPassword);
    }
}