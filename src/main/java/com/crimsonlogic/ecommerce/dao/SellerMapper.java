package com.crimsonlogic.ecommerce.dao;

import com.crimsonlogic.ecommerce.model.Address;
import com.crimsonlogic.ecommerce.model.Customer;
import com.crimsonlogic.ecommerce.model.Seller;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SellerMapper {

    // ==========================================================
    // SELLER CRUD
    // ==========================================================

    void insertSeller(Seller seller);

    void updateSeller(Seller seller);

    void updatePassword(
            @Param("userId")
            String userId,

            @Param("userPassword")
            String userPassword);

    void deleteSeller(
            @Param("sellerId")
            String sellerId);

    Seller findSellerById(
            @Param("sellerId")
            String sellerId);

    Seller findSellerByEmail(
            @Param("email")
            String email);

    Seller findSellerByPhone(
            @Param("phone")
            String phone);

    List<Seller> findAllSellers();


    // ==========================================================
    // SELLER ↔ ADDRESS
    // ==========================================================

    /**
     * Assigns an existing address to Seller.
     */
    void assignAddressToSeller(
            @Param("sellerId")
            String sellerId,

            @Param("addressId")
            String addressId);


    /**
     * Removes an address from Seller.
     */
    void removeAddressFromSeller(
            @Param("sellerId")
            String sellerId,

            @Param("addressId")
            String addressId);


    /**
     * Finds all Seller addresses.
     */
    List<Address> findAddressesBySeller(
            @Param("sellerId")
            String sellerId);


    /**
     * Removes all Seller addresses.
     */
    void removeAllAddressesFromSeller(
            @Param("sellerId")
            String sellerId);
    
    List<Customer> findCustomersBySeller(
            @Param("sellerId")
            String sellerId);
    
    Double findAverageRatingBySeller(
            @Param("sellerId")
            String sellerId);
}