package com.crimsonlogic.ecommerce.service;

import com.crimsonlogic.ecommerce.model.Cart;

import java.util.List;

public interface CartService {

    /**
     * Inserts Cart.
     */
    void insertCartItem(
            Cart cart);


    /**
     * Updates Cart.
     */
    void updateCartItem(
            Cart cart);


    /**
     * Deletes Cart.
     */
    void deleteCartItem(
            String cartId);


    /**
     * Clears Customer Cart.
     */
    void clearCart(
            String customerId);


    /**
     * Finds Cart by ID.
     */
    Cart findCartItemById(
            String cartId);


    /**
     * Finds Cart by Customer and Product.
     */
    Cart findCartItem(
            String customerId,
            String productId);


    /**
     * Returns Customer Cart.
     */
    List<Cart> findCartByCustomer(
            String customerId);


    /**
     * Returns All Cart records.
     */
    List<Cart> findAllCartItems();


    /**
     * Adds Product to Customer Cart.
     *
     * All validation and business rules are handled
     * inside CartServiceImpl.
     *
     * No CartItem is used.
     */
    void addToCart(
            String customerId,
            String productId,
            int quantity);
}