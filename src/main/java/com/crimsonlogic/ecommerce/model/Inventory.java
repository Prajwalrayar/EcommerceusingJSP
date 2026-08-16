package com.crimsonlogic.ecommerce.model;

public class Inventory {

    /**
     * Inventory ID.
     */
    private String inventoryId;

    /**
     * Product.
     */
    private Product product;

    /**
     * Available Quantity.
     */
    private int quantity;


    /**
     * Default Constructor.
     */
    public Inventory() {
    }


    /**
     * Parameterized Constructor.
     */
    public Inventory(
            String inventoryId,
            Product product,
            int quantity) {

        this.inventoryId = inventoryId;
        this.product = product;
        this.quantity = quantity;
    }


    public String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
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
}