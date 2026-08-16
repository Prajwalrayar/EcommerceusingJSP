package com.crimsonlogic.ecommerce.dao;

import com.crimsonlogic.ecommerce.model.Admin;
import com.crimsonlogic.ecommerce.model.Customer;
import com.crimsonlogic.ecommerce.model.Seller;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {

    List<Customer> findAllCustomers();

    List<Seller> findAllSellers();

    void deleteCustomer(
            @Param("customerId")
            String customerId);

    void deleteSeller(
            @Param("sellerId")
            String sellerId);


    Admin findAdminById(
            @Param("adminId")
            String adminId);


    Admin findAdminByEmail(
            @Param("email")
            String email);

    void insertAdmin(Admin admin);

    List<Admin> findAllAdmins();

    Admin findAdminByPhone(
            @Param("phone")
            String phone);


    void updateAdminPhone(
            Admin admin);


    void updateAdminPassword(
            @Param("userId")
            String userId,

            @Param("userPassword")
            String userPassword);
}