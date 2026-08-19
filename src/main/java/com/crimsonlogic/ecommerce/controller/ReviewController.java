package com.crimsonlogic.ecommerce.controller;

import com.crimsonlogic.ecommerce.model.Order;
import com.crimsonlogic.ecommerce.model.Review;
import com.crimsonlogic.ecommerce.model.report.ReportFilter;
import com.crimsonlogic.ecommerce.model.report.SalesReport;
import com.crimsonlogic.ecommerce.service.OrderService;
import com.crimsonlogic.ecommerce.service.ReportService;
import com.crimsonlogic.ecommerce.service.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class ReviewController {

    private ReviewService reviewService;
    private OrderService orderService;
    private ReportService reportService;


    // ==========================================================
    // Setter Injection
    // ==========================================================

    @Autowired
    public void setReviewService(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
    
    @Autowired
    public void setReportService(ReportService reportService) {
        this.reportService = reportService;
    }


    // ==========================================================
    // Show Review Form
    // ==========================================================

    @GetMapping("/customer/review/{orderId}")
    public String showReviewForm(
            @PathVariable("orderId")
            String orderId,

            @RequestParam("customerId")
            String customerId,

            Model model) {


        // ------------------------------------------------------
        // Find Order belonging to Customer
        // ------------------------------------------------------

        Order order =
                orderService.findOrderByIdAndCustomer(
                        orderId,
                        customerId
                );


        if (order == null) {

            model.addAttribute(
                    "error",
                    "Order not found or does not belong to this customer."
            );

            return "review/review-error";
        }


        // ------------------------------------------------------
        // Check whether review already exists
        // ------------------------------------------------------

        Review existingReview =
                reviewService.findReviewByCustomerAndOrder(
                        customerId,
                        orderId
                );


        if (existingReview != null) {

            model.addAttribute(
                    "review",
                    existingReview
            );

            return "review/review-details";
        }


        // ------------------------------------------------------
        // Send Order to JSP
        // ------------------------------------------------------

        model.addAttribute(
                "order",
                order
        );

        model.addAttribute(
                "customerId",
                customerId
        );


        return "/customer/review/review-form";
    }


    // ==========================================================
    // Submit Review
    // ==========================================================

    @PostMapping("/customer/review/{orderId}")
    public String submitReview(

            @PathVariable("orderId")
            String orderId,

            @RequestParam("customerId")
            String customerId,

            @RequestParam("rating")
            int rating,

            @RequestParam("reviewText")
            String reviewText,

            Model model) {


        // ------------------------------------------------------
        // Validate Rating
        // ------------------------------------------------------

        if (rating < 1 || rating > 5) {

            model.addAttribute(
                    "error",
                    "Rating must be between 1 and 5."
            );

            return showReviewForm(
                    orderId,
                    customerId,
                    model
            );
        }


        // ------------------------------------------------------
        // Validate Review Text
        // ------------------------------------------------------

        if (reviewText == null ||
                reviewText.trim().isEmpty()) {

            model.addAttribute(
                    "error",
                    "Review text cannot be empty."
            );

            return showReviewForm(
                    orderId,
                    customerId,
                    model
            );
        }


        // ------------------------------------------------------
        // Verify Order belongs to Customer
        // ------------------------------------------------------

        Order order =
                orderService.findOrderByIdAndCustomer(
                        orderId,
                        customerId
                );


        if (order == null) {

            model.addAttribute(
                    "error",
                    "Order not found or access denied."
            );

            return "review/review-error";
        }


        // ------------------------------------------------------
        // Prevent Duplicate Review
        // ------------------------------------------------------

        Review existingReview =
                reviewService.findReviewByCustomerAndOrder(
                        customerId,
                        orderId
                );


        if (existingReview != null) {

            model.addAttribute(
                    "review",
                    existingReview
            );

            return "review/review-details";
        }


        // ------------------------------------------------------
        // Create Review
        // ------------------------------------------------------

        Review review = new Review();


        review.setReviewId(
                "REV-" +
                        UUID.randomUUID()
                                .toString()
                                .substring(0, 8)
                                .toUpperCase()
        );


        review.setOrder(order);

        review.setProduct(
                order.getProduct()
        );

        review.setCustomer(
                order.getCustomer()
        );

        review.setRating(rating);

        review.setReviewText(
                reviewText.trim()
        );

        review.setReviewDate(
                LocalDateTime.now()
        );


        // ------------------------------------------------------
        // Save Review
        // ------------------------------------------------------

        reviewService.insertReview(review);


        // ------------------------------------------------------
        // Redirect to Review Details
        // ------------------------------------------------------

        return "redirect:/customer/review/"
                + orderId
                + "?customerId="
                + customerId;
    }
    
	 // ==========================================================
	 // SELLER - VIEW REVIEWS
	 // ==========================================================
	
	 @GetMapping("/seller/reviews/{sellerId}")
	 public String sellerReviews(
	         @PathVariable("sellerId")
	         String sellerId,
	
	         Model model) {
	
	
		 
	     List<Review> reviews =
	             reviewService.findReviewsBySeller(
	                     sellerId
	             );
	
	
	     model.addAttribute(
	             "reviews",
	             reviews
	     );
	
	     model.addAttribute(
	             "sellerId",
	             sellerId
	     );
	
	
	     return "seller/reviews";
	 }
	 
	 @Controller
	 @RequestMapping("/reports")
	 public class ReportController {

	     @GetMapping("/filter")
	     public String filterReport(
	             @ModelAttribute ReportFilter filter,
	             Model model) {

	         SalesReport report =
	                 reportService.getSalesReport(filter);

	         model.addAttribute("salesReport", report);
	         model.addAttribute("filter", filter);

	         return "seller/sales-report";
	     }
	 }
}