package com.crimsonlogic.ecommerce.service.impl;

import com.crimsonlogic.ecommerce.dao.AdminMapper;
import com.crimsonlogic.ecommerce.dao.CustomerMapper;
import com.crimsonlogic.ecommerce.dao.SellerMapper;
import com.crimsonlogic.ecommerce.exception.DuplicateUserException;
import com.crimsonlogic.ecommerce.exception.InvalidCredentialsException;
import com.crimsonlogic.ecommerce.exception.ValidationException;
import com.crimsonlogic.ecommerce.model.Admin;
import com.crimsonlogic.ecommerce.model.Customer;
import com.crimsonlogic.ecommerce.model.Seller;
import com.crimsonlogic.ecommerce.service.AuthenticationService;
import com.crimsonlogic.ecommerce.util.IdGenerator;
import com.crimsonlogic.ecommerce.util.PasswordUtil;
import com.crimsonlogic.ecommerce.util.ValidationUtil;

public class AuthenticationServiceImpl
        implements AuthenticationService {

    private AdminMapper adminMapper;

    private CustomerMapper customerMapper;

    private SellerMapper sellerMapper;


    // ==========================================================
    // SETTERS
    // ==========================================================

    public void setAdminMapper(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    public void setCustomerMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    public void setSellerMapper(SellerMapper sellerMapper) {
        this.sellerMapper = sellerMapper;
    }


    // ==========================================================
    // CUSTOMER LOGIN
    // ==========================================================

    @Override
    public Customer loginCustomer(
            String email,
            String password) {

        if (email == null ||
                email.trim().isEmpty() ||
                password == null ||
                password.trim().isEmpty()) {

            throw new InvalidCredentialsException(
                    "Email or Password is required.");
        }

        Customer customer =
                customerMapper.findCustomerByEmail(
                        email.trim());

        if (customer == null ||
                !PasswordUtil.verifyPassword(
                        password,
                        customer.getUserPassword())) {

            throw new InvalidCredentialsException(
                    "Invalid Email or Password.");
        }

        return customer;
    }


    // ==========================================================
    // SELLER LOGIN
    // ==========================================================

    @Override
    public Seller loginSeller(
            String email,
            String password) {

        if (email == null ||
                email.trim().isEmpty() ||
                password == null ||
                password.trim().isEmpty()) {

            throw new InvalidCredentialsException(
                    "Email or Password is required.");
        }

        Seller seller =
                sellerMapper.findSellerByEmail(
                        email.trim());

        if (seller == null ||
                !PasswordUtil.verifyPassword(
                        password,
                        seller.getUserPassword())) {

            throw new InvalidCredentialsException(
                    "Invalid Email or Password.");
        }

        return seller;
    }


    // ==========================================================
    // ADMIN LOGIN
    // ==========================================================

    @Override
    public Admin loginAdmin(
            String email,
            String password) {

        if (email == null ||
                email.trim().isEmpty() ||
                password == null ||
                password.trim().isEmpty()) {

            throw new InvalidCredentialsException(
                    "Email or Password is required.");
        }

        Admin admin =
                adminMapper.findAdminByEmail(
                        email.trim());

        if (admin == null ||
                !PasswordUtil.verifyPassword(
                        password,
                        admin.getUserPassword())) {

            throw new InvalidCredentialsException(
                    "Invalid Email or Password.");
        }

        return admin;
    }


    // ==========================================================
    // CUSTOMER REGISTRATION
    // ==========================================================

    @Override
    public void registerCustomer(
            Customer customer) {

        if (customer == null) {

            throw new ValidationException(
                    "Customer information is required.");
        }

        // Validate customer details
        validateCommonDetails(
                customer.getUserName(),
                customer.getUserEmail(),
                customer.getUserPhNo(),
                customer.getUserPassword());

        // Normalize non-password values
        customer.setUserName(
                customer.getUserName().trim());

        customer.setUserEmail(
                customer.getUserEmail().trim());

        customer.setUserPhNo(
                customer.getUserPhNo().trim());

        // Check duplicate email
        checkDuplicateEmail(
                customer.getUserEmail());

        // Check duplicate phone
        checkDuplicatePhone(
                customer.getUserPhNo());

        // Generate customer ID
        customer.setUserId(
                IdGenerator.generateId("CUS"));

        // Encrypt password before storing
        customer.setUserPassword(
                PasswordUtil.encryptPassword(
                        customer.getUserPassword()));

        // Insert customer
        customerMapper.insertCustomer(customer);
    }


    // ==========================================================
    // SELLER REGISTRATION
    // ==========================================================

    @Override
    public void registerSeller(
            Seller seller) {

        if (seller == null) {

            throw new ValidationException(
                    "Seller information is required.");
        }

        // Validate common details
        validateCommonDetails(
                seller.getUserName(),
                seller.getUserEmail(),
                seller.getUserPhNo(),
                seller.getUserPassword());

        // Validate shop details
        if (seller.getShopName() == null ||
                seller.getShopName().trim().isEmpty()) {

            throw new ValidationException(
                    "Shop name is required.");
        }

        if (seller.getShopAddress() == null ||
                seller.getShopAddress().trim().isEmpty()) {

            throw new ValidationException(
                    "Shop address is required.");
        }

        // Normalize values
        seller.setUserName(
                seller.getUserName().trim());

        seller.setUserEmail(
                seller.getUserEmail().trim());

        seller.setUserPhNo(
                seller.getUserPhNo().trim());

        seller.setShopName(
                seller.getShopName().trim());

        seller.setShopAddress(
                seller.getShopAddress().trim());

        // Check duplicate email
        checkDuplicateEmail(
                seller.getUserEmail());

        // Check duplicate phone
        checkDuplicatePhone(
                seller.getUserPhNo());

        // Generate seller ID
        seller.setUserId(
                IdGenerator.generateId("SEL"));

        // Encrypt password
        seller.setUserPassword(
                PasswordUtil.encryptPassword(
                        seller.getUserPassword()));

        // Insert seller
        sellerMapper.insertSeller(seller);
    }


    // ==========================================================
    // COMMON VALIDATION
    // ==========================================================

    private void validateCommonDetails(
            String name,
            String email,
            String phone,
            String password) {

        ValidationUtil.validateUserName(name);

        ValidationUtil.validateEmail(email);

        ValidationUtil.validatePhone(phone);

        ValidationUtil.validatePassword(password);
    }


    // ==========================================================
    // DUPLICATE EMAIL
    // ==========================================================

    private void checkDuplicateEmail(
            String email) {

        if (adminMapper.findAdminByEmail(email) != null) {

            throw new DuplicateUserException(
                    "Email is already registered.");
        }

        if (sellerMapper.findSellerByEmail(email) != null) {

            throw new DuplicateUserException(
                    "Email is already registered.");
        }

        if (customerMapper.findCustomerByEmail(email) != null) {

            throw new DuplicateUserException(
                    "Email is already registered.");
        }
    }


    // ==========================================================
    // DUPLICATE PHONE
    // ==========================================================

    private void checkDuplicatePhone(
            String phone) {

        if (adminMapper.findAdminByPhone(phone) != null) {

            throw new DuplicateUserException(
                    "Phone number is already registered.");
        }

        if (sellerMapper.findSellerByPhone(phone) != null) {

            throw new DuplicateUserException(
                    "Phone number is already registered.");
        }

        if (customerMapper.findCustomerByPhone(phone) != null) {

            throw new DuplicateUserException(
                    "Phone number is already registered.");
        }
    }
}