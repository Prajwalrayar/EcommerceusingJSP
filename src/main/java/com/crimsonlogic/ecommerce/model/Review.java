package com.crimsonlogic.ecommerce.model;

import java.time.LocalDateTime;

/**
 * Represents a product review submitted by a customer in the ecommerce application.
 *
 * A Review contains information about the order, product, and customer
 * associated with the review, along with the rating, review text,
 * and date and time when the review was submitted.
 */
public class Review {

    /**
     * Unique identifier of the review.
     */
    private String reviewId;

    /**
     * Order associated with the review.
     *
     * This identifies the order from which the customer reviewed
     * the purchased product.
     */
    private Order order;

    /**
     * Product being reviewed by the customer.
     */
    private Product product;

    /**
     * Customer who submitted the review.
     */
    private Customer customer;

    /**
     * Rating given by the customer for the product.
     */
    private int rating;

    /**
     * Textual feedback provided by the customer.
     */
    private String reviewText;

    /**
     * Date and time when the review was submitted.
     */
    private LocalDateTime reviewDate;



    /**
     * Default constructor.
     *
     * Creates an empty Review object that can be populated
     * using the setter methods.
     */
    public Review() {

    }



    /**
     * Creates a Review object using the supplied review information.
     *
     * @param reviewId unique identifier of the review
     * @param order order associated with the review
     * @param product product being reviewed
     * @param customer customer who submitted the review
     * @param rating rating given to the product
     * @param reviewText textual feedback provided by the customer
     * @param reviewDate date and time when the review was submitted
     */
    public Review(
            String reviewId,
            Order order,
            Product product,
            Customer customer,
            int rating,
            String reviewText,
            LocalDateTime reviewDate) {

        this.reviewId = reviewId;
        this.order = order;
        this.product = product;
        this.customer = customer;
        this.rating = rating;
        this.reviewText = reviewText;
        this.reviewDate = reviewDate;
    }



    /**
     * Returns the unique identifier of the review.
     *
     * @return review ID
     */
    public String getReviewId() {

        return reviewId;
    }

    /**
     * Updates the unique identifier of the review.
     *
     * @param reviewId new review ID
     */
    public void setReviewId(String reviewId) {

        this.reviewId = reviewId;
    }



    /**
     * Returns the order associated with the review.
     *
     * @return order associated with the review
     */
    public Order getOrder() {

        return order;
    }

    /**
     * Updates the order associated with the review.
     *
     * @param order order to associate with the review
     */
    public void setOrder(Order order) {

        this.order = order;
    }



    /**
     * Returns the product being reviewed.
     *
     * @return reviewed product
     */
    public Product getProduct() {

        return product;
    }

    /**
     * Updates the product being reviewed.
     *
     * @param product product to associate with the review
     */
    public void setProduct(Product product) {

        this.product = product;
    }



    /**
     * Returns the customer who submitted the review.
     *
     * @return customer who submitted the review
     */
    public Customer getCustomer() {

        return customer;
    }

    /**
     * Updates the customer associated with the review.
     *
     * @param customer customer who submitted the review
     */
    public void setCustomer(Customer customer) {

        this.customer = customer;
    }



    /**
     * Returns the rating given to the product.
     *
     * @return product rating
     */
    public int getRating() {

        return rating;
    }

    /**
     * Updates the rating given to the product.
     *
     * @param rating new product rating
     */
    public void setRating(int rating) {

        this.rating = rating;
    }



    /**
     * Returns the textual feedback provided by the customer.
     *
     * @return review text
     */
    public String getReviewText() {

        return reviewText;
    }

    /**
     * Updates the textual feedback provided by the customer.
     *
     * @param reviewText new review text
     */
    public void setReviewText(String reviewText) {

        this.reviewText = reviewText;
    }



    /**
     * Returns the date and time when the review was submitted.
     *
     * @return review date and time
     */
    public LocalDateTime getReviewDate() {

        return reviewDate;
    }

    /**
     * Updates the date and time when the review was submitted.
     *
     * @param reviewDate new review date and time
     */
    public void setReviewDate(LocalDateTime reviewDate) {

        this.reviewDate = reviewDate;
    }

}