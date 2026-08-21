package com.crimsonlogic.ecommerce.model;

import com.crimsonlogic.ecommerce.enumeration.PaymentMethod;
import com.crimsonlogic.ecommerce.enumeration.PaymentStatus;

import java.time.LocalDateTime;

/**
 * Represents a payment made for an order in the ecommerce application.
 *
 * A Payment contains information about the transaction, customer,
 * associated order, payment method, payment status, amount, UPI details,
 * and the date and time when the payment was made.
 */
public class Payment {

    /**
     * Unique identifier of the payment.
     */
    private String paymentId;

    /**
     * Unique transaction identifier associated with the payment.
     */
    private String transactionId;

    /**
     * Customer who made the payment.
     */
    private Customer customer;

    /**
     * Order associated with the payment.
     */
    private Order order;

    /**
     * Payment method selected by the customer.
     */
    private PaymentMethod paymentMethod;

    /**
     * Current status of the payment.
     */
    private PaymentStatus paymentStatus;

    /**
     * Amount paid for the associated order.
     */
    private double amount;

    /**
     * UPI ID used for the payment.
     *
     * This value is applicable when the selected payment method
     * uses UPI.
     */
    private String upiId;

    /**
     * Date and time when the payment was made.
     */
    private LocalDateTime paymentDate;

    /**
     * Default constructor.
     *
     * Creates an empty Payment object that can be populated
     * using the setter methods.
     */
    public Payment() {
    }

    /**
     * Creates a Payment object using the supplied payment information.
     *
     * @param paymentId unique identifier of the payment
     * @param transactionId unique transaction identifier
     * @param customer customer who made the payment
     * @param order order associated with the payment
     * @param paymentMethod method used to make the payment
     * @param paymentStatus current status of the payment
     * @param amount amount paid for the order
     * @param upiId UPI ID used for the payment
     * @param paymentDate date and time when the payment was made
     */
    public Payment(
            String paymentId,
            String transactionId,
            Customer customer,
            Order order,
            PaymentMethod paymentMethod,
            PaymentStatus paymentStatus,
            double amount,
            String upiId,
            LocalDateTime paymentDate) {

        this.paymentId = paymentId;
        this.transactionId = transactionId;
        this.customer = customer;
        this.order = order;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.amount = amount;
        this.upiId = upiId;
        this.paymentDate = paymentDate;
    }

    /**
     * Returns the unique identifier of the payment.
     *
     * @return payment ID
     */
    public String getPaymentId() {
        return paymentId;
    }

    /**
     * Updates the unique identifier of the payment.
     *
     * @param paymentId new payment ID
     */
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * Returns the transaction identifier associated with the payment.
     *
     * @return transaction ID
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Updates the transaction identifier associated with the payment.
     *
     * @param transactionId new transaction ID
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Returns the customer who made the payment.
     *
     * @return customer associated with the payment
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Updates the customer associated with the payment.
     *
     * @param customer customer who made the payment
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Returns the order associated with the payment.
     *
     * @return associated order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Updates the order associated with the payment.
     *
     * @param order order to associate with the payment
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Returns the payment method selected for the transaction.
     *
     * @return payment method
     */
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Updates the payment method used for the transaction.
     *
     * @param paymentMethod new payment method
     */
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Returns the current status of the payment.
     *
     * @return payment status
     */
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * Updates the current status of the payment.
     *
     * @param paymentStatus new payment status
     */
    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    /**
     * Returns the amount paid for the order.
     *
     * @return payment amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Updates the amount paid for the order.
     *
     * @param amount new payment amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Returns the UPI ID associated with the payment.
     *
     * @return UPI ID
     */
    public String getUpiId() {
        return upiId;
    }

    /**
     * Updates the UPI ID associated with the payment.
     *
     * @param upiId new UPI ID
     */
    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }

    /**
     * Returns the date and time when the payment was made.
     *
     * @return payment date and time
     */
    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    /**
     * Updates the date and time when the payment was made.
     *
     * @param paymentDate new payment date and time
     */
    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
}