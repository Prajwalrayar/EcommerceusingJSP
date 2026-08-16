package com.crimsonlogic.ecommerce.service.impl;

import com.crimsonlogic.ecommerce.dao.AddressMapper;
import com.crimsonlogic.ecommerce.dao.AdminMapper;
import com.crimsonlogic.ecommerce.model.Address;
import com.crimsonlogic.ecommerce.service.AddressService;

import java.util.List;

public class AddressServiceImpl implements AddressService {

    private AddressMapper addressMapper;

    public void setAddressMapper(AddressMapper addressMapper) {

        this.addressMapper = addressMapper;
    }

    @Override
    public void insertAddress(Address address) {
        addressMapper.insertAddress(address);
    }

    @Override
    public void updateAddress(Address address) {
        addressMapper.updateAddress(address);
    }

    @Override
    public void deleteAddress(String addressId) {
        addressMapper.deleteAddress(addressId);
    }

    @Override
    public Address findAddressById(String addressId) {

        return addressMapper.findAddressById(addressId);
    }

    @Override
    public List<Address> findAllAddresses() {
        return  addressMapper.findAllAddresses();
    }
}
