package com.crimsonlogic.ecommerce.service.impl;

import com.crimsonlogic.ecommerce.dao.CartMapper;
import com.crimsonlogic.ecommerce.model.Cart;
import com.crimsonlogic.ecommerce.service.CartService;

import java.util.List;

public class CartServiceImpl implements CartService {

    private CartMapper cartMapper;


    public void setCartMapper(
            CartMapper cartMapper) {

        this.cartMapper = cartMapper;
    }


    @Override
    public void insertCartItem(
            Cart cart) {

        cartMapper.insertCartItem(cart);
    }


    @Override
    public void updateCartItem(
            Cart cart) {

        cartMapper.updateCartItem(cart);
    }


    @Override
    public void deleteCartItem(
            String cartId) {

        cartMapper.deleteCartItem(cartId);
    }


    @Override
    public void clearCart(
            String customerId) {

        cartMapper.clearCart(customerId);
    }


    @Override
    public Cart findCartItemById(
            String cartId) {

        return cartMapper.findCartItemById(cartId);
    }


    @Override
    public Cart findCartItem(
            String customerId,
            String productId) {

        return cartMapper.findCartItem(
                customerId,
                productId
        );
    }


    @Override
    public List<Cart> findCartByCustomer(
            String customerId) {

        return cartMapper.findCartByCustomer(
                customerId
        );
    }


    @Override
    public List<Cart> findAllCartItems() {

        return cartMapper.findAllCartItems();
    }
}