package com.crimsonlogic.ecommerce.service.impl;

import com.crimsonlogic.ecommerce.dao.ReviewMapper;
import com.crimsonlogic.ecommerce.model.Review;
import com.crimsonlogic.ecommerce.service.ReviewService;

public class ReviewServiceImpl implements ReviewService {

    private ReviewMapper reviewMapper;


    public void setReviewMapper(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }


    @Override
    public void insertReview(Review review) {

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
}