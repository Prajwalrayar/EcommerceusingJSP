package com.crimsonlogic.ecommerce.service;

import com.crimsonlogic.ecommerce.model.Review;
import org.apache.ibatis.annotations.Param;

public interface ReviewService {

    /**
     * Inserts Review.
     *
     * @param review Review
     */
    void insertReview(Review review);


    /**
     * Finds review by customer and order.
     *
     * @param customerId Customer ID
     * @param orderId Order ID
     * @return Review
     */
    Review findReviewByCustomerAndOrder(
            @Param("customerId")
            String customerId,

            @Param("orderId")
            String orderId
    );
}