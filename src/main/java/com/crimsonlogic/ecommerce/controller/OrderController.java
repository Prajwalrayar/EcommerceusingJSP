package com.crimsonlogic.ecommerce.controller;

import com.crimsonlogic.ecommerce.enumeration.OrderStatus;
import com.crimsonlogic.ecommerce.model.Order;
import com.crimsonlogic.ecommerce.service.OrderService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    // =========================================================
    // CUSTOMER
    // =========================================================

    /**
     * Displays all orders of a customer.
     */
    @GetMapping("/customer/{customerId}")
    public String customerOrders(
            @PathVariable String customerId,
            Model model) {

        List<Order> orders =
                orderService.findOrdersByCustomer(customerId);

        model.addAttribute("orders", orders);
        model.addAttribute("customerId", customerId);

        return "customer/orders";
    }


    /**
     * Displays customer's specific order.
     */
    @GetMapping("/customer/{customerId}/{orderId}")
    public String customerOrderDetails(
            @PathVariable String customerId,
            @PathVariable String orderId,
            Model model) {

        Order order =
                orderService.findOrderByIdAndCustomer(
                        orderId,
                        customerId);

        model.addAttribute("order", order);

        return "customer/order-details";
    }


    /**
     * Displays customer orders that can be cancelled.
     */
    @GetMapping("/customer/{customerId}/cancelable")
    public String cancelableOrders(
            @PathVariable String customerId,
            Model model) {

        List<Order> orders =
                orderService.findCancelableOrders(customerId);

        model.addAttribute("orders", orders);
        model.addAttribute("customerId", customerId);

        return "customer/cancelable-orders";
    }


    /**
     * Cancels customer's order.
     */
    @PostMapping("/customer/{customerId}/cancel/{orderId}")
    public String cancelOrder(
            @PathVariable String customerId,
            @PathVariable String orderId) {

        Order order =
                orderService.findOrderByIdAndCustomer(
                        orderId,
                        customerId);

        if (order != null) {

            order.setOrderStatus(OrderStatus.CANCELLED);

            orderService.updateOrderStatus(order);
        }

        return "redirect:/orders/customer/" + customerId;
    }


    /**
     * Displays customer's unpaid orders.
     */
    @GetMapping("/customer/{customerId}/payment-pending")
    public String paymentPendingOrders(
            @PathVariable String customerId,
            Model model) {

        List<Order> orders =
                orderService.findOrdersWithoutPayment(
                        customerId);

        model.addAttribute("orders", orders);
        model.addAttribute("customerId", customerId);

        return "customer/payment-pending-orders";
    }


    /**
     * Searches customer's orders by product.
     */
    @GetMapping("/customer/{customerId}/search")
    public String searchCustomerOrders(
            @PathVariable String customerId,
            @RequestParam String productName,
            Model model) {

        List<Order> orders =
                orderService.findOrdersByCustomerAndProduct(
                        customerId,
                        productName);

        model.addAttribute("orders", orders);
        model.addAttribute("customerId", customerId);
        model.addAttribute("productName", productName);

        return "customer/orders";
    }


    // =========================================================
    // SELLER
    // =========================================================

    /**
     * Displays all orders containing seller's products.
     */
    @GetMapping("/seller/{sellerId}")
    public String sellerOrders(
            @PathVariable String sellerId,
            Model model) {

        List<Order> orders =
                orderService.findOrdersBySeller(sellerId);

        model.addAttribute("orders", orders);
        model.addAttribute("sellerId", sellerId);

        return "seller/orders";
    }


    /**
     * Displays seller's pending approval orders.
     */
    @GetMapping("/seller/{sellerId}/pending")
    public String pendingSellerOrders(
            @PathVariable String sellerId,
            Model model) {

        List<Order> orders =
                orderService.findPendingApprovalOrdersBySeller(
                        sellerId);

        model.addAttribute("orders", orders);
        model.addAttribute("sellerId", sellerId);

        return "seller/pending-orders";
    }


    /**
     * Displays seller's specific order.
     */
    @GetMapping("/seller/{sellerId}/{orderId}")
    public String sellerOrderDetails(
            @PathVariable String sellerId,
            @PathVariable String orderId,
            Model model) {

        Order order =
                orderService.findOrderByIdAndSeller(
                        orderId,
                        sellerId);

        model.addAttribute("order", order);

        return "seller/order-details";
    }


    /**
     * Updates seller order status.
     */
    @PostMapping("/seller/{sellerId}/status")
    public String updateSellerOrderStatus(
            @PathVariable String sellerId,
            @ModelAttribute Order order) {

        Order existingOrder =
                orderService.findOrderByIdAndSeller(
                        order.getOrderId(),
                        sellerId);

        if (existingOrder != null) {

            existingOrder.setOrderStatus(
                    order.getOrderStatus());

            if (order.getOrderStatus() ==
                    OrderStatus.DELIVERED) {

                existingOrder.setDeliveredDate(
                        LocalDateTime.now());
            }

            orderService.updateOrderStatus(existingOrder);
        }

        return "redirect:/orders/seller/" + sellerId;
    }


    /**
     * Searches seller's orders by product.
     */
    @GetMapping("/seller/{sellerId}/search")
    public String searchSellerOrders(
            @PathVariable String sellerId,
            @RequestParam String productName,
            Model model) {

        List<Order> orders =
                orderService.findOrdersBySellerAndProduct(
                        sellerId,
                        productName);

        model.addAttribute("orders", orders);
        model.addAttribute("sellerId", sellerId);
        model.addAttribute("productName", productName);

        return "seller/orders";
    }


    // =========================================================
    // ADMIN
    // =========================================================

    /**
     * Displays all orders.
     */
    @GetMapping("/admin")
    public String allOrders(Model model) {

        List<Order> orders =
                orderService.findAllOrders();

        model.addAttribute("orders", orders);

        return "orders/admin/orders";
    }


    /**
     * Displays a specific order.
     */
    @GetMapping("/admin/{orderId}")
    public String adminOrderDetails(
            @PathVariable String orderId,
            Model model) {

        Order order =
                orderService.findOrderById(orderId);

        model.addAttribute("order", order);

        return "orders/admin/order-details";
    }


    /**
     * Filters orders by status.
     */
    @GetMapping("/admin/status")
    public String ordersByStatus(
            @RequestParam OrderStatus status,
            Model model) {

        List<Order> orders =
                orderService.findOrdersByStatus(status);

        model.addAttribute("orders", orders);
        model.addAttribute("selectedStatus", status);

        return "orders/admin/orders";
    }


    /**
     * Searches all orders.
     */
    @GetMapping("/admin/search")
    public String searchOrders(
            @RequestParam String keyword,
            Model model) {

        List<Order> orders =
                orderService.findOrdersByKeyword(keyword);

        model.addAttribute("orders", orders);
        model.addAttribute("keyword", keyword);

        return "orders/admin/orders";
    }


    /**
     * Deletes an order.
     */
    @PostMapping("/admin/delete/{orderId}")
    public String deleteOrder(
            @PathVariable String orderId) {

        orderService.deleteOrder(orderId);

        return "redirect:/orders/admin";
    }
}