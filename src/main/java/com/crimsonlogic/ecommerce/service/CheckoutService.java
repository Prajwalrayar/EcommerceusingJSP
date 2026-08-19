package com.crimsonlogic.ecommerce.service;

/**
 * Service responsible for coordinating the checkout process.
 *
 * Checkout business logic is handled in the service layer
 * instead of the controller.
 */
public interface CheckoutService {

    /**
     * Places an order for the customer using the selected
     * delivery address and payment method.
     *
     * @param customerId customer ID
     * @param addressId selected delivery address ID
     * @param paymentMethod selected payment method
     * @param upiId UPI ID when UPI is selected
     */
    void placeOrder(
            String customerId,
            String addressId,
            String paymentMethod,
            String upiId
    );
}