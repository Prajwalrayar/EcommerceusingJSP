package com.crimsonlogic.ecommerce.service;

import com.crimsonlogic.ecommerce.enumeration.ProductStatus;
import com.crimsonlogic.ecommerce.model.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductService {

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

    List<Product> findProductsBySeller(
            @Param("sellerId")
            String sellerId);

    List<Product> findProductsByCategory(
            @Param("categoryId")
            String categoryId);

    List<Product> findAvailableProducts();

    void updateProductStatus(Product product);

    Product findProductByIdAndSeller(
            @Param("productId")
            String productId,

            @Param("sellerId")
            String sellerId);

    List<Product> findProductsByStatus(
            ProductStatus productStatus);

    Double findAverageRating(
            @Param("productId")
            String productId);

    int countReviews(
            @Param("productId")
            String productId);
}