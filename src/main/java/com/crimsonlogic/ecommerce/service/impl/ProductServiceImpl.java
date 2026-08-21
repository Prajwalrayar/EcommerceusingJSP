package com.crimsonlogic.ecommerce.service.impl;

import com.crimsonlogic.ecommerce.dao.ProductMapper;
import com.crimsonlogic.ecommerce.enumeration.ProductStatus;
import com.crimsonlogic.ecommerce.model.Inventory;
import com.crimsonlogic.ecommerce.model.Product;
import com.crimsonlogic.ecommerce.service.InventoryService;
import com.crimsonlogic.ecommerce.service.ProductService;
import com.crimsonlogic.ecommerce.util.IdGenerator;
import com.crimsonlogic.ecommerce.util.ValidationUtil;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductMapper productMapper;
    private InventoryService inventoryService;

    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }


    public void setInventoryService(
            InventoryService inventoryService) {

        this.inventoryService = inventoryService;
    }


    @Override
    public void insertProduct(Product product) {

        // ----------------------------------------------------------
        // Generate Product ID automatically
        // ----------------------------------------------------------

        if (product.getProductId() == null
                || product.getProductId().trim().isEmpty()) {

            product.setProductId(
                    IdGenerator.generateId("PRO")
            );
        }


        // ----------------------------------------------------------
        // Validate Product
        // ----------------------------------------------------------

        ValidationUtil.validateProductName(
                product.getProductName()
        );

        ValidationUtil.validateField(
                product.getBrand(),
                "Brand"
        );

        ValidationUtil.validateProductDescription(
                product.getProductDescription()
        );

        ValidationUtil.validateProductPrice(
                product.getProductPrice()
        );


        if (product.getCategory() == null) {

            throw new com.crimsonlogic.ecommerce.exception.ValidationException(
                    "Category must be selected."
            );
        }


        // ----------------------------------------------------------
        // Validate Initial Quantity
        // ----------------------------------------------------------

        if (product.getInitialStock() < 0) {

            throw new com.crimsonlogic.ecommerce.exception.ValidationException(
                    "Quantity cannot be negative."
            );
        }


        // ----------------------------------------------------------
        // Set Product Status Based On Quantity
        // ----------------------------------------------------------

        if (product.getInitialStock() > 0) {

            product.setProductStatus(
                    ProductStatus.AVAILABLE
            );

        } else {

            product.setProductStatus(
                    ProductStatus.OUT_OF_STOCK
            );
        }


        // ----------------------------------------------------------
        // Insert Product
        // ----------------------------------------------------------

        productMapper.insertProduct(product);


        // ----------------------------------------------------------
        // Create Inventory
        // ----------------------------------------------------------

        Inventory inventory = new Inventory();

        inventory.setInventoryId(
                IdGenerator.generateId("INV")
        );

        inventory.setProduct(
                product
        );

        inventory.setQuantity(
                product.getInitialStock()
        );

        inventoryService.insertInventory(
                inventory
        );
    }


    @Override
    public void updateProduct(Product product) {

        // ----------------------------------------------------------
        // Validate Product ID
        // ----------------------------------------------------------

        if (product == null
                || product.getProductId() == null
                || product.getProductId().trim().isEmpty()) {

            throw new com.crimsonlogic.ecommerce.exception.ValidationException(
                    "Product ID is required."
            );
        }


        // ----------------------------------------------------------
        // Find Existing Product
        // ----------------------------------------------------------

        Product existingProduct =
                productMapper.findProductById(
                        product.getProductId()
                );


        if (existingProduct == null) {

            throw new com.crimsonlogic.ecommerce.exception.ValidationException(
                    "Product not found."
            );
        }


        // ----------------------------------------------------------
        // Validate Product
        // ----------------------------------------------------------

        ValidationUtil.validateProductName(
                product.getProductName()
        );

        ValidationUtil.validateField(
                product.getBrand(),
                "Brand"
        );

        ValidationUtil.validateProductDescription(
                product.getProductDescription()
        );

        ValidationUtil.validateProductPrice(
                product.getProductPrice()
        );


        if (product.getCategory() == null) {

            throw new com.crimsonlogic.ecommerce.exception.ValidationException(
                    "Category must be selected."
            );
        }


        // ----------------------------------------------------------
        // Preserve Existing Status
        // ----------------------------------------------------------

        product.setProductStatus(
                existingProduct.getProductStatus()
        );


        // ----------------------------------------------------------
        // Preserve Seller / Creator Information
        // ----------------------------------------------------------

        product.setCreatedBy(
                existingProduct.getCreatedBy()
        );

        product.setUserId(
                existingProduct.getUserId()
        );

        product.setSeller(
                existingProduct.getSeller()
        );


        // ----------------------------------------------------------
        // Update Product
        // ----------------------------------------------------------

        productMapper.updateProduct(product);
    }


    @Override
    public void deleteProduct(String productId) {

        productMapper.deleteProduct(productId);
    }


    @Override
    public Product findProductById(String productId) {

        return productMapper.findProductById(productId);
    }


    @Override
    public Product findProductByName(String productName) {

        return productMapper.findProductByName(productName);
    }


    @Override
    public List<Product> findAllProducts() {

        return productMapper.findAllProducts();
    }


    @Override
    public List<Product> findProductsBySeller(String sellerId) {

        return productMapper.findProductsBySeller(sellerId);
    }


    @Override
    public List<Product> findProductsByCategory(String categoryId) {

        return productMapper.findProductsByCategory(categoryId);
    }


    @Override
    public List<Product> findAvailableProducts() {

        return productMapper.findAvailableProducts();
    }

    
    @Override
    public List<Product> searchAvailableProducts(
            String keyword,
            String categoryId,
            String sellerId,
            Double minPrice,
            Double maxPrice) {

        // ----------------------------------------------------------
        // Normalize optional text filters
        // ----------------------------------------------------------

        if (keyword != null) {
            keyword = keyword.trim();

            if (keyword.isEmpty()) {
                keyword = null;
            }
        }

        if (categoryId != null) {
            categoryId = categoryId.trim();

            if (categoryId.isEmpty()) {
                categoryId = null;
            }
        }

        if (sellerId != null) {
            sellerId = sellerId.trim();

            if (sellerId.isEmpty()) {
                sellerId = null;
            }
        }


        // ----------------------------------------------------------
        // Validate minimum price
        // ----------------------------------------------------------

        if (minPrice != null && minPrice < 0) {

            throw new com.crimsonlogic.ecommerce.exception.ValidationException(
                    "Minimum price cannot be negative."
            );
        }


        // ----------------------------------------------------------
        // Validate maximum price
        // ----------------------------------------------------------

        if (maxPrice != null && maxPrice < 0) {

            throw new com.crimsonlogic.ecommerce.exception.ValidationException(
                    "Maximum price cannot be negative."
            );
        }


        // ----------------------------------------------------------
        // Validate price range
        // ----------------------------------------------------------

        if (minPrice != null
                && maxPrice != null
                && minPrice > maxPrice) {

            throw new com.crimsonlogic.ecommerce.exception.ValidationException(
                    "Minimum price cannot be greater than maximum price."
            );
        }


        // ----------------------------------------------------------
        // Database-level filtering
        // ----------------------------------------------------------

        return productMapper.searchAvailableProducts(
                keyword,
                categoryId,
                sellerId,
                minPrice,
                maxPrice
        );
    }

    @Override
    public void updateProductStatus(Product product) {

        productMapper.updateProductStatus(product);
    }


    @Override
    public Product findProductByIdAndSeller(
            String productId,
            String sellerId) {

        return productMapper.findProductByIdAndSeller(
                productId,
                sellerId
        );
    }


    @Override
    public List<Product> findProductsByStatus(
            ProductStatus productStatus) {

        return productMapper.findProductsByStatus(
                productStatus
        );
    }


    @Override
    public Double findAverageRating(String productId) {

        return productMapper.findAverageRating(productId);
    }


    @Override
    public int countReviews(String productId) {

        return productMapper.countReviews(productId);
    }
    
    @Override
    public List<Product> findCustomerAvailableProducts() {

        return productMapper.findCustomerAvailableProducts();
    }
}