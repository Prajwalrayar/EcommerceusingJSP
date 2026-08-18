package com.crimsonlogic.ecommerce.model;

import com.crimsonlogic.ecommerce.enumeration.ProductStatus;

public class Product {

    private String productId;

    private String productName;

    private String brand;

    private String productDescription;

    private double productPrice;

    private double rating;

    private int reviewCount;

    private Category category;

    private Seller seller;

    private ProductStatus productStatus;

    /*
     * ID of the user who created this product.
     *
     * ADMIN  -> ADM001
     * SELLER -> SEL001
     */
    private String createdBy;
    
    private String userId;
    
    private int initialStock;

    
    // ==========================================================
    // CONSTRUCTORS
    // ==========================================================

    public Product() {
    }


    public Product(
            String productId,
            String productName,
            String brand,
            String productDescription,
            double productPrice,
            Category category,
            Seller seller,
            ProductStatus productStatus) {

        this.productId = productId;
        this.productName = productName;
        this.brand = brand;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.category = category;
        this.seller = seller;
        this.productStatus = productStatus;
    }


    // ==========================================================
    // PRODUCT ID
    // ==========================================================

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }


    // ==========================================================
    // PRODUCT NAME
    // ==========================================================

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    // ==========================================================
    // BRAND
    // ==========================================================

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


    // ==========================================================
    // DESCRIPTION
    // ==========================================================

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(
            String productDescription) {

        this.productDescription =
                productDescription;
    }


    // ==========================================================
    // PRICE
    // ==========================================================

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(
            double productPrice) {

        this.productPrice = productPrice;
    }


    
    // ==========================================================
    // RATING
    // ==========================================================

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }


    // ==========================================================
    // REVIEW COUNT
    // ==========================================================

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(
            int reviewCount) {

        this.reviewCount = reviewCount;
    }


    // ==========================================================
    // CATEGORY
    // ==========================================================

    public Category getCategory() {
        return category;
    }

    public void setCategory(
            Category category) {

        this.category = category;
    }


    // ==========================================================
    // SELLER
    // ==========================================================

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(
            Seller seller) {

        this.seller = seller;
    }


    // ==========================================================
    // PRODUCT STATUS
    // ==========================================================

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(
            ProductStatus productStatus) {

        this.productStatus = productStatus;
    }


    // ==========================================================
    // CREATED BY
    // ==========================================================

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(
            String createdBy) {

        this.createdBy = createdBy;
    }
    
 // ==========================================================
 // USER ID
 // ==========================================================

 public String getUserId() {
     return userId;
 }

 public void setUserId(String userId) {
     this.userId = userId;
 }

 public int getInitialStock() {
     return initialStock;
 }

 public void setInitialStock(int initialStock) {
     this.initialStock = initialStock;
 }
}