package com.crimsonlogic.ecommerce.service.impl;

import com.crimsonlogic.ecommerce.dao.CustomerMapper;
import com.crimsonlogic.ecommerce.exception.ValidationException;
import com.crimsonlogic.ecommerce.model.Address;
import com.crimsonlogic.ecommerce.model.Customer;
import com.crimsonlogic.ecommerce.service.CustomerService;
import com.crimsonlogic.ecommerce.service.abstraction.UserService;

import java.util.List;

/**
 * Customer service implementation.
 *
 * Reuses common user functionality through
 * UserService<Customer>.
 */
public class CustomerServiceImpl
        extends UserService<Customer>
        implements CustomerService {

    private CustomerMapper customerMapper;


    /**
     * Sets CustomerMapper.
     *
     * @param customerMapper CustomerMapper
     */
    public void setCustomerMapper(
            CustomerMapper customerMapper) {

        this.customerMapper = customerMapper;
    }


    /**
     * Inserts Customer.
     *
     * @param customer Customer
     */
    @Override
    public void insertCustomer(
            Customer customer) {

        if (customer == null) {

            throw new ValidationException(
                    "Customer information is required.");
        }

        customerMapper.insertCustomer(
                customer);
    }


    /**
     * Updates Customer profile.
     *
     * Customer can edit:
     * - Name
     * - Email
     * - Phone
     *
     * @param customer Customer
     */
    @Override
    public void updateCustomer(
            Customer customer) {

        if (customer == null) {

            throw new ValidationException(
                    "Customer information is required.");
        }


        /*
         * Reuse common User validation.
         */
        validateCommonProfile(
                customer);


        customerMapper.updateCustomer(
                customer);
    }


    /**
     * Updates Customer password.
     *
     * @param userId User ID
     * @param userPassword Encrypted password
     */
    @Override
    public void updatePassword(
            String userId,
            String userPassword) {

        customerMapper.updatePassword(
                userId,
                userPassword);
    }


    /**
     * Deletes Customer.
     *
     * @param customerId Customer ID
     */
    @Override
    public void deleteCustomer(
            String customerId) {

        customerMapper.deleteCustomer(
                customerId);
    }


    /**
     * Finds Customer by ID.
     *
     * @param customerId Customer ID
     * @return Customer
     */
    @Override
    public Customer findCustomerById(
            String customerId) {

        return customerMapper.findCustomerById(
                customerId);
    }


    /**
     * Finds Customer by email.
     *
     * @param email Email
     * @return Customer
     */
    @Override
    public Customer findCustomerByEmail(
            String email) {

        return customerMapper.findCustomerByEmail(
                email);
    }


    /**
     * Finds Customer by phone.
     *
     * @param phone Phone number
     * @return Customer
     */
    @Override
    public Customer findCustomerByPhone(
            String phone) {

        return customerMapper.findCustomerByPhone(
                phone);
    }


    /**
     * Returns all Customers.
     *
     * @return Customer list
     */
    @Override
    public List<Customer> findAllCustomers() {

        return customerMapper.findAllCustomers();
    }


    /**
     * Updates Customer wallet balance.
     *
     * @param customerId Customer ID
     * @param walletBalance Wallet balance
     */
    @Override
    public void updateWalletBalance(
            String customerId,
            double walletBalance) {

        if (walletBalance < 0) {

            throw new ValidationException(
                    "Wallet balance cannot be negative.");
        }


        customerMapper.updateWalletBalance(
                customerId,
                walletBalance);
    }


    /**
     * Changes Customer password.
     *
     * Reuses password validation and
     * encryption from UserService.
     *
     * @param customerId Customer ID
     * @param currentPassword Current password
     * @param newPassword New password
     * @param confirmPassword Confirm password
     */
    @Override
    public void changePassword(
            String customerId,
            String currentPassword,
            String newPassword,
            String confirmPassword) {

        Customer customer =
                customerMapper.findCustomerById(
                        customerId);


        if (customer == null) {

            throw new ValidationException(
                    "Customer not found.");
        }


        String encryptedPassword =
                generateNewPassword(
                        customer,
                        currentPassword,
                        newPassword,
                        confirmPassword);


        customerMapper.updatePassword(
                customerId,
                encryptedPassword);
    }


    // =====================================================
    // CUSTOMER ↔ ADDRESS
    // =====================================================

    /**
     * Assigns an address to Customer.
     *
     * @param customerId Customer ID
     * @param addressId Address ID
     */
    public void assignAddress(
            String customerId,
            String addressId) {

        if (customerId == null ||
                customerId.trim().isEmpty()) {

            throw new ValidationException(
                    "Customer ID is required.");
        }


        if (addressId == null ||
                addressId.trim().isEmpty()) {

            throw new ValidationException(
                    "Address ID is required.");
        }


        customerMapper.assignAddressToCustomer(
                customerId,
                addressId);
    }


    /**
     * Removes an address from Customer.
     *
     * @param customerId Customer ID
     * @param addressId Address ID
     */
    public void removeAddress(
            String customerId,
            String addressId) {

        if (customerId == null ||
                customerId.trim().isEmpty()) {

            throw new ValidationException(
                    "Customer ID is required.");
        }


        if (addressId == null ||
                addressId.trim().isEmpty()) {

            throw new ValidationException(
                    "Address ID is required.");
        }


        customerMapper.removeAddressFromCustomer(
                customerId,
                addressId);
    }


    /**
     * Finds all addresses assigned
     * to a Customer.
     *
     * @param customerId Customer ID
     * @return Address list
     */
    public List<Address> findCustomerAddresses(
            String customerId) {

        return customerMapper.findAddressesByCustomer(
                customerId);
    }
}