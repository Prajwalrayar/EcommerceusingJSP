package com.crimsonlogic.ecommerce.dao;

import com.crimsonlogic.ecommerce.model.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressMapper {

    /**
     * Inserts Address.
     */
    void insertAddress(Address address);

    /**
     * Updates Address.
     */
    void updateAddress(Address address);

    /**
     * Deletes Address.
     */
    void deleteAddress(
            @Param("addressId")
            String addressId);

    /**
     * Finds Address by ID.
     */
    Address findAddressById(
            @Param("addressId")
            String addressId);

    /**
     * Returns all addresses.
     */
    List<Address> findAllAddresses();

    /**
     * Returns addresses assigned to a customer.
     */
    List<Address> findAddressesByCustomer(
            @Param("customerId")
            String customerId);

    /**
     * Assigns an address to a customer.
     */
    void assignAddressToCustomer(
            @Param("customerId")
            String customerId,
            @Param("addressId")
            String addressId);

    /**
     * Removes an address from a customer.
     */
    void removeAddressFromCustomer(
            @Param("customerId")
            String customerId,
            @Param("addressId")
            String addressId);
}