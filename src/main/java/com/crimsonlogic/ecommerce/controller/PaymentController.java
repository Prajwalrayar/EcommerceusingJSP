package com.crimsonlogic.ecommerce.controller;

import com.crimsonlogic.ecommerce.model.Payment;
import com.crimsonlogic.ecommerce.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    // =========================================================
    // ADMIN - VIEW ALL PAYMENTS
    // =========================================================

    @GetMapping("/list")
    public String viewAllPayments(Model model) {

        List<Payment> payments =
                paymentService.findAllPayments();

        model.addAttribute("payments", payments);

        return "payment/payments";
    }


    // =========================================================
    // ADMIN - VIEW PAYMENT DETAILS
    // =========================================================

    @GetMapping("/details/{paymentId}")
    public String viewPaymentDetails(
            @PathVariable String paymentId,
            Model model) {

        Payment payment =
                paymentService.findPaymentById(paymentId);

        model.addAttribute("payment", payment);

        return "payment/payment-details";
    }


    // =========================================================
    // CUSTOMER - VIEW OWN PAYMENTS
    // =========================================================

    @GetMapping("/customer/{customerId}")
    public String viewCustomerPayments(
            @PathVariable String customerId,
            Model model) {

        List<Payment> payments =
                paymentService.findPaymentsByCustomer(customerId);

        model.addAttribute("payments", payments);

        model.addAttribute("customerId", customerId);

        return "payment/customer-payments";
    }


    // =========================================================
    // SELLER - VIEW PAYMENTS
    // =========================================================

    @GetMapping("/seller/{sellerId}")
    public String viewSellerPayments(
            @PathVariable String sellerId,
            Model model) {

        List<Payment> payments =
                paymentService.findPaymentsBySeller(sellerId);

        model.addAttribute("payments", payments);

        model.addAttribute("sellerId", sellerId);

        return "payment/seller-payments";
    }


    // =========================================================
    // SEARCH - ALL PAYMENTS
    // =========================================================

    @GetMapping("/search")
    public String searchPayments(
            @RequestParam String keyword,
            Model model) {

        List<Payment> payments =
                paymentService.findPaymentsByKeyword(keyword);

        model.addAttribute("payments", payments);

        model.addAttribute("keyword", keyword);

        return "payment/payments";
    }


    // =========================================================
    // SEARCH - CUSTOMER PAYMENTS
    // =========================================================

    @GetMapping("/customer/{customerId}/search")
    public String searchCustomerPayments(
            @PathVariable String customerId,
            @RequestParam String keyword,
            Model model) {

        List<Payment> payments =
                paymentService.findPaymentsByCustomerAndKeyword(
                        customerId,
                        keyword
                );

        model.addAttribute("payments", payments);

        model.addAttribute("customerId", customerId);

        model.addAttribute("keyword", keyword);

        return "payment/customer-payments";
    }


    // =========================================================
    // SEARCH - SELLER PAYMENTS
    // =========================================================

    @GetMapping("/seller/{sellerId}/search")
    public String searchSellerPayments(
            @PathVariable String sellerId,
            @RequestParam String keyword,
            Model model) {

        List<Payment> payments =
                paymentService.findPaymentsBySellerAndKeyword(
                        sellerId,
                        keyword
                );

        model.addAttribute("payments", payments);

        model.addAttribute("sellerId", sellerId);

        model.addAttribute("keyword", keyword);

        return "payment/seller-payments";
    }


    // =========================================================
    // DELETE PAYMENT
    // =========================================================

    @PostMapping("/delete/{paymentId}")
    public String deletePayment(
            @PathVariable String paymentId) {

        paymentService.deletePayment(paymentId);

        return "redirect:/payment/list";
    }

}