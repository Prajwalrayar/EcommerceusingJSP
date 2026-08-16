package com.crimsonlogic.ecommerce.service.impl;

import com.crimsonlogic.ecommerce.dao.PaymentMapper;
import com.crimsonlogic.ecommerce.enumeration.PaymentStatus;
import com.crimsonlogic.ecommerce.model.Payment;
import com.crimsonlogic.ecommerce.service.PaymentService;

import java.util.List;

public class PaymentServiceImpl implements PaymentService {

    private PaymentMapper paymentMapper;

    public void setPaymentMapper(PaymentMapper paymentMapper) {
        this.paymentMapper = paymentMapper;
    }

    @Override
    public void insertPayment(Payment payment) {
        paymentMapper.insertPayment(payment);
    }

    @Override
    public void updatePayment(Payment payment) {
        paymentMapper.updatePayment(payment);
    }

    @Override
    public void deletePayment(String paymentId) {
        paymentMapper.deletePayment(paymentId);
    }

    @Override
    public Payment findPaymentById(String paymentId) {
        return paymentMapper.findPaymentById(paymentId);
    }

    @Override
    public Payment findPaymentByUtr(String transactionId) {
        return paymentMapper.findPaymentByUtr(transactionId);
    }

    @Override
    public List<Payment> findPaymentsByCustomer(String customerId) {
        return paymentMapper.findPaymentsByCustomer(customerId);
    }

    @Override
    public List<Payment> findPaymentsBySeller(String sellerId) {
        return paymentMapper.findPaymentsBySeller(sellerId);
    }

    @Override
    public List<Payment> findAllPayments() {
        return paymentMapper.findAllPayments();
    }

    @Override
    public void updatePaymentStatus(Payment payment) {
        paymentMapper.updatePaymentStatus(payment);
    }

    @Override
    public Payment findPaymentByOrder(String orderId) {
        return paymentMapper.findPaymentByOrder(orderId);
    }

    @Override
    public List<Payment> findPaymentsByCustomerAndKeyword(
            String customerId,
            String keyword) {

        return paymentMapper.findPaymentsByCustomerAndKeyword(
                customerId,
                keyword
        );
    }

    @Override
    public List<Payment> findPaymentsBySellerAndKeyword(
            String sellerId,
            String keyword) {

        return paymentMapper.findPaymentsBySellerAndKeyword(
                sellerId,
                keyword
        );
    }

    @Override
    public List<Payment> findPaymentsByStatus(
            PaymentStatus paymentStatus) {

        return paymentMapper.findPaymentsByStatus(paymentStatus);
    }

    @Override
    public List<Payment> findPaymentsByKeyword(String keyword) {
        return paymentMapper.findPaymentsByKeyword(keyword);
    }
}