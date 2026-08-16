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


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }


    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }


    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }


    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }


    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }
}