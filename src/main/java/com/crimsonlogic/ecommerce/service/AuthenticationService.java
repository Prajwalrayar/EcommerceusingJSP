package com.crimsonlogic.ecommerce.service;

import com.crimsonlogic.ecommerce.model.Admin;
import com.crimsonlogic.ecommerce.model.Customer;
import com.crimsonlogic.ecommerce.model.Seller;

public interface AuthenticationService {

    Customer loginCustomer(
            String email,
            String password);

    Seller loginSeller(
            String email,
            String password);

    Admin loginAdmin(
            String email,
            String password);

    void registerCustomer(
            Customer customer);

    void registerSeller(
            Seller seller);
}