package com.crimsonlogic.ecommerce.dao;

import com.crimsonlogic.ecommerce.model.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {

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
    void deleteCartItem(
            @Param("cartId")
            String cartId);


    /**
     * Clears Customer Cart.
     */
    void clearCart(
            @Param("customerId")
            String customerId);


    /**
     * Finds Cart Item by ID.
     */
    Cart findCartItemById(
            @Param("cartId")
            String cartId);


    /**
     * Finds Cart Item by Customer and Product.
     */
    Cart findCartItem(
            @Param("customerId")
            String customerId,

            @Param("productId")
            String productId);


    /**
     * Returns Customer Cart.
     */
    List<Cart> findCartByCustomer(
            @Param("customerId")
            String customerId);


    /**
     * Returns All Cart Items.
     */
    List<Cart> findAllCartItems();
}