package com.crimsonlogic.ecommerce.model;

import com.crimsonlogic.ecommerce.enumeration.Role;
import com.crimsonlogic.ecommerce.model.abstraction.User;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

    private double walletBalance;

    private List<Address> addresses = new ArrayList<>();


    /**
     * Default Constructor.
     */
    public Customer() {
        setRole(Role.CUSTOMER);
    }


    /**
     * Parameterized Constructor.
     */
    public Customer(
            String userId,
            String userName,
            String userEmail,
            String userPhNo,
            String userPassword,
            List<Address> addresses,
            double walletBalance) {

        super(
                userId,
                userName,
                userEmail,
                userPhNo,
                userPassword
        );

        setRole(Role.CUSTOMER);

        this.addresses = addresses;
        this.walletBalance = walletBalance;
    }


    // ==========================================================
    // Wallet
    // ==========================================================

    public double getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }


    // ==========================================================
    // Addresses
    // ==========================================================

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }


}