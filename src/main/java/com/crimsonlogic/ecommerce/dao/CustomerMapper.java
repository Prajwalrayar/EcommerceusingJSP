package com.crimsonlogic.ecommerce.dao;

import com.crimsonlogic.ecommerce.model.Address;
import com.crimsonlogic.ecommerce.model.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerMapper {

    // ==========================================================
    // CUSTOMER CRUD
    // ==========================================================

    void insertCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void updatePassword(
            @Param("userId")
            String userId,

            @Param("userPassword")
            String userPassword);

    void deleteCustomer(
            @Param("customerId")
            String customerId);

    Customer findCustomerById(
            @Param("customerId")
            String customerId);

    Customer findCustomerByEmail(
            @Param("email")
            String email);

    Customer findCustomerByPhone(
            @Param("phone")
            String phone);

    List<Customer> findAllCustomers();


    // ==========================================================
    // CUSTOMER WALLET
    // ==========================================================

    void updateWalletBalance(
            @Param("customerId")
            String customerId,

            @Param("walletBalance")
            double walletBalance);


    // ==========================================================
    // CUSTOMER ↔ ADDRESS
    // ==========================================================

    /**
     * Assigns an existing address to Customer.
     */
    void assignAddressToCustomer(
            @Param("customerId")
            String customerId,

            @Param("addressId")
            String addressId);


    /**
     * Removes an address from Customer.
     */
    void removeAddressFromCustomer(
            @Param("customerId")
            String customerId,

            @Param("addressId")
            String addressId);


    /**
     * Finds all Customer addresses.
     */
    List<Address> findAddressesByCustomer(
            @Param("customerId")
            String customerId);


    /**
     * Removes all Customer addresses.
     */
    void removeAllAddressesFromCustomer(
            @Param("customerId")
            String customerId);
    
    
}