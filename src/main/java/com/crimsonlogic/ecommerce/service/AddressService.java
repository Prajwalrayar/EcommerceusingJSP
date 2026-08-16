package com.crimsonlogic.ecommerce.service;

import com.crimsonlogic.ecommerce.model.Address;

import java.util.List;

public interface AddressService {

    // Inserts Address.
    void insertAddress(Address address);

    // Updates Address.
    void updateAddress(Address address);

    // Deletes Address.
    void deleteAddress(String addressId);
    // Finds Address by ID.
    Address findAddressById(String addressId);

    List<Address> findAllAddresses();
}
