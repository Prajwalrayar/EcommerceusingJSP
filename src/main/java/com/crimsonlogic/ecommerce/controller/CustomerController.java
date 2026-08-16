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
import com.crimsonlogic.ecommerce.model.Cart;
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

        this.customerService = customerService;
        this.addressService = addressService;
        this.productService = productService;
        this.cartService = cartService;
    }


    // =====================================================
    // CUSTOMER DASHBOARD
    // =====================================================

    @GetMapping("/dashboard")
    public String customerDashboard(
            HttpSession session,
            Model model) {

        Customer customer =
                (Customer) session.getAttribute(
                        "loggedInUser");

        if (customer == null) {

            return "redirect:/customer/login";
        }

        model.addAttribute(
                "customer",
                customer);

        return "customer/dashboard";
    }


    // =====================================================
    // CUSTOMER PROFILE
    // =====================================================

    @GetMapping("/profile/{customerId}")
    public String viewCustomerProfile(
            @PathVariable String customerId,
            Model model) {

        Customer customer =
                customerService.findCustomerById(
                        customerId);

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
            Model model) {

        Customer customer =
                customerService.findCustomerById(
                        customerId);

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
            @ModelAttribute Customer customer) {

        customerService.updateCustomer(
                customer);

        return "redirect:/customer/profile/"
                + customer.getUserId();
    }


    // =====================================================
    // SHOW ASSIGN ADDRESS PAGE
    // =====================================================

    @GetMapping("/{customerId}/addresses/add")
    public String showAssignAddressPage(
            @PathVariable String customerId,
            Model model) {

        Customer customer =
                customerService.findCustomerById(
                        customerId);

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
            @RequestParam String addressId) {

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
            @PathVariable String addressId) {

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
                (Customer) session.getAttribute(
                        "loggedInUser");

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
            HttpSession session) {

        Customer customer =
                (Customer) session.getAttribute(
                        "loggedInUser");

        if (customer == null) {

            return "redirect:/customer/login";
        }

        Cart existingCart =
                cartService.findCartItem(
                        customer.getUserId(),
                        productId);

        if (existingCart != null) {

            existingCart.setQuantity(
                    existingCart.getQuantity()
                            + quantity);

            cartService.updateCartItem(
                    existingCart);

        } else {

            Product product =
                    productService.findProductById(
                            productId);

            if (product == null) {

                return "redirect:/customer/products";
            }

            Cart cart = new Cart();

            cart.setCustomer(customer);

            cart.setProduct(product);

            cart.setQuantity(quantity);

            cartService.insertCartItem(
                    cart);
        }

        return "redirect:/customer/cart";
    }
}