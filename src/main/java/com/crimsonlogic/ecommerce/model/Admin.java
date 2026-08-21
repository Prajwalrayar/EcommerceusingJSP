package com.crimsonlogic.ecommerce.model;

import com.crimsonlogic.ecommerce.enumeration.Role;
import com.crimsonlogic.ecommerce.model.abstraction.User;

/**
 * Represents an administrator user in the ecommerce application.
 *
 * Admin extends the common User abstraction and automatically assigns
 * the ADMIN role when an administrator object is created.
 */
public class Admin extends User {

    /**
     * Default constructor for creating an Admin object.
     *
     * The administrator role is automatically assigned as ADMIN
     * when the object is created.
     */
    public Admin() {
        setRole(Role.ADMIN);
    }


    /**
     * Creates an Admin object using the supplied user information.
     *
     * The common user information is initialized through the
     * parent User class constructor. The ADMIN role is then
     * assigned to the newly created administrator.
     *
     * @param userId Admin ID
     * @param userName Admin Name
     * @param userEmail Admin Email
     * @param userPhNo Admin Phone Number
     * @param userPassword Admin Password
     */
    public Admin(
            String userId,
            String userName,
            String userEmail,
            String userPhNo,
            String userPassword) {

        super(
                userId,
                userName,
                userEmail,
                userPhNo,
                userPassword
        );

        // Assign the ADMIN role to this user.
        setRole(Role.ADMIN);
    }

}