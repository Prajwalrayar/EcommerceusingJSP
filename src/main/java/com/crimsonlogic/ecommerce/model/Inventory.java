package com.crimsonlogic.ecommerce.model;

/**
 * Represents inventory information for a product in the ecommerce application.
 *
 * An Inventory object associates a product with its available quantity
 * and maintains a unique identifier for the inventory record.
 */
public class Inventory {

    /**
     * Unique identifier of the inventory record.
     */
    private String inventoryId;

    /**
     * Product associated with this inventory record.
     */
    private Product product;

    /**
     * Quantity of the product currently available in inventory.
     */
    private int quantity;



    /**
     * Default constructor.
     *
     * Creates an empty Inventory object that can be populated
     * using the setter methods.
     */
    public Inventory() {

    }



    /**
     * Creates an Inventory object using the supplied inventory information.
     *
     * @param inventoryId unique identifier of the inventory record
     * @param product product associated with the inventory
     * @param quantity available quantity of the product
     */
    public Inventory(
            String inventoryId,
            Product product,
            int quantity) {

        this.inventoryId = inventoryId;
        this.product = product;
        this.quantity = quantity;

    }



    /**
     * Returns the unique identifier of the inventory record.
     *
     * @return inventory ID
     */
    public String getInventoryId() {

        return inventoryId;

    }

    /**
     * Updates the unique identifier of the inventory record.
     *
     * @param inventoryId new inventory ID
     */
    public void setInventoryId(String inventoryId) {

        this.inventoryId = inventoryId;

    }



    /**
     * Returns the product associated with this inventory record.
     *
     * @return inventory product
     */
    public Product getProduct() {

        return product;

    }

    /**
     * Updates the product associated with this inventory record.
     *
     * @param product product to associate with the inventory
     */
    public void setProduct(Product product) {

        this.product = product;

    }



    /**
     * Returns the currently available quantity of the product.
     *
     * @return available product quantity
     */
    public int getQuantity() {

        return quantity;

    }

    /**
     * Updates the available quantity of the product.
     *
     * @param quantity new available product quantity
     */
    public void setQuantity(int quantity) {

        this.quantity = quantity;

    }

}