package com.crimsonlogic.ecommerce.service;

import com.crimsonlogic.ecommerce.model.Inventory;

import java.util.List;

public interface InventoryService {

    /**
     * Inserts Inventory.
     */
    void insertInventory(Inventory inventory);


    /**
     * Updates Inventory.
     */
    void updateInventory(Inventory inventory);


    /**
     * Deletes Inventory.
     */
    void deleteInventory(String inventoryId);


    /**
     * Finds Inventory by ID.
     */
    Inventory findInventoryById(String inventoryId);


    /**
     * Finds Inventory by Product ID.
     */
    Inventory findInventoryByProduct(String productId);


    /**
     * Returns all Inventory.
     */
    List<Inventory> findAllInventory();


    /**
     * Updates Product Quantity.
     */
    void updateQuantity(Inventory inventory);


    /**
     * Finds Inventory by Seller.
     */
    List<Inventory> findInventoryBySeller(
            String sellerId);
    
    Inventory findInventoryByIdAndSeller(
            String inventoryId,
            String sellerId);
}