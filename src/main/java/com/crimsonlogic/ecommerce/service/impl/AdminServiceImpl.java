package com.crimsonlogic.ecommerce.service.impl;

import com.crimsonlogic.ecommerce.dao.AdminMapper;
import com.crimsonlogic.ecommerce.exception.ValidationException;
import com.crimsonlogic.ecommerce.model.Admin;
import com.crimsonlogic.ecommerce.model.Customer;
import com.crimsonlogic.ecommerce.model.Seller;
import com.crimsonlogic.ecommerce.service.AdminService;
import com.crimsonlogic.ecommerce.util.PasswordUtil;
import com.crimsonlogic.ecommerce.util.ValidationUtil;

import java.util.List;

public class AdminServiceImpl implements AdminService {

    private AdminMapper adminMapper;


    /**
     * Sets AdminMapper.
     *
     * @param adminMapper AdminMapper
     */
    public void setAdminMapper(
            AdminMapper adminMapper) {

        this.adminMapper = adminMapper;
    }


    // =====================================================
    // ADMIN PROFILE
    // =====================================================

    /**
     * Finds Admin profile.
     *
     * @param adminId Admin ID
     * @return Admin
     */
    @Override
    public Admin getAdminProfile(
            String adminId) {

        Admin admin =
                adminMapper.findAdminById(adminId);

        if (admin == null) {

            throw new ValidationException(
                    "Admin not found.");
        }

        return admin;
    }


    /**
     * Updates ONLY Admin phone number.
     *
     * Admin cannot change:
     * - Name
     * - Email
     * - Password
     *
     * @param admin Admin
     */
    @Override
    public void updateAdminPhone(Admin admin) {

        if (admin == null) {

            throw new ValidationException(
                    "Admin information is required.");
        }


        if (admin.getUserId() == null ||
                admin.getUserId().trim().isEmpty()) {

            throw new ValidationException(
                    "Admin ID is required.");
        }


        if (admin.getUserPhNo() == null ||
                admin.getUserPhNo().trim().isEmpty()) {

            throw new ValidationException(
                    "Phone number is required.");
        }


        ValidationUtil.validatePhone(
                admin.getUserPhNo());


        /*
         * Fetch the existing admin from DB.
         */
        Admin existingAdmin =
                adminMapper.findAdminById(
                        admin.getUserId());


        if (existingAdmin == null) {

            throw new ValidationException(
                    "Admin not found.");
        }


        /*
         * Do not update if phone number
         * is exactly the same.
         */
        if (existingAdmin.getUserPhNo()
                .equals(admin.getUserPhNo())) {

            throw new ValidationException(
                    "Please enter a new phone number.");
        }


        /*
         * Only phone number is updated.
         *
         * Name and email from the browser
         * are completely ignored.
         */
        adminMapper.updateAdminPhone(admin);
    }
    
    
    @Override
    public void changePassword(
            String adminId,
            String currentPassword,
            String newPassword,
            String confirmPassword) {

        // Find admin
        Admin admin =
                adminMapper.findAdminById(adminId);

        if (admin == null) {

            throw new ValidationException(
                    "Admin account not found.");
        }


        // Current password required
        if (currentPassword == null ||
                currentPassword.trim().isEmpty()) {

            throw new ValidationException(
                    "Please enter your current password.");
        }


        // Verify current password
        if (!PasswordUtil.verifyPassword(
                currentPassword,
                admin.getUserPassword())) {

            throw new ValidationException(
                    "Current password is incorrect.");
        }


        // Validate new password
        ValidationUtil.validatePassword(
                newPassword);


        // Confirm password
        if (!newPassword.equals(confirmPassword)) {

            throw new ValidationException(
                    "New password and confirm password do not match.");
        }


        // Prevent using same password
        if (PasswordUtil.verifyPassword(
                newPassword,
                admin.getUserPassword())) {

            throw new ValidationException(
                    "New password must be different from your current password.");
        }


        // BCrypt new password
        String encryptedPassword =
                PasswordUtil.encryptPassword(
                        newPassword);


        // Update database
        adminMapper.updateAdminPassword(
                adminId,
                encryptedPassword);
    }


    // =====================================================
    // CUSTOMER MANAGEMENT
    // =====================================================

    /**
     * Returns all customers.
     *
     * @return Customer list
     */
    @Override
    public List<Customer> getAllCustomers() {

        return adminMapper.findAllCustomers();
    }


    /**
     * Deletes Customer.
     *
     * @param customerId Customer ID
     */
    @Override
    public void deleteCustomer(
            String customerId) {

        if (customerId == null ||
                customerId.trim().isEmpty()) {

            throw new ValidationException(
                    "Customer ID is required.");
        }

        adminMapper.deleteCustomer(
                customerId);
    }


    // =====================================================
    // SELLER MANAGEMENT
    // =====================================================

    /**
     * Returns all sellers.
     *
     * @return Seller list
     */
    @Override
    public List<Seller> getAllSellers() {

        return adminMapper.findAllSellers();
    }


    /**
     * Deletes Seller.
     *
     * @param sellerId Seller ID
     */
    @Override
    public void deleteSeller(
            String sellerId) {

        if (sellerId == null ||
                sellerId.trim().isEmpty()) {

            throw new ValidationException(
                    "Seller ID is required.");
        }

        adminMapper.deleteSeller(
                sellerId);
    }
}