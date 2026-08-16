package com.crimsonlogic.ecommerce.model;

import com.crimsonlogic.ecommerce.enumeration.Role;
import com.crimsonlogic.ecommerce.model.abstraction.User;

import java.util.ArrayList;
import java.util.List;

public class Seller extends User {

    /* Shop Name */
    private String shopName;

    /*
     * Shop Address
     *
     * This is kept as a String because it is a separate
     * shop-address description/property in your existing model.
     *
     * The actual Customer/Seller <-> Address relationship
     * is handled by seller_address.
     */
    private String shopAddress;

    /*
     * Seller can have multiple addresses.
     *
     * Database relationship:
     *
     * sellers
     *     |
     * seller_address
     *     |
     * address
     */
    private List<Address> addresses = new ArrayList<>();


    /**
     * Default Constructor.
     * Required by MyBatis.
     */
    public Seller() {
        setRole(Role.SELLER);
    }


    /**
     * Parameterized Constructor.
     */
    public Seller(
            String userId,
            String userName,
            String userEmail,
            String userPhNo,
            String userPassword,
            String shopName,
            String shopAddress) {

        super(
                userId,
                userName,
                userEmail,
                userPhNo,
                userPassword
        );

        setRole(Role.SELLER);

        this.shopName = shopName;
        this.shopAddress = shopAddress;
    }


    // ==========================================================
    // Shop Name
    // ==========================================================

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }


    // ==========================================================
    // Shop Address
    // ==========================================================

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }


    // ==========================================================
    // Seller Addresses
    // ==========================================================

    /**
     * Returns all addresses associated with the seller.
     *
     * @return Seller addresses
     */
    public List<Address> getAddresses() {
        return addresses;
    }


    /**
     * Sets all addresses associated with the seller.
     *
     * @param addresses Seller addresses
     */
    public void setAddresses(List<Address> addresses) {

        if (addresses == null) {
            this.addresses = new ArrayList<>();
        } else {
            this.addresses = addresses;
        }
    }
}