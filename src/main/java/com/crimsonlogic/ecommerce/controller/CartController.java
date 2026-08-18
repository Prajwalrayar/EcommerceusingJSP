package com.crimsonlogic.ecommerce.controller;

import com.crimsonlogic.ecommerce.model.Cart;
import com.crimsonlogic.ecommerce.model.Customer;
import com.crimsonlogic.ecommerce.model.Product;
import com.crimsonlogic.ecommerce.service.CartService;
import com.crimsonlogic.ecommerce.service.CustomerService;
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


    public CartController(
            CartService cartService,
            CustomerService customerService,
            ProductService productService) {

        this.cartService = cartService;
        this.customerService = customerService;
        this.productService = productService;
    }


    // ==========================================================
    // Customer Cart
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
    // Add Product To Cart
    // ==========================================================

    @PostMapping("/{customerId}/add/{productId}")
    public String addToCart(
            @PathVariable String customerId,
            @PathVariable String productId,
            @RequestParam(defaultValue = "1")
            int quantity) {

        Cart existingCart =
                cartService.findCartItem(
                        customerId,
                        productId
                );

        if (existingCart != null) {

            existingCart.setQuantity(
                    existingCart.getQuantity()
                            + quantity
            );

            cartService.updateCartItem(
                    existingCart
            );

        } else {

            Customer customer =
                    customerService.findCustomerById(
                            customerId
                    );

            Product product =
                    productService.findProductById(
                            productId
                    );

            if (customer == null || product == null) {
                return "redirect:/product/list";
            }

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
    // Update Quantity
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
    // Remove Item
    // ==========================================================

    @PostMapping("/{customerId}/remove/{cartId}")
    public String removeCartItem(
            @PathVariable String customerId,
            @PathVariable String cartId) {

        cartService.deleteCartItem(cartId);

        return "redirect:/cart/" + customerId;
    }


    // ==========================================================
    // Clear Cart
    // ==========================================================

    @PostMapping("/{customerId}/clear")
    public String clearCart(
            @PathVariable String customerId) {

        cartService.clearCart(customerId);

        return "redirect:/cart/" + customerId;
    }
}