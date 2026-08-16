package com.crimsonlogic.ecommerce.service;

import com.crimsonlogic.ecommerce.model.Cart;

import java.util.List;

public interface CartService {

    /**
     * Inserts Cart Item.
     */
    void insertCartItem(Cart cart);


    /**
     * Updates Cart Item.
     */
    void updateCartItem(Cart cart);


    /**
     * Deletes Cart Item.
     */
    void deleteCartItem(String cartId);


    /**
     * Clears Customer Cart.
     */
    void clearCart(String customerId);


    /**
     * Finds Cart Item by ID.
     */
    Cart findCartItemById(String cartId);


    /**
     * Finds Cart Item by Customer and Product.
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
     * Returns All Cart Items.
     */
    List<Cart> findAllCartItems();
}