package com.crimsonlogic.ecommerce.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.crimsonlogic.ecommerce.dao.ReviewMapper;
import com.crimsonlogic.ecommerce.exception.ValidationException;
import com.crimsonlogic.ecommerce.model.Review;
import com.crimsonlogic.ecommerce.service.ReviewService;
import com.crimsonlogic.ecommerce.util.IdGenerator;

public class ReviewServiceImpl implements ReviewService {

    private ReviewMapper reviewMapper;


    public void setReviewMapper(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }


    @Override
    public void insertReview(Review review) {

        if (review == null) {

            throw new ValidationException(
                    "Review information is required."
            );
        }

        if (review.getOrder() == null
                || review.getOrder().getOrderId() == null) {

            throw new ValidationException(
                    "Order is required."
            );
        }

        if (review.getProduct() == null
                || review.getProduct().getProductId() == null) {

            throw new ValidationException(
                    "Product is required."
            );
        }

        if (review.getCustomer() == null
                || review.getCustomer().getUserId() == null) {

            throw new ValidationException(
                    "Customer is required."
            );
        }

        if (review.getRating() < 1
                || review.getRating() > 5) {

            throw new ValidationException(
                    "Rating must be between 1 and 5."
            );
        }

        if (review.getReviewText() == null
                || review.getReviewText().trim().isEmpty()) {

            throw new ValidationException(
                    "Review text cannot be empty."
            );
        }

        if (review.getReviewId() == null
                || review.getReviewId().trim().isEmpty()) {

            review.setReviewId(
                    IdGenerator.generateId("REV")
            );
        }

        if (review.getReviewDate() == null) {

            review.setReviewDate(
                    LocalDateTime.now()
            );
        }

        reviewMapper.insertReview(review);
    }


    @Override
    public Review findReviewByCustomerAndOrder(
            String customerId,
            String orderId) {

        return reviewMapper.findReviewByCustomerAndOrder(
                customerId,
                orderId
        );
    }


    @Override
    public List<Review> findReviewsBySeller(
            String sellerId) {

        if (sellerId == null
                || sellerId.trim().isEmpty()) {

            throw new ValidationException(
                    "Seller ID is required."
            );
        }

        return reviewMapper.findReviewsBySeller(
                sellerId
        );
    }
}