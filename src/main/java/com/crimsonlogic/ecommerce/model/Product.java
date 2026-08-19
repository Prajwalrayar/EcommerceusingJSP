package com.crimsonlogic.ecommerce.model;

import com.crimsonlogic.ecommerce.enumeration.ProductStatus;

/**
 * Represents a product in the ecommerce application.
 *
 * A Product contains the product's basic information, pricing,
 * rating, category, seller, status, and information about the
 * user who created the product.
 */
public class Product {

    /**
     * Unique identifier of the product.
     */
    private String productId;

    /**
     * Name of the product.
     */
    private String productName;

    /**
     * Brand associated with the product.
     */
    private String brand;

    /**
     * Description providing additional information about the product.
     */
    private String productDescription;

    /**
     * Selling price of the product.
     */
    private double productPrice;

    /**
     * Average rating given to the product.
     */
    private double rating;

    /**
     * Number of reviews submitted for the product.
     */
    private int reviewCount;

    /**
     * Category to which the product belongs.
     */
    private Category category;

    /**
     * Seller associated with the product.
     *
     * This value identifies the seller responsible for the product
     * when the product is created or managed by a seller.
     */
    private Seller seller;

    /**
     * Current status of the product.
     */
    private ProductStatus productStatus;

    /*
     * ID of the user who created this product.
     *
     * ADMIN  -> ADM001
     * SELLER -> SEL001
     */
    private String createdBy;

    /**
     * ID of the user associated with the product.
     *
     * This value can be used to identify the user responsible
     * for the product operation.
     */
    private String userId;

    /**
     * Initial stock quantity specified when the product is created.
     */
    private int initialStock;



    // ==========================================================
    // CONSTRUCTORS
    // ==========================================================

    /**
     * Default constructor.
     *
     * Creates an empty Product object that can be populated
     * using the setter methods.
     */
    public Product() {

    }



    /**
     * Creates a Product object using the supplied product information.
     *
     * @param productId unique identifier of the product
     * @param productName name of the product
     * @param brand brand associated with the product
     * @param productDescription description of the product
     * @param productPrice selling price of the product
     * @param category category to which the product belongs
     * @param seller seller associated with the product
     * @param productStatus current status of the product
     */
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

    /**
     * Returns the unique identifier of the product.
     *
     * @return product ID
     */
    public String getProductId() {

        return productId;

    }

    /**
     * Updates the unique identifier of the product.
     *
     * @param productId new product ID
     */
    public void setProductId(String productId) {

        this.productId = productId;

    }



    // ==========================================================
    // PRODUCT NAME
    // ==========================================================

    /**
     * Returns the name of the product.
     *
     * @return product name
     */
    public String getProductName() {

        return productName;

    }

    /**
     * Updates the name of the product.
     *
     * @param productName new product name
     */
    public void setProductName(String productName) {

        this.productName = productName;

    }



    // ==========================================================
    // BRAND
    // ==========================================================

    /**
     * Returns the brand associated with the product.
     *
     * @return product brand
     */
    public String getBrand() {

        return brand;

    }

    /**
     * Updates the brand associated with the product.
     *
     * @param brand new product brand
     */
    public void setBrand(String brand) {

        this.brand = brand;

    }



    // ==========================================================
    // DESCRIPTION
    // ==========================================================

    /**
     * Returns the description of the product.
     *
     * @return product description
     */
    public String getProductDescription() {

        return productDescription;

    }

    /**
     * Updates the description of the product.
     *
     * @param productDescription new product description
     */
    public void setProductDescription(
            String productDescription) {

        this.productDescription =
                productDescription;

    }



    // ==========================================================
    // PRICE
    // ==========================================================

    /**
     * Returns the selling price of the product.
     *
     * @return product price
     */
    public double getProductPrice() {

        return productPrice;

    }

    /**
     * Updates the selling price of the product.
     *
     * @param productPrice new product price
     */
    public void setProductPrice(
            double productPrice) {

        this.productPrice = productPrice;

    }





    // ==========================================================
    // RATING
    // ==========================================================

    /**
     * Returns the average rating of the product.
     *
     * @return product rating
     */
    public double getRating() {

        return rating;

    }

    /**
     * Updates the average rating of the product.
     *
     * @param rating new product rating
     */
    public void setRating(double rating) {

        this.rating = rating;

    }



    // ==========================================================
    // REVIEW COUNT
    // ==========================================================

    /**
     * Returns the number of reviews submitted for the product.
     *
     * @return product review count
     */
    public int getReviewCount() {

        return reviewCount;

    }

    /**
     * Updates the number of reviews submitted for the product.
     *
     * @param reviewCount new review count
     */
    public void setReviewCount(
            int reviewCount) {

        this.reviewCount = reviewCount;

    }



    // ==========================================================
    // CATEGORY
    // ==========================================================

    /**
     * Returns the category associated with the product.
     *
     * @return product category
     */
    public Category getCategory() {

        return category;

    }

    /**
     * Updates the category associated with the product.
     *
     * @param category new product category
     */
    public void setCategory(
            Category category) {

        this.category = category;

    }



    // ==========================================================
    // SELLER
    // ==========================================================

    /**
     * Returns the seller associated with the product.
     *
     * @return product seller
     */
    public Seller getSeller() {

        return seller;

    }

    /**
     * Updates the seller associated with the product.
     *
     * @param seller new product seller
     */
    public void setSeller(
            Seller seller) {

        this.seller = seller;

    }



    // ==========================================================
    // PRODUCT STATUS
    // ==========================================================

    /**
     * Returns the current status of the product.
     *
     * @return product status
     */
    public ProductStatus getProductStatus() {

        return productStatus;

    }

    /**
     * Updates the current status of the product.
     *
     * @param productStatus new product status
     */
    public void setProductStatus(
            ProductStatus productStatus) {

        this.productStatus = productStatus;

    }



    // ==========================================================
    // CREATED BY
    // ==========================================================

    /**
     * Returns the ID of the user who created the product.
     *
     * @return creator user ID
     */
    public String getCreatedBy() {

        return createdBy;

    }

    /**
     * Updates the ID of the user who created the product.
     *
     * @param createdBy ID of the user who created the product
     */
    public void setCreatedBy(
            String createdBy) {

        this.createdBy = createdBy;

    }

    // ==========================================================
    // USER ID
    // ==========================================================

    /**
     * Returns the user ID associated with the product.
     *
     * @return user ID
     */
    public String getUserId() {

        return userId;

    }

    /**
     * Updates the user ID associated with the product.
     *
     * @param userId new user ID
     */
    public void setUserId(String userId) {

        this.userId = userId;

    }

    /**
     * Returns the initial stock quantity of the product.
     *
     * @return initial stock quantity
     */
    public int getInitialStock() {

        return initialStock;

    }

    /**
     * Updates the initial stock quantity of the product.
     *
     * @param initialStock new initial stock quantity
     */
    public void setInitialStock(int initialStock) {

        this.initialStock = initialStock;

    }

}