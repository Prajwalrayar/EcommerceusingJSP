package com.crimsonlogic.ecommerce.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crimsonlogic.ecommerce.model.Address;
import com.crimsonlogic.ecommerce.model.Customer;
import com.crimsonlogic.ecommerce.model.Product;
import com.crimsonlogic.ecommerce.service.AddressService;
import com.crimsonlogic.ecommerce.service.CartService;
import com.crimsonlogic.ecommerce.service.CustomerService;
import com.crimsonlogic.ecommerce.service.ProductService;


@Controller
@RequestMapping("/customer")
public class CustomerController {


    private final CustomerService customerService;

    private final AddressService addressService;

    private final ProductService productService;

    private final CartService cartService;


    public CustomerController(
            CustomerService customerService,
            AddressService addressService,
            ProductService productService,
            CartService cartService) {

        this.customerService =
                customerService;

        this.addressService =
                addressService;

        this.productService =
                productService;

        this.cartService =
                cartService;
    }


    // =====================================================
    // CUSTOMER DASHBOARD
    // =====================================================

    @GetMapping("/dashboard")
    public String customerDashboard(
            HttpSession session,
            Model model) {

        Customer customer =
                getLoggedInCustomer(session);


        if (customer == null) {

            return "redirect:/customer/login";
        }


        /*
         * Refresh customer information from database.
         *
         * This ensures that the profile displayed on the
         * dashboard is not stale session data.
         */
        Customer currentCustomer =
                customerService.findCustomerById(
                        customer.getUserId());


        if (currentCustomer == null) {

            session.invalidate();

            return "redirect:/customer/login";
        }


        /*
         * Keep the latest Customer in session.
         */
        session.setAttribute(
                "loggedInUser",
                currentCustomer);


        model.addAttribute(
                "customer",
                currentCustomer);


        return "customer/dashboard";
    }


    // =====================================================
    // CUSTOMER PROFILE
    // =====================================================

    @GetMapping("/profile/{customerId}")
    public String viewCustomerProfile(
            @PathVariable String customerId,
            HttpSession session,
            Model model) {

        Customer loggedInCustomer =
                getLoggedInCustomer(session);


        if (loggedInCustomer == null) {

            return "redirect:/customer/login";
        }


        /*
         * A customer can view only his/her own profile.
         */
        if (!loggedInCustomer.getUserId()
                .equals(customerId)) {

            return "redirect:/customer/profile/"
                    + loggedInCustomer.getUserId();
        }


        Customer customer =
                customerService.findCustomerById(
                        customerId);


        if (customer == null) {

            return "redirect:/customer/dashboard";
        }


        List<Address> addresses =
                customerService.findCustomerAddresses(
                        customerId);


        model.addAttribute(
                "customer",
                customer);


        model.addAttribute(
                "addresses",
                addresses);


        return "customer/customer-profile";
    }


    // =====================================================
    // EDIT CUSTOMER PROFILE
    // =====================================================

    @GetMapping("/profile/edit/{customerId}")
    public String editProfile(
            @PathVariable String customerId,
            HttpSession session,
            Model model) {

        Customer loggedInCustomer =
                getLoggedInCustomer(session);


        if (loggedInCustomer == null) {

            return "redirect:/customer/login";
        }


        /*
         * A customer can edit only his/her own profile.
         */
        if (!loggedInCustomer.getUserId()
                .equals(customerId)) {

            return "redirect:/customer/profile/"
                    + loggedInCustomer.getUserId();
        }


        Customer customer =
                customerService.findCustomerById(
                        customerId);


        if (customer == null) {

            return "redirect:/customer/dashboard";
        }


        model.addAttribute(
                "customer",
                customer);


        return "customer/edit-profile";
    }


    // =====================================================
    // UPDATE CUSTOMER PROFILE
    // =====================================================

    @PostMapping("/profile/update")
    public String updateProfile(
            @ModelAttribute Customer customer,
            HttpSession session,
            Model model) {

        Customer loggedInCustomer =
                getLoggedInCustomer(session);


        if (loggedInCustomer == null) {

            return "redirect:/customer/login";
        }


        /*
         * Never trust a customer ID coming only from the form.
         *
         * The submitted ID must belong to the logged-in user.
         */
        if (customer.getUserId() == null ||
                !loggedInCustomer.getUserId()
                        .equals(customer.getUserId())) {

            return "redirect:/customer/profile/"
                    + loggedInCustomer.getUserId();
        }


        try {

            /*
             * All validation and business logic are handled
             * inside CustomerServiceImpl.
             */
            customerService.updateCustomer(
                    customer);


            /*
             * Reload the updated Customer.
             */
            Customer updatedCustomer =
                    customerService.findCustomerById(
                            loggedInCustomer.getUserId());


            /*
             * Update session with latest Customer data.
             */
            session.setAttribute(
                    "loggedInUser",
                    updatedCustomer);


            return "redirect:/customer/profile/"
                    + updatedCustomer.getUserId();

        } catch (RuntimeException exception) {

            /*
             * Validation/business rules remain in service layer.
             *
             * Controller only passes the service error to JSP.
             */
            model.addAttribute(
                    "error",
                    exception.getMessage());


            model.addAttribute(
                    "customer",
                    customer);


            return "customer/edit-profile";
        }
    }


    // =====================================================
    // SHOW ASSIGN ADDRESS PAGE
    // =====================================================

    @GetMapping("/{customerId}/addresses/add")
    public String showAssignAddressPage(
            @PathVariable String customerId,
            HttpSession session,
            Model model) {

        Customer loggedInCustomer =
                getLoggedInCustomer(session);


        if (loggedInCustomer == null) {

            return "redirect:/customer/login";
        }


        /*
         * Customer can manage only his/her own addresses.
         */
        if (!loggedInCustomer.getUserId()
                .equals(customerId)) {

            return "redirect:/customer/profile/"
                    + loggedInCustomer.getUserId();
        }


        Customer customer =
                customerService.findCustomerById(
                        customerId);


        if (customer == null) {

            return "redirect:/customer/dashboard";
        }


        List<Address> addresses =
                addressService.findAllAddresses();


        List<Address> customerAddresses =
                customerService.findCustomerAddresses(
                        customerId);


        model.addAttribute(
                "customer",
                customer);


        model.addAttribute(
                "addresses",
                addresses);


        model.addAttribute(
                "customerAddresses",
                customerAddresses);


        return "address/assign-customer-address";
    }


    // =====================================================
    // ASSIGN ADDRESS
    // =====================================================

    @PostMapping("/{customerId}/addresses/add")
    public String assignAddress(
            @PathVariable String customerId,
            @RequestParam String addressId,
            HttpSession session) {

        Customer loggedInCustomer =
                getLoggedInCustomer(session);


        if (loggedInCustomer == null) {

            return "redirect:/customer/login";
        }


        /*
         * Customer can assign an address only to
         * his/her own account.
         */
        if (!loggedInCustomer.getUserId()
                .equals(customerId)) {

            return "redirect:/customer/profile/"
                    + loggedInCustomer.getUserId();
        }


        /*
         * Address validation and duplicate assignment
         * rules are handled by CustomerServiceImpl.
         */
        customerService.assignAddress(
                customerId,
                addressId);


        return "redirect:/customer/profile/"
                + customerId;
    }


    // =====================================================
    // REMOVE ADDRESS
    // =====================================================

    @PostMapping(
            "/{customerId}/addresses/remove/{addressId}")
    public String removeAddress(
            @PathVariable String customerId,
            @PathVariable String addressId,
            HttpSession session) {

        Customer loggedInCustomer =
                getLoggedInCustomer(session);


        if (loggedInCustomer == null) {

            return "redirect:/customer/login";
        }


        /*
         * Customer can remove an address only from
         * his/her own account.
         */
        if (!loggedInCustomer.getUserId()
                .equals(customerId)) {

            return "redirect:/customer/profile/"
                    + loggedInCustomer.getUserId();
        }


        /*
         * Address ownership and validation are handled
         * by CustomerServiceImpl.
         */
        customerService.removeAddress(
                customerId,
                addressId);


        return "redirect:/customer/profile/"
                + customerId;
    }


    // =====================================================
    // VIEW PRODUCTS
    // =====================================================

    @GetMapping("/products")
    public String products(
            HttpSession session,
            Model model) {

        Customer customer =
                getLoggedInCustomer(session);


        if (customer == null) {

            return "redirect:/customer/login";
        }


        List<Product> products =
                productService.findAllProducts();


        model.addAttribute(
                "products",
                products);


        model.addAttribute(
                "customer",
                customer);


        return "customer/products";
    }


    // =====================================================
    // ADD PRODUCT TO CART
    // =====================================================

    @PostMapping("/cart/add")
    public String addToCart(
            @RequestParam("productId") String productId,
            @RequestParam("quantity") int quantity,
            HttpSession session,
            Model model) {

        Customer customer =
                getLoggedInCustomer(session);


        if (customer == null) {

            return "redirect:/customer/login";
        }


        try {

            /*
             * IMPORTANT:
             *
             * The controller does NOT:
             *
             * - create Cart
             * - generate Cart ID
             * - find existing Cart
             * - calculate Cart quantity
             * - validate Product
             * - validate Inventory
             * - validate stock
             *
             * All of these business rules belong to
             * CartServiceImpl.
             *
             * The project uses Cart, NOT CartItem.
             */
            cartService.addToCart(
                    customer.getUserId(),
                    productId,
                    quantity);


            return "redirect:/customer/cart";

        } catch (RuntimeException exception) {

            /*
             * Business validation remains in service layer.
             */
            model.addAttribute(
                    "error",
                    exception.getMessage());


            /*
             * Return to product page so the customer can
             * correct the request.
             */
            List<Product> products =
                    productService.findAllProducts();


            model.addAttribute(
                    "products",
                    products);


            model.addAttribute(
                    "customer",
                    customer);


            return "customer/products";
        }
    }


    // =====================================================
    // GET LOGGED-IN CUSTOMER
    // =====================================================

    private Customer getLoggedInCustomer(
            HttpSession session) {

        Object loggedInUser =
                session.getAttribute(
                        "loggedInUser");


        if (!(loggedInUser instanceof Customer)) {

            return null;
        }


        return (Customer) loggedInUser;
    }
}