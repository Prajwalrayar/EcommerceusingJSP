package com.crimsonlogic.ecommerce.controller;

import com.crimsonlogic.ecommerce.exception.DuplicateUserException;
import com.crimsonlogic.ecommerce.exception.InvalidCredentialsException;
import com.crimsonlogic.ecommerce.exception.ValidationException;
import com.crimsonlogic.ecommerce.model.Admin;
import com.crimsonlogic.ecommerce.model.Customer;
import com.crimsonlogic.ecommerce.model.Seller;
import com.crimsonlogic.ecommerce.service.AuthenticationService;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    public AuthenticationController(
            AuthenticationService authenticationService) {

        this.authenticationService =
                authenticationService;
    }


    // ==========================================================
    // ADMIN LOGIN PAGE
    // ==========================================================

    @GetMapping("/admin/login")
    public String showAdminLoginPage() {

        return "admin/login";
    }


    // ==========================================================
    // ADMIN LOGIN
    // ==========================================================

    @PostMapping("/admin/login")
    public String loginAdmin(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        try {

            Admin admin =
                    authenticationService.loginAdmin(
                            email,
                            password);

            session.setAttribute(
                    "loggedInUser",
                    admin);

            session.setAttribute(
                    "role",
                    admin.getRole());

            session.setAttribute(
                    "userId",
                    admin.getUserId());

            return "redirect:/admin/dashboard";

        } catch (InvalidCredentialsException |
                 ValidationException exception) {

            model.addAttribute(
                    "error",
                    exception.getMessage());

            return "admin/login";
        }
    }


    // ==========================================================
    // SELLER LOGIN PAGE
    // ==========================================================

    @GetMapping("/seller/login")
    public String showSellerLoginPage() {

        return "seller/login";
    }


    // ==========================================================
    // SELLER LOGIN
    // ==========================================================

    @PostMapping("/seller/login")
    public String loginSeller(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        try {

            Seller seller =
                    authenticationService.loginSeller(
                            email,
                            password);

            session.setAttribute(
                    "loggedInUser",
                    seller);

            session.setAttribute(
                    "role",
                    seller.getRole());

            session.setAttribute(
                    "userId",
                    seller.getUserId());

            return "redirect:/seller/dashboard";

        } catch (InvalidCredentialsException |
                 ValidationException exception) {

            model.addAttribute(
                    "error",
                    exception.getMessage());

            return "seller/login";
        }
    }


    // ==========================================================
    // SELLER REGISTRATION PAGE
    // ==========================================================

    @GetMapping("/seller/register")
    public String showSellerRegistrationPage(
            Model model) {

        model.addAttribute(
                "seller",
                new Seller());

        return "seller/register";
    }


    // ==========================================================
    // SELLER REGISTRATION
    // ==========================================================

    @PostMapping("/seller/register")
    public String registerSeller(
            @ModelAttribute Seller seller,
            Model model) {

        try {

            authenticationService.registerSeller(
                    seller);

            model.addAttribute(
                    "success",
                    "Seller registered successfully. Please login.");

            return "seller/login";

        } catch (DuplicateUserException |
                 ValidationException exception) {

            model.addAttribute(
                    "error",
                    exception.getMessage());

            model.addAttribute(
                    "seller",
                    seller);

            return "seller/register";
        }
    }


    // ==========================================================
    // CUSTOMER LOGIN PAGE
    // ==========================================================

    @GetMapping("/customer/login")
    public String showCustomerLoginPage() {

        return "customer/login";
    }


    // ==========================================================
    // CUSTOMER LOGIN
    // ==========================================================

    @PostMapping("/customer/login")
    public String loginCustomer(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        try {

            Customer customer =
                    authenticationService.loginCustomer(
                            email,
                            password);

            session.setAttribute(
                    "loggedInUser",
                    customer);

            session.setAttribute(
                    "role",
                    customer.getRole());

            session.setAttribute(
                    "userId",
                    customer.getUserId());

            return "redirect:/customer/dashboard";

        } catch (InvalidCredentialsException |
                 ValidationException exception) {

            model.addAttribute(
                    "error",
                    exception.getMessage());

            return "customer/login";
        }
    }


    // ==========================================================
    // CUSTOMER REGISTRATION PAGE
    // ==========================================================

    @GetMapping("/customer/register")
    public String showCustomerRegistrationPage(
            Model model) {

        model.addAttribute(
                "customer",
                new Customer());

        return "customer/register";
    }


    // ==========================================================
    // CUSTOMER REGISTRATION
    // ==========================================================

    @PostMapping("/customer/register")
    public String registerCustomer(
            @ModelAttribute Customer customer,
            Model model) {

        try {

            authenticationService.registerCustomer(
                    customer);

            model.addAttribute(
                    "success",
                    "Customer registered successfully. Please login.");

            return "customer/login";

        } catch (DuplicateUserException |
                 ValidationException exception) {

            model.addAttribute(
                    "error",
                    exception.getMessage());

            model.addAttribute(
                    "customer",
                    customer);

            return "customer/register";
        }
    }


    // ==========================================================
    // LOGOUT
    // ==========================================================

    @GetMapping("/logout")
    public String logout(
            HttpSession session) {

        session.invalidate();

        return "redirect:/";
    }
}