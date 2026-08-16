package com.crimsonlogic.ecommerce.model;

import com.crimsonlogic.ecommerce.enumeration.Role;
import com.crimsonlogic.ecommerce.model.abstraction.User;

public class Admin extends User {

    /**
     * Default Constructor.
     */
    public Admin() {
        setRole(Role.ADMIN);
    }


    /**
     * Parameterized Constructor.
     *
     * @param userId       Admin ID
     * @param userName     Admin Name
     * @param userEmail    Admin Email
     * @param userPhNo    Admin Phone Number
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

        setRole(Role.ADMIN);
    }

}