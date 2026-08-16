package com.crimsonlogic.ecommerce.service;

import com.crimsonlogic.ecommerce.enumeration.PaymentStatus;
import com.crimsonlogic.ecommerce.model.Payment;

import java.util.List;

public interface PaymentService {

    void insertPayment(Payment payment);

    void updatePayment(Payment payment);

    void deletePayment(String paymentId);

    Payment findPaymentById(String paymentId);

    Payment findPaymentByUtr(String transactionId);

    List<Payment> findPaymentsByCustomer(String customerId);

    List<Payment> findPaymentsBySeller(String sellerId);

    List<Payment> findAllPayments();

    void updatePaymentStatus(Payment payment);

    Payment findPaymentByOrder(String orderId);

    List<Payment> findPaymentsByCustomerAndKeyword(
            String customerId,
            String keyword
    );

    List<Payment> findPaymentsBySellerAndKeyword(
            String sellerId,
            String keyword
    );

    List<Payment> findPaymentsByStatus(
            PaymentStatus paymentStatus
    );

    List<Payment> findPaymentsByKeyword(
            String keyword
    );
}