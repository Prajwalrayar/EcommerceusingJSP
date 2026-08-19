package com.crimsonlogic.ecommerce.service;

import com.crimsonlogic.ecommerce.model.Address;
import com.crimsonlogic.ecommerce.model.Customer;

import java.util.List;

public interface CustomerService {

    void insertCustomer(
            Customer customer);

    void updateCustomer(
            Customer customer);

    void updatePassword(
            String userId,
            String userPassword);

    void deleteCustomer(
            String customerId);

    Customer findCustomerById(
            String customerId);

    Customer findCustomerByEmail(
            String email);

    Customer findCustomerByPhone(
            String phone);

    List<Customer> findAllCustomers();


    // =====================================================
    // WALLET
    // =====================================================

    void updateWalletBalance(
            String customerId,
            double walletBalance);


    // =====================================================
    // PASSWORD
    // =====================================================

    void changePassword(
            String customerId,
            String currentPassword,
            String newPassword,
            String confirmPassword);


    // =====================================================
    // CUSTOMER ↔ ADDRESS
    // =====================================================

    void assignAddress(
            String customerId,
            String addressId);
    
    void rechargeWallet(
            String customerId,
            double amount,
            String paymentMethod,
            String upiId);

    void removeAddress(
            String customerId,
            String addressId);

    List<Address> findCustomerAddresses(
            String customerId);
}