package com.crimsonlogic.ecommerce.model;

public class Cart {

    /**
     * Cart ID.
     */
    private String cartId;

    /**
     * Customer.
     */
    private Customer customer;

    /**
     * Product.
     */
    private Product product;

    /**
     * Quantity.
     */
    private int quantity;


    /**
     * Default Constructor.
     */
    public Cart() {
    }


    /**
     * Parameterized Constructor.
     */
    public Cart(
            String cartId,
            Customer customer,
            Product product,
            int quantity) {

        this.cartId = cartId;
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
    }


    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    /**
     * Returns total price of this cart item.
     */
    public double getTotalPrice() {

        if (product == null) {
            return 0.0;
        }

        return quantity * product.getProductPrice();
    }
}