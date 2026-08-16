package com.crimsonlogic.ecommerce.service.impl;

import com.crimsonlogic.ecommerce.dao.OrderMapper;
import com.crimsonlogic.ecommerce.enumeration.OrderStatus;
import com.crimsonlogic.ecommerce.model.Order;
import com.crimsonlogic.ecommerce.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderMapper orderMapper;


    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }


    @Override
    public void insertOrder(Order order) {

        orderMapper.insertOrder(order);
    }


    @Override
    public void updateOrder(Order order) {

        orderMapper.updateOrder(order);
    }


    @Override
    public void deleteOrder(String orderId) {

        orderMapper.deleteOrder(orderId);
    }


    @Override
    public Order findOrderById(String orderId) {

        return orderMapper.findOrderById(orderId);
    }


    @Override
    public List<Order> findOrdersByCustomer(String customerId) {

        return orderMapper.findOrdersByCustomer(customerId);
    }


    @Override
    public List<Order> findOrdersBySeller(String sellerId) {

        return orderMapper.findOrdersBySeller(sellerId);
    }


    @Override
    public List<Order> findAllOrders() {

        return orderMapper.findAllOrders();
    }


    @Override
    public void updateOrderStatus(Order order) {

        orderMapper.updateOrderStatus(order);
    }


    @Override
    public List<Order> findPendingApprovalOrdersBySeller(
            String sellerId) {

        return orderMapper.findPendingApprovalOrdersBySeller(sellerId);
    }


    @Override
    public List<Order> findOrdersWithoutPayment(
            String customerId) {

        return orderMapper.findOrdersWithoutPayment(customerId);
    }


    @Override
    public List<Order> findOrdersByStatus(
            OrderStatus status) {

        return orderMapper.findOrdersByStatus(status);
    }


    @Override
    public List<Order> findOrdersByCustomerAndProduct(
            String customerId,
            String productName) {

        return orderMapper.findOrdersByCustomerAndProduct(
                customerId,
                productName);
    }


    @Override
    public List<Order> findOrdersBySellerAndProduct(
            String sellerId,
            String productName) {

        return orderMapper.findOrdersBySellerAndProduct(
                sellerId,
                productName);
    }


    @Override
    public List<Order> findOrdersByKeyword(
            String keyword) {

        return orderMapper.findOrdersByKeyword(keyword);
    }


    @Override
    public Order findOrderByIdAndCustomer(
            String orderId,
            String customerId) {

        return orderMapper.findOrderByIdAndCustomer(
                orderId,
                customerId);
    }


    @Override
    public List<Order> findCancelableOrders(
            String customerId) {

        return orderMapper.findCancelableOrders(customerId);
    }


    @Override
    public Order findOrderByIdAndSeller(
            String orderId,
            String sellerId) {

        return orderMapper.findOrderByIdAndSeller(
                orderId,
                sellerId);
    }
}