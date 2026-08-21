package com.crimsonlogic.ecommerce.controller;

import com.crimsonlogic.ecommerce.model.Cart;
import com.crimsonlogic.ecommerce.model.Customer;
import com.crimsonlogic.ecommerce.model.Product;
import com.crimsonlogic.ecommerce.service.CartService;
import com.crimsonlogic.ecommerce.service.CustomerService;
import com.crimsonlogic.ecommerce.service.OrderService;
import com.crimsonlogic.ecommerce.service.ProductService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final CustomerService customerService;
    private final ProductService productService;
    private final OrderService orderService;


    public CartController(
            CartService cartService,
            CustomerService customerService,
            ProductService productService,
            OrderService orderService) {

        this.cartService = cartService;
        this.customerService = customerService;
        this.productService = productService;
        this.orderService = orderService;
    }


    // ==========================================================
    // CUSTOMER CART
    // ==========================================================

    @GetMapping("/{customerId}")
    public String viewCart(
            @PathVariable String customerId,
            Model model) {

        Customer customer =
                customerService.findCustomerById(
                        customerId
                );

        if (customer == null) {
            return "redirect:/";
        }

        List<Cart> cartItems =
                cartService.findCartByCustomer(
                        customerId
                );

        model.addAttribute(
                "customer",
                customer
        );

        model.addAttribute(
                "cartItems",
                cartItems
        );

        return "cart/cart";
    }


    // ==========================================================
    // ADD PRODUCT TO CART
    // ==========================================================

    @PostMapping("/{customerId}/add/{productId}")
    public String addToCart(
            @PathVariable String customerId,
            @PathVariable String productId,
            @RequestParam(defaultValue = "1")
            int quantity) {

        if (quantity <= 0) {
            return "redirect:/customer/products";
        }

        Customer customer =
                customerService.findCustomerById(
                        customerId
                );

        Product product =
                productService.findProductById(
                        productId
                );

        if (customer == null || product == null) {
            return "redirect:/customer/products";
        }


        Cart existingCart =
                cartService.findCartItem(
                        customerId,
                        productId
                );


        // ------------------------------------------------------
        // Product already exists in cart
        // ------------------------------------------------------

        if (existingCart != null) {

            existingCart.setQuantity(
                    existingCart.getQuantity()
                            + quantity
            );

            cartService.updateCartItem(
                    existingCart
            );

        }

        // ------------------------------------------------------
        // New cart item
        // ------------------------------------------------------

        else {

            Cart cart = new Cart();

            cart.setCartId(
                    "CART-" + System.currentTimeMillis()
            );

            cart.setCustomer(customer);

            cart.setProduct(product);

            cart.setQuantity(quantity);

            cartService.insertCartItem(cart);
        }


        return "redirect:/cart/" + customerId;
    }


    // ==========================================================
    // UPDATE QUANTITY
    // ==========================================================

    @PostMapping("/{customerId}/update/{cartId}")
    public String updateCartItem(
            @PathVariable String customerId,
            @PathVariable String cartId,
            @RequestParam int quantity) {

        Cart cart =
                cartService.findCartItemById(
                        cartId
                );

        if (cart != null) {

            if (quantity <= 0) {

                cartService.deleteCartItem(
                        cartId
                );

            } else {

                cart.setQuantity(quantity);

                cartService.updateCartItem(
                        cart
                );
            }
        }

        return "redirect:/cart/" + customerId;
    }


    // ==========================================================
    // REMOVE ITEM
    // ==========================================================

    @PostMapping("/{customerId}/remove/{cartId}")
    public String removeCartItem(
            @PathVariable String customerId,
            @PathVariable String cartId) {

        Cart cart =
                cartService.findCartItemById(
                        cartId
                );

        if (cart != null) {

            cartService.deleteCartItem(
                    cartId
            );
        }

        return "redirect:/cart/" + customerId;
    }


    // ==========================================================
    // CLEAR CART
    // ==========================================================

    @PostMapping("/{customerId}/clear")
    public String clearCart(
            @PathVariable String customerId) {

        cartService.clearCart(
                customerId
        );

        return "redirect:/cart/" + customerId;
    }
    
}