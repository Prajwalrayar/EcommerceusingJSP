package com.crimsonlogic.ecommerce.dao;

import com.crimsonlogic.ecommerce.model.Inventory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InventoryMapper {

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
    void deleteInventory(
            @Param("inventoryId")
            String inventoryId);


    /**
     * Finds Inventory by ID.
     */
    Inventory findInventoryById(
            @Param("inventoryId")
            String inventoryId);


    /**
     * Finds Inventory by ID belonging to a Seller.
     */
    Inventory findInventoryByIdAndSeller(
            @Param("inventoryId")
            String inventoryId,

            @Param("sellerId")
            String sellerId);


    /**
     * Finds Inventory by Product ID.
     */
    Inventory findInventoryByProduct(
            @Param("productId")
            String productId);


    /**
     * Returns all Inventory.
     */
    List<Inventory> findAllInventory();


    /**
     * Updates Product Quantity.
     */
    void updateQuantity(Inventory inventory);


    /**
     * Finds Inventory belonging to a Seller.
     */
    List<Inventory> findInventoryBySeller(
            @Param("sellerId")
            String sellerId);
    
}