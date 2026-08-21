package com.crimsonlogic.ecommerce.model;

import com.crimsonlogic.ecommerce.enumeration.Role;
import com.crimsonlogic.ecommerce.model.abstraction.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer in the ecommerce application.
 *
 * Customer extends the common User abstraction and contains
 * customer-specific information such as wallet balance and
 * the list of addresses associated with the customer.
 *
 * The CUSTOMER role is automatically assigned when a Customer
 * object is created.
 */
public class Customer extends User {

    /**
     * Current wallet balance available to the customer.
     *
     * This value represents the amount of money maintained
     * in the customer's ecommerce wallet.
     */
    private double walletBalance;

    /**
     * List of addresses associated with the customer.
     *
     * The list is initialized as an empty ArrayList so that
     * a newly created customer can safely store addresses.
     */
    private List<Address> addresses = new ArrayList<>();


    /**
     * Default constructor.
     *
     * Creates a Customer object and automatically assigns
     * the CUSTOMER role to the user.
     */
    public Customer() {
        setRole(Role.CUSTOMER);
    }


    /**
     * Creates a Customer object using the supplied customer information.
     *
     * The common user information is initialized through the parent
     * User class constructor. The customer role is then assigned,
     * followed by the customer's addresses and wallet balance.
     *
     * @param userId customer ID
     * @param userName customer name
     * @param userEmail customer email address
     * @param userPhNo customer phone number
     * @param userPassword customer password
     * @param addresses list of addresses associated with the customer
     * @param walletBalance customer's wallet balance
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

        // Assign the CUSTOMER role to this user.
        setRole(Role.CUSTOMER);

        this.addresses = addresses;
        this.walletBalance = walletBalance;
    }


    // ==========================================================
    // Wallet
    // ==========================================================

    /**
     * Returns the current wallet balance of the customer.
     *
     * @return customer's wallet balance
     */
    public double getWalletBalance() {
        return walletBalance;
    }

    /**
     * Updates the customer's wallet balance.
     *
     * @param walletBalance new wallet balance
     */
    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }


    // ==========================================================
    // Addresses
    // ==========================================================

    /**
     * Returns the list of addresses associated with the customer.
     *
     * @return customer's address list
     */
    public List<Address> getAddresses() {
        return addresses;
    }

    /**
     * Updates the list of addresses associated with the customer.
     *
     * @param addresses new list of customer addresses
     */
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }


}