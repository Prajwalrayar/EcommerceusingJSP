package com.crimsonlogic.ecommerce.dao;

import com.crimsonlogic.ecommerce.enumeration.ProductStatus;
import com.crimsonlogic.ecommerce.model.Product;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {

    // ==========================================================
    // BASIC CRUD
    // ==========================================================

    void insertProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(
            @Param("productId")
            String productId);


    Product findProductById(
            @Param("productId")
            String productId);


    Product findProductByName(
            @Param("productName")
            String productName);


    List<Product> findAllProducts();

    
    List<Product> searchAvailableProducts(
            @Param("keyword")
            String keyword,

            @Param("categoryId")
            String categoryId,

            @Param("sellerId")
            String sellerId,

            @Param("minPrice")
            Double minPrice,

            @Param("maxPrice")
            Double maxPrice);
    
    List<Product> findCustomerAvailableProducts();

    // ==========================================================
    // SELLER PRODUCTS
    // ==========================================================

    List<Product> findProductsBySeller(
            @Param("sellerId")
            String sellerId);


    Product findProductByIdAndSeller(
            @Param("productId")
            String productId,

            @Param("sellerId")
            String sellerId);


    // ==========================================================
    // PRODUCTS CREATED BY USER
    // ==========================================================

    List<Product> findProductsByCreator(
            @Param("createdBy")
            String createdBy);


    Product findProductByIdAndCreator(
            @Param("productId")
            String productId,

            @Param("createdBy")
            String createdBy);


    // ==========================================================
    // DELETE WITH OWNERSHIP
    // ==========================================================

    void deleteProductByCreator(
            @Param("productId")
            String productId,

            @Param("createdBy")
            String createdBy);


    // ==========================================================
    // UPDATE WITH OWNERSHIP
    // ==========================================================

    void updateProductByCreator(
            Product product,

            @Param("createdBy")
            String createdBy);


    // ==========================================================
    // CATEGORY
    // ==========================================================

    List<Product> findProductsByCategory(
            @Param("categoryId")
            String categoryId);


    // ==========================================================
    // STATUS
    // ==========================================================

    List<Product> findAvailableProducts();

    void updateProductStatus(Product product);

    List<Product> findProductsByStatus(
            @Param("productStatus")
            ProductStatus productStatus);


    // ==========================================================
    // RATINGS
    // ==========================================================

    Double findAverageRating(
            @Param("productId")
            String productId);


    int countReviews(
            @Param("productId")
            String productId);
    
    
}