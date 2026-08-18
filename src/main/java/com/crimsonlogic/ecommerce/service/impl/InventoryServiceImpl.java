package com.crimsonlogic.ecommerce.service.impl;

import com.crimsonlogic.ecommerce.dao.InventoryMapper;
import com.crimsonlogic.ecommerce.model.Inventory;
import com.crimsonlogic.ecommerce.service.InventoryService;

import java.util.List;

public class InventoryServiceImpl implements InventoryService {

    private InventoryMapper inventoryMapper;


    public void setInventoryMapper(
            InventoryMapper inventoryMapper) {

        this.inventoryMapper = inventoryMapper;
    }


    @Override
    public void insertInventory(
            Inventory inventory) {

        inventoryMapper.insertInventory(inventory);
    }


    @Override
    public void updateInventory(
            Inventory inventory) {

        inventoryMapper.updateInventory(inventory);
    }


    @Override
    public void deleteInventory(
            String inventoryId) {

        inventoryMapper.deleteInventory(inventoryId);
    }


    @Override
    public Inventory findInventoryById(
            String inventoryId) {

        return inventoryMapper.findInventoryById(
                inventoryId
        );
    }


    @Override
    public Inventory findInventoryByProduct(
            String productId) {

        return inventoryMapper.findInventoryByProduct(
                productId
        );
    }


    @Override
    public List<Inventory> findAllInventory() {

        return inventoryMapper.findAllInventory();
    }


    @Override
    public void updateQuantity(
            Inventory inventory) {

        inventoryMapper.updateQuantity(inventory);
    }


    @Override
    public List<Inventory> findInventoryBySeller(
            String sellerId) {

        return inventoryMapper.findInventoryBySeller(
                sellerId
        );
    }


    @Override
    public Inventory findInventoryByIdAndSeller(
            String inventoryId,
            String sellerId) {

        return inventoryMapper.findInventoryByIdAndSeller(
                inventoryId,
                sellerId
        );
    }
}