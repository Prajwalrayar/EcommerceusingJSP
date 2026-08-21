package com.crimsonlogic.ecommerce.model;

/**
 * Represents a shopping cart item in the ecommerce application.
 *
 * A Cart object associates a customer with a product and stores
 * the quantity of that product selected by the customer.
 *
 * The class also provides a method to calculate the total price
 * of the cart item based on the product price and selected quantity.
 */
public class Cart {

    /**
     * Unique identifier of the cart item.
     */
    private String cartId;

    /**
     * Customer who owns this cart item.
     */
    private Customer customer;

    /**
     * Product added to the customer's cart.
     */
    private Product product;

    /**
     * Quantity of the product added to the cart.
     */
    private int quantity;



    /**
     * Default constructor.
     *
     * Creates an empty Cart object that can be populated
     * using the setter methods.
     */
    public Cart() {

    }



    /**
     * Creates a Cart object using the supplied cart information.
     *
     * @param cartId unique identifier of the cart item
     * @param customer customer who owns the cart item
     * @param product product added to the cart
     * @param quantity quantity of the selected product
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



    /**
     * Returns the unique identifier of the cart item.
     *
     * @return cart item ID
     */
    public String getCartId() {

        return cartId;

    }

    /**
     * Updates the unique identifier of the cart item.
     *
     * @param cartId new cart item ID
     */
    public void setCartId(String cartId) {

        this.cartId = cartId;

    }



    /**
     * Returns the customer who owns this cart item.
     *
     * @return customer associated with the cart item
     */
    public Customer getCustomer() {

        return customer;

    }

    /**
     * Updates the customer associated with this cart item.
     *
     * @param customer customer to associate with the cart item
     */
    public void setCustomer(Customer customer) {

        this.customer = customer;

    }



    /**
     * Returns the product added to the cart.
     *
     * @return product associated with the cart item
     */
    public Product getProduct() {

        return product;

    }

    /**
     * Updates the product associated with this cart item.
     *
     * @param product product to add to the cart item
     */
    public void setProduct(Product product) {

        this.product = product;

    }



    /**
     * Returns the quantity of the selected product.
     *
     * @return product quantity
     */
    public int getQuantity() {

        return quantity;

    }

    /**
     * Updates the quantity of the selected product.
     *
     * @param quantity new product quantity
     */
    public void setQuantity(int quantity) {

        this.quantity = quantity;

    }



    /**
     * Returns the total price of this cart item.
     *
     * The total price is calculated by multiplying the selected
     * quantity by the current product price.
     *
     * When no product is associated with the cart item, zero is
     * returned because a product price cannot be calculated.
     *
     * @return total price of the cart item
     */
    public double getTotalPrice() {

        // A cart item without a product cannot have a calculated price.
        if (product == null) {

            return 0.0;

        }

        // Calculate the total price using quantity and product price.
        return quantity * product.getProductPrice();

    }

}