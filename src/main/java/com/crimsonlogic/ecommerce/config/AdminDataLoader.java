package com.crimsonlogic.ecommerce.config;

import com.crimsonlogic.ecommerce.dao.AdminMapper;
import com.crimsonlogic.ecommerce.model.Admin;
import com.crimsonlogic.ecommerce.util.IdGenerator;
import com.crimsonlogic.ecommerce.util.PasswordUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Loads default Admin accounts when the application starts.
 */
@Component
public class AdminDataLoader implements InitializingBean {

    private final AdminMapper adminMapper;


    /**
     * Constructor Injection.
     *
     * @param adminMapper AdminMapper
     */
    public AdminDataLoader(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }


    /**
     * Executes after Spring has created this bean
     * and injected AdminMapper.
     */
    @Override
    public void afterPropertiesSet() {

    	 System.out.println(
    	            "========== ADMIN DATA LOADER STARTED =========="
    	    );
        loadAdmins();
        
        System.out.println(
                "========== ADMIN DATA LOADER FINISHED =========="
        );
    }


    /**
     * Creates default administrators if no admins exist.
     *
     * Passwords are encrypted using BCrypt
     * before being stored in the database.
     */
    public void loadAdmins() {

        List<Admin> admins =
                adminMapper.findAllAdmins();


        // =====================================================
        // PREVENT DUPLICATE ADMIN CREATION
        // =====================================================

        if (admins != null && !admins.isEmpty()) {

            System.out.println(
                    "Admins already exist. "
                    + "Skipping default admin creation."
            );

            return;
        }


        // =====================================================
        // ADMIN 1
        // =====================================================

        Admin admin1 = new Admin(
                IdGenerator.generateId("ADM"),
                "ADMINISTRATOR",
                "admin@ecommerce.com",
                "9876543210",
                PasswordUtil.encryptPassword("Admin@123")
        );


        // =====================================================
        // ADMIN 2
        // =====================================================

        Admin admin2 = new Admin(
                IdGenerator.generateId("ADM"),
                "Naveen",
                "naveen@ecommerce.com",
                "9876543211",
                PasswordUtil.encryptPassword("Naveen@123")
        );


        // =====================================================
        // ADMIN 3
        // =====================================================

        Admin admin3 = new Admin(
                IdGenerator.generateId("ADM"),
                "Rahul",
                "rahul@ecommerce.com",
                "9876543212",
                PasswordUtil.encryptPassword("Rahul@123")
        );


        // =====================================================
        // INSERT ADMINS
        // =====================================================

        adminMapper.insertAdmin(admin1);

        adminMapper.insertAdmin(admin2);

        adminMapper.insertAdmin(admin3);


        System.out.println(
                "Default Admins Created Successfully."
        );
    }
}