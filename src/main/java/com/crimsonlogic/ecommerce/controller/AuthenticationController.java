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

/**
 * Controller responsible for handling authentication-related requests.
 *
 * The controller manages login and registration operations for
 * administrators, sellers, and customers.
 *
 * It receives authentication data from the web pages, delegates
 * authentication and registration processing to AuthenticationService,
 * manages the authenticated user's HTTP session, and returns the
 * appropriate view or redirect.
 */
@Controller
public class AuthenticationController {

    /**
     * Service used to perform authentication and user registration operations.
     *
     * The controller delegates authentication-related business processing
     * to the service layer instead of directly accessing the database.
     */
    private final AuthenticationService authenticationService;


    /**
     * Creates the AuthenticationController with its required service dependency.
     *
     * Constructor injection allows Spring to provide the
     * AuthenticationService instance required for authentication operations.
     *
     * @param authenticationService service used for login and registration operations
     */
    public AuthenticationController(
            AuthenticationService authenticationService) {

        this.authenticationService =
                authenticationService;
    }


    // ==========================================================
    // ADMIN LOGIN PAGE
    // ==========================================================

    /**
     * Displays the administrator login page.
     *
     * This endpoint is used to display the login form before
     * administrator authentication is performed.
     *
     * HTTP method: GET
     * Endpoint: /admin/login
     *
     * @return administrator login view
     */
    @GetMapping("/admin/login")
    public String showAdminLoginPage() {

        return "admin/login";
    }


    // ==========================================================
    // ADMIN LOGIN
    // ==========================================================

    /**
     * Authenticates an administrator.
     *
     * The supplied email and password are passed to the authentication
     * service for validation. When authentication succeeds, the
     * administrator information, role, and user ID are stored in
     * the HTTP session.
     *
     * HTTP method: POST
     * Endpoint: /admin/login
     *
     * @param email administrator email address supplied from the login form
     * @param password administrator password supplied from the login form
     * @param session HTTP session used to store authenticated administrator information
     * @param model model used to pass authentication errors to the login view
     * @return administrator dashboard after successful login or login view when authentication fails
     */
    @PostMapping("/admin/login")
    public String loginAdmin(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        try {

            // Delegate administrator authentication to the service layer.
            Admin admin =
                    authenticationService.loginAdmin(
                            email,
                            password);

            // Store the authenticated administrator in the current session.
            session.setAttribute(
                    "loggedInUser",
                    admin);

            // Store the administrator's role for role-based access control.
            session.setAttribute(
                    "role",
                    admin.getRole());

            // Store the administrator ID so it can be used by subsequent requests.
            session.setAttribute(
                    "userId",
                    admin.getUserId());

            // Redirect to the administrator dashboard after successful authentication.
            return "redirect:/admin/dashboard";

        } catch (InvalidCredentialsException |
                 ValidationException exception) {

            // Display the authentication or validation error on the login page.
            model.addAttribute(
                    "error",
                    exception.getMessage());

            return "admin/login";
        }
    }


    // ==========================================================
    // SELLER LOGIN PAGE
    // ==========================================================

    /**
     * Displays the seller login page.
     *
     * This endpoint displays the login form before seller authentication
     * is performed.
     *
     * HTTP method: GET
     * Endpoint: /seller/login
     *
     * @return seller login view
     */
    @GetMapping("/seller/login")
    public String showSellerLoginPage() {

        return "seller/login";
    }


    // ==========================================================
    // SELLER LOGIN
    // ==========================================================

    /**
     * Authenticates a seller.
     *
     * The supplied seller credentials are validated by the
     * AuthenticationService. When authentication succeeds,
     * seller information, role, and user ID are stored in the
     * HTTP session.
     *
     * HTTP method: POST
     * Endpoint: /seller/login
     *
     * @param email seller email address supplied from the login form
     * @param password seller password supplied from the login form
     * @param session HTTP session used to store authenticated seller information
     * @param model model used to pass authentication errors to the login view
     * @return seller dashboard after successful login or login view when authentication fails
     */
    @PostMapping("/seller/login")
    public String loginSeller(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        try {

            // Delegate seller authentication to the service layer.
            Seller seller =
                    authenticationService.loginSeller(
                            email,
                            password);

            // Store the authenticated seller in the current session.
            session.setAttribute(
                    "loggedInUser",
                    seller);

            // Store the seller's role for role-based access control.
            session.setAttribute(
                    "role",
                    seller.getRole());

            // Store the seller ID for use by subsequent requests.
            session.setAttribute(
                    "userId",
                    seller.getUserId());

            // Redirect to the seller dashboard after successful authentication.
            return "redirect:/seller/dashboard";

        } catch (InvalidCredentialsException |
                 ValidationException exception) {

            // Display the authentication or validation error on the login page.
            model.addAttribute(
                    "error",
                    exception.getMessage());

            return "seller/login";
        }
    }


    // ==========================================================
    // SELLER REGISTRATION PAGE
    // ==========================================================

    /**
     * Displays the seller registration page.
     *
     * A new Seller object is added to the model so that the
     * registration form can bind its input fields to the object.
     *
     * HTTP method: GET
     * Endpoint: /seller/register
     *
     * @param model model used to provide a new Seller object to the registration form
     * @return seller registration view
     */
    @GetMapping("/seller/register")
    public String showSellerRegistrationPage(
            Model model) {

        // Provide an empty Seller object for form data binding.
        model.addAttribute(
                "seller",
                new Seller());

        return "seller/register";
    }


    // ==========================================================
    // SELLER REGISTRATION
    // ==========================================================

    /**
     * Registers a new seller.
     *
     * The submitted seller information is passed to the authentication
     * service for validation and registration.
     *
     * When registration succeeds, a success message is displayed
     * on the seller login page.
     *
     * DuplicateUserException and ValidationException are handled
     * so that the registration form can be displayed again with
     * the appropriate error message.
     *
     * HTTP method: POST
     * Endpoint: /seller/register
     *
     * @param seller seller information submitted from the registration form
     * @param model model used to pass success, error, and seller data to the views
     * @return seller login view after successful registration or registration view when validation fails
     */
    @PostMapping("/seller/register")
    public String registerSeller(
            @ModelAttribute Seller seller,
            Model model) {

        try {

            // Delegate seller registration and validation to the service layer.
            authenticationService.registerSeller(
                    seller);

            // Display a success message so the seller can proceed to login.
            model.addAttribute(
                    "success",
                    "Seller registered successfully. Please login.");

            return "seller/login";

        } catch (DuplicateUserException |
                 ValidationException exception) {

            // Display the registration error returned by the service layer.
            model.addAttribute(
                    "error",
                    exception.getMessage());

            // Preserve the submitted seller information when returning to the form.
            model.addAttribute(
                    "seller",
                    seller);

            return "seller/register";
        }
    }


    // ==========================================================
    // CUSTOMER LOGIN PAGE
    // ==========================================================

    /**
     * Displays the customer login page.
     *
     * This endpoint displays the login form before customer
     * authentication is performed.
     *
     * HTTP method: GET
     * Endpoint: /customer/login
     *
     * @return customer login view
     */
    @GetMapping("/customer/login")
    public String showCustomerLoginPage() {

        return "customer/login";
    }


    // ==========================================================
    // CUSTOMER LOGIN
    // ==========================================================

    /**
     * Authenticates a customer.
     *
     * The supplied customer credentials are validated by the
     * AuthenticationService. When authentication succeeds,
     * customer information, role, and user ID are stored in
     * the HTTP session.
     *
     * HTTP method: POST
     * Endpoint: /customer/login
     *
     * @param email customer email address supplied from the login form
     * @param password customer password supplied from the login form
     * @param session HTTP session used to store authenticated customer information
     * @param model model used to pass authentication errors to the login view
     * @return customer dashboard after successful login or login view when authentication fails
     */
    @PostMapping("/customer/login")
    public String loginCustomer(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        try {

            // Delegate customer authentication to the service layer.
            Customer customer =
                    authenticationService.loginCustomer(
                            email,
                            password);

            // Store the authenticated customer in the current session.
            session.setAttribute(
                    "loggedInUser",
                    customer);

            // Store the customer's role for role-based access control.
            session.setAttribute(
                    "role",
                    customer.getRole());

            // Store the customer ID for use by subsequent requests.
            session.setAttribute(
                    "userId",
                    customer.getUserId());

            // Redirect to the customer dashboard after successful authentication.
            return "redirect:/customer/dashboard";

        } catch (InvalidCredentialsException |
                 ValidationException exception) {

            // Display the authentication or validation error on the login page.
            model.addAttribute(
                    "error",
                    exception.getMessage());

            return "customer/login";
        }
    }


    // ==========================================================
    // CUSTOMER REGISTRATION PAGE
    // ==========================================================

    /**
     * Displays the customer registration page.
     *
     * A new Customer object is added to the model so that the
     * registration form can bind its input fields to the object.
     *
     * HTTP method: GET
     * Endpoint: /customer/register
     *
     * @param model model used to provide a new Customer object to the registration form
     * @return customer registration view
     */
    @GetMapping("/customer/register")
    public String showCustomerRegistrationPage(
            Model model) {

        // Provide an empty Customer object for form data binding.
        model.addAttribute(
                "customer",
                new Customer());

        return "customer/register";
    }


    // ==========================================================
    // CUSTOMER REGISTRATION
    // ==========================================================

    /**
     * Registers a new customer.
     *
     * The submitted customer information is passed to the
     * AuthenticationService for validation and registration.
     *
     * When registration succeeds, a success message is displayed
     * on the customer login page.
     *
     * DuplicateUserException and ValidationException are handled
     * so that the registration form can be displayed again with
     * the appropriate error message.
     *
     * HTTP method: POST
     * Endpoint: /customer/register
     *
     * @param customer customer information submitted from the registration form
     * @param model model used to pass success, error, and customer data to the views
     * @return customer login view after successful registration or registration view when validation fails
     */
    @PostMapping("/customer/register")
    public String registerCustomer(
            @ModelAttribute Customer customer,
            Model model) {

        try {

            // Delegate customer registration and validation to the service layer.
            authenticationService.registerCustomer(
                    customer);

            // Display a success message so the customer can proceed to login.
            model.addAttribute(
                    "success",
                    "Customer registered successfully. Please login.");

            return "customer/login";

        } catch (DuplicateUserException |
                 ValidationException exception) {

            // Display the registration error returned by the service layer.
            model.addAttribute(
                    "error",
                    exception.getMessage());

            // Preserve the submitted customer information when returning to the form.
            model.addAttribute(
                    "customer",
                    customer);

            return "customer/register";
        }
    }


    // ==========================================================
    // LOGOUT
    // ==========================================================

    /**
     * Logs out the currently authenticated user.
     *
     * Invalidating the HTTP session removes the stored authentication
     * information, including the logged-in user, role, and user ID.
     *
     * HTTP method: GET
     * Endpoint: /logout
     *
     * @param session HTTP session associated with the current user
     * @return redirect to the application's home page
     */
    @GetMapping("/logout")
    public String logout(
            HttpSession session) {

        // Invalidate the current session to remove authenticated user information.
        session.invalidate();

        // Redirect the user to the application's home page after logout.
        return "redirect:/";
    }
}