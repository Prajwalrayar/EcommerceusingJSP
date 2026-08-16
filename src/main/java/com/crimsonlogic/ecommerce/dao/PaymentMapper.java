package com.crimsonlogic.ecommerce.dao;

import com.crimsonlogic.ecommerce.enumeration.PaymentStatus;
import com.crimsonlogic.ecommerce.model.Payment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaymentMapper {

    void insertPayment(Payment payment);

    void updatePayment(Payment payment);

    void deletePayment(
            @Param("paymentId")
            String paymentId);

    Payment findPaymentById(
            @Param("paymentId")
            String paymentId);

    Payment findPaymentByUtr(
            @Param("transactionId")
            String transactionId);

    List<Payment> findPaymentsByCustomer(
            @Param("customerId")
            String customerId);

    List<Payment> findPaymentsBySeller(
            @Param("sellerId")
            String sellerId);

    List<Payment> findAllPayments();

    void updatePaymentStatus(Payment payment);

    Payment findPaymentByOrder(
            @Param("orderId")
            String orderId);

    List<Payment> findPaymentsByCustomerAndKeyword(
            @Param("customerId")
            String customerId,

            @Param("keyword")
            String keyword
    );

    List<Payment> findPaymentsBySellerAndKeyword(
            @Param("sellerId")
            String sellerId,

            @Param("keyword")
            String keyword
    );

    List<Payment> findPaymentsByStatus(
            @Param("paymentStatus")
            PaymentStatus paymentStatus);

    List<Payment> findPaymentsByKeyword(
            @Param("keyword")
            String keyword
    );
}