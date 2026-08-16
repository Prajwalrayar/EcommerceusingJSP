package com.crimsonlogic.ecommerce.model;

import com.crimsonlogic.ecommerce.enumeration.OrderStatus;

import java.time.LocalDateTime;

public class Order {

    /**
     * Order ID.
     */
    private String orderId;

    /**
     * Customer who placed the order.
     */
    private Customer customer;

    /**
     * Ordered product.
     */
    private Product product;

    /**
     * Quantity ordered.
     */
    private int quantity;

    /**
     * Total amount.
     */
    private double totalPrice;

    /**
     * Current order status.
     */
    private OrderStatus orderStatus;

    /**
     * Order date and time.
     */
    private LocalDateTime orderDate;

    /**
     * Delivered date and time.
     */
    private LocalDateTime deliveredDate;


    /**
     * Default constructor.
     */
    public Order() {
    }


    /**
     * Parameterized constructor.
     */
    public Order(
            String orderId,
            Customer customer,
            Product product,
            int quantity,
            double totalPrice,
            OrderStatus orderStatus,
            LocalDateTime orderDate) {

        this.orderId = orderId;
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }


    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }


    public LocalDateTime getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(LocalDateTime deliveredDate) {
        this.deliveredDate = deliveredDate;
    }
}