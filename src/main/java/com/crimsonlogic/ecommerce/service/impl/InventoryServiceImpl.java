package com.crimsonlogic.ecommerce.service.impl;

import com.crimsonlogic.ecommerce.dao.InventoryMapper;
import com.crimsonlogic.ecommerce.dao.ProductMapper;
import com.crimsonlogic.ecommerce.enumeration.ProductStatus;
import com.crimsonlogic.ecommerce.exception.ValidationException;
import com.crimsonlogic.ecommerce.model.Inventory;
import com.crimsonlogic.ecommerce.model.Product;
import com.crimsonlogic.ecommerce.service.InventoryService;
import com.crimsonlogic.ecommerce.util.ValidationUtil;

import java.util.List;

public class InventoryServiceImpl implements InventoryService {

    private InventoryMapper inventoryMapper;
    private ProductMapper productMapper;

    public void setInventoryMapper(
            InventoryMapper inventoryMapper,
            ProductMapper productMapper) {

        this.inventoryMapper = inventoryMapper;
        this.productMapper = productMapper;
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

        if (inventory == null) {

            throw new ValidationException(
                    "Inventory information is required."
            );
        }

        ValidationUtil.validateStockQuantity(
                inventory.getQuantity()
        );

        Inventory existing =
                inventoryMapper.findInventoryById(
                        inventory.getInventoryId()
                );

        if (existing == null) {

            throw new ValidationException(
                    "Inventory not found."
            );
        }

        inventoryMapper.updateQuantity(
                inventory
        );

        Product product =
                existing.getProduct();

        if (product != null) {

            if (inventory.getQuantity() > 0) {

                product.setProductStatus(
                        ProductStatus.AVAILABLE
                );

            } else {

                product.setProductStatus(
                        ProductStatus.OUT_OF_STOCK
                );
            }

            productMapper.updateProductStatus(
                    product
            );
        }
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