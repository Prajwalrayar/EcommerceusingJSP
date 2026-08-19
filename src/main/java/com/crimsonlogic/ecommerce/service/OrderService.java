package com.crimsonlogic.ecommerce.service;

import com.crimsonlogic.ecommerce.enumeration.OrderStatus;
import com.crimsonlogic.ecommerce.model.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderService {

    /**
     * Inserts Order.
     */
    void insertOrder(Order order);


    /**
     * Updates Order.
     */
    void updateOrder(Order order);


    /**
     * Deletes Order.
     */
    void deleteOrder(
            @Param("orderId")
            String orderId);


    /**
     * Finds Order by ID.
     */
    Order findOrderById(
            @Param("orderId")
            String orderId);


    /**
     * Returns Customer Orders.
     */
    List<Order> findOrdersByCustomer(
            @Param("customerId")
            String customerId);


    /**
     * Returns Seller Orders.
     */
    List<Order> findOrdersBySeller(
            @Param("sellerId")
            String sellerId);


    /**
     * Returns All Orders.
     */
    List<Order> findAllOrders();


    /**
     * Updates Order Status.
     */
    void updateOrderStatus(Order order);


    /**
     * Returns Seller Pending Approval Orders.
     */
    List<Order> findPendingApprovalOrdersBySeller(
            @Param("sellerId")
            String sellerId);


    /**
     * Returns Orders that are not yet paid.
     */
    List<Order> findOrdersWithoutPayment(
            @Param("customerId")
            String customerId);


    /**
     * Returns Orders by Status.
     */
    List<Order> findOrdersByStatus(
            @Param("status")
            OrderStatus status);


    /**
     * Returns Customer Orders matching Product.
     */
    List<Order> findOrdersByCustomerAndProduct(
            @Param("customerId")
            String customerId,

            @Param("productName")
            String productName);


    /**
     * Returns Seller Orders matching Product.
     */
    List<Order> findOrdersBySellerAndProduct(
            @Param("sellerId")
            String sellerId,

            @Param("productName")
            String productName);


    /**
     * Searches orders by keyword.
     */
    List<Order> findOrdersByKeyword(
            @Param("keyword")
            String keyword);


    /**
     * Finds customer's specific order.
     */
    Order findOrderByIdAndCustomer(
            @Param("orderId")
            String orderId,

            @Param("customerId")
            String customerId);


    /**
     * Finds customer's cancelable orders.
     */
    List<Order> findCancelableOrders(
            @Param("customerId")
            String customerId);


    /**
     * Finds seller's specific order.
     */
    Order findOrderByIdAndSeller(
            @Param("orderId")
            String orderId,

            @Param("sellerId")
            String sellerId);
    
    List<Order> findOrdersBySellerAndStatus(
            String sellerId,
            OrderStatus status);

    List<Order> findOrdersBySellerAndOrderId(
            String sellerId,
            String orderId);
}