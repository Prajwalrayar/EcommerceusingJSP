package com.crimsonlogic.ecommerce.service.impl;

import com.crimsonlogic.ecommerce.dao.CartMapper;
import com.crimsonlogic.ecommerce.dao.CustomerMapper;
import com.crimsonlogic.ecommerce.dao.InventoryMapper;
import com.crimsonlogic.ecommerce.dao.ProductMapper;
import com.crimsonlogic.ecommerce.enumeration.ProductStatus;
import com.crimsonlogic.ecommerce.exception.ValidationException;
import com.crimsonlogic.ecommerce.model.Cart;
import com.crimsonlogic.ecommerce.model.Customer;
import com.crimsonlogic.ecommerce.model.Inventory;
import com.crimsonlogic.ecommerce.model.Product;
import com.crimsonlogic.ecommerce.service.CartService;
import com.crimsonlogic.ecommerce.util.IdGenerator;

import java.util.List;

public class CartServiceImpl implements CartService {

	private CartMapper cartMapper;

	private ProductMapper productMapper;

	private InventoryMapper inventoryMapper;

	private CustomerMapper customerMapper;
	
	public void setCartMapper(CartMapper cartMapper) {
	    this.cartMapper = cartMapper;
	}

	public void setProductMapper(ProductMapper productMapper) {
	    this.productMapper = productMapper;
	}

	public void setInventoryMapper(InventoryMapper inventoryMapper) {
	    this.inventoryMapper = inventoryMapper;
	}

	public void setCustomerMapper(CustomerMapper customerMapper) {
	    this.customerMapper = customerMapper;
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


    @Override
    public void addToCart(
            String customerId,
            String productId,
            int quantity) {

        // ------------------------------------------------------
        // VALIDATE CUSTOMER ID
        // ------------------------------------------------------

        if (customerId == null ||
                customerId.trim().isEmpty()) {

            throw new ValidationException(
                    "Customer ID is required."
            );
        }


        // ------------------------------------------------------
        // VALIDATE PRODUCT ID
        // ------------------------------------------------------

        if (productId == null ||
                productId.trim().isEmpty()) {

            throw new ValidationException(
                    "Product ID is required."
            );
        }


        // ------------------------------------------------------
        // VALIDATE QUANTITY
        // ------------------------------------------------------

        if (quantity <= 0) {

            throw new ValidationException(
                    "Quantity must be greater than zero."
            );
        }


        // ------------------------------------------------------
        // FIND CUSTOMER
        // ------------------------------------------------------

        Customer customer =
                customerMapper.findCustomerById(
                        customerId
                );

        if (customer == null) {

            throw new ValidationException(
                    "Customer not found."
            );
        }


        // ------------------------------------------------------
        // FIND PRODUCT
        // ------------------------------------------------------

        Product product =
                productMapper.findProductById(
                        productId
                );

        if (product == null) {

            throw new ValidationException(
                    "Product not found."
            );
        }


        // ------------------------------------------------------
        // CHECK PRODUCT STATUS
        // ------------------------------------------------------

        if (product.getProductStatus()
                != ProductStatus.AVAILABLE) {

            throw new ValidationException(
                    "Product is not available."
            );
        }


        // ------------------------------------------------------
        // FIND INVENTORY
        // ------------------------------------------------------

        Inventory inventory =
                inventoryMapper.findInventoryByProduct(
                        productId
                );

        if (inventory == null) {

            throw new ValidationException(
                    "Inventory not found for this product."
            );
        }


        // ------------------------------------------------------
        // CHECK STOCK
        // ------------------------------------------------------

        if (inventory.getQuantity() <= 0) {

            throw new ValidationException(
                    "Product is out of stock."
            );
        }


        if (quantity > inventory.getQuantity()) {

            throw new ValidationException(
                    "Insufficient stock. Available quantity: "
                            + inventory.getQuantity()
            );
        }


        // ------------------------------------------------------
        // CHECK EXISTING CART
        // ------------------------------------------------------

        Cart existingCart =
                cartMapper.findCartItem(
                        customerId,
                        productId
                );


        // ======================================================
        // EXISTING CART
        // ======================================================

        if (existingCart != null) {

            int newQuantity =
                    existingCart.getQuantity()
                            + quantity;


            if (newQuantity >
                    inventory.getQuantity()) {

                throw new ValidationException(
                        "Requested quantity exceeds available stock. "
                                + "Available quantity: "
                                + inventory.getQuantity()
                );
            }


            existingCart.setQuantity(
                    newQuantity
            );


            cartMapper.updateCartItem(
                    existingCart
            );

            return;
        }


        // ======================================================
        // NEW CART
        // ======================================================

        Cart cart =
                new Cart();


        // ------------------------------------------------------
        // GENERATE CART ID
        // ------------------------------------------------------

        cart.setCartId(
                IdGenerator.generateId("CRT")
        );


        // ------------------------------------------------------
        // SET CUSTOMER
        // ------------------------------------------------------

        cart.setCustomer(
                customer
        );


        // ------------------------------------------------------
        // SET PRODUCT
        // ------------------------------------------------------

        cart.setProduct(
                product
        );


        // ------------------------------------------------------
        // SET QUANTITY
        // ------------------------------------------------------

        cart.setQuantity(
                quantity
        );


        // ------------------------------------------------------
        // INSERT CART
        // ------------------------------------------------------

        cartMapper.insertCartItem(
                cart
        );
    }
}