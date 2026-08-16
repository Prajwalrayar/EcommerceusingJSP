package com.crimsonlogic.ecommerce.service;

import com.crimsonlogic.ecommerce.model.Admin;
import com.crimsonlogic.ecommerce.model.Customer;
import com.crimsonlogic.ecommerce.model.Seller;

import java.util.List;

public interface AdminService {

    // =====================================================
    // ADMIN PROFILE
    // =====================================================

    /**
     * Returns Admin profile.
     *
     * @param adminId Admin ID
     * @return Admin
     */
    Admin getAdminProfile(String adminId);


    /**
     * Updates ONLY Admin phone number.
     *
     * Admin cannot update:
     * - Name
     * - Email
     * - Password
     *
     * @param admin Admin
     */
    void updateAdminPhone(Admin admin);
    
    void changePassword(
            String adminId,
            String currentPassword,
            String newPassword,
            String confirmPassword);


    // =====================================================
    // CUSTOMER MANAGEMENT
    // =====================================================

    /**
     * Returns all customers.
     *
     * @return Customer list
     */
    List<Customer> getAllCustomers();


    /**
     * Deletes a customer.
     *
     * @param customerId Customer ID
     */
    void deleteCustomer(String customerId);


    // =====================================================
    // SELLER MANAGEMENT
    // =====================================================

    /**
     * Returns all sellers.
     *
     * @return Seller list
     */
    List<Seller> getAllSellers();


    /**
     * Deletes a seller.
     *
     * @param sellerId Seller ID
     */
    void deleteSeller(String sellerId);
}