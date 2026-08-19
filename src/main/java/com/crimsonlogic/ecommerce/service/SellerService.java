package com.crimsonlogic.ecommerce.service;

import com.crimsonlogic.ecommerce.model.Address;
import com.crimsonlogic.ecommerce.model.Customer;
import com.crimsonlogic.ecommerce.model.Seller;

import java.util.List;

public interface SellerService {

    void insertSeller(Seller seller);

    void updateSeller(Seller seller);

    void updatePassword(
            String userId,
            String userPassword);

    void deleteSeller(
            String sellerId);

    Seller findSellerById(
            String sellerId);

    Seller findSellerByEmail(
            String email);

    Seller findSellerByPhone(
            String phone);

    List<Seller> findAllSellers();


    // =====================================================
    // PASSWORD
    // =====================================================

    void changePassword(
            String sellerId,
            String currentPassword,
            String newPassword,
            String confirmPassword);


    // =====================================================
    // SELLER ↔ ADDRESS
    // =====================================================

    void assignAddressToSeller(
            String sellerId,
            String addressId);

    void removeAddressFromSeller(
            String sellerId,
            String addressId);

    List<Address> findAddressesBySeller(
            String sellerId);
    
    List<Customer> findCustomersBySeller(
            String sellerId);
}