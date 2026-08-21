package com.crimsonlogic.ecommerce.model;

import com.crimsonlogic.ecommerce.enumeration.OrderStatus;

import java.time.LocalDateTime;

/**
 * Represents an order placed by a customer in the ecommerce application.
 *
 * An Order contains information about the customer, ordered product,
 * quantity, total price, current order status, order date, delivery date,
 * and shipment tracking information.
 */
public class Order {

    /**
     * Unique identifier of the order.
     */
    private String orderId;

    /**
     * Customer who placed the order.
     */
    private Customer customer;

    /**
     * Product included in the order.
     */
    private Product product;

    /**
     * Quantity of the product ordered.
     */
    private int quantity;

    /**
     * Total amount calculated for the order.
     */
    private double totalPrice;

    /**
     * Current status of the order.
     *
     * The status represents the current stage of the order
     * in the ecommerce order-processing workflow.
     */
    private OrderStatus orderStatus;

    /**
     * Date and time when the order was placed.
     */
    private LocalDateTime orderDate;

    /**
     * Date and time when the order was delivered.
     *
     * This value can remain unset until the order is delivered.
     */
    private LocalDateTime deliveredDate;

    /**
     * Tracking number associated with the order shipment.
     *
     * This value is used to identify and track the shipment
     * after tracking information has been assigned.
     */
    private String trackingNumber;


    /**
     * Default constructor.
     *
     * Creates an empty Order object that can be populated
     * using the setter methods.
     */
    public Order() {
    }


    /**
     * Creates an Order object using the supplied order information.
     *
     * @param orderId unique identifier of the order
     * @param customer customer who placed the order
     * @param product product included in the order
     * @param quantity quantity of the product ordered
     * @param totalPrice total amount of the order
     * @param orderStatus current status of the order
     * @param orderDate date and time when the order was placed
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


    /**
     * Returns the unique identifier of the order.
     *
     * @return order ID
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Updates the unique identifier of the order.
     *
     * @param orderId new order ID
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    /**
     * Returns the customer who placed the order.
     *
     * @return customer associated with the order
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Updates the customer associated with the order.
     *
     * @param customer customer who placed the order
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    /**
     * Returns the product included in the order.
     *
     * @return ordered product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Updates the product included in the order.
     *
     * @param product product to associate with the order
     */
    public void setProduct(Product product) {
        this.product = product;
    }


    /**
     * Returns the quantity of the product ordered.
     *
     * @return ordered quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Updates the quantity of the product ordered.
     *
     * @param quantity new ordered quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    /**
     * Returns the total amount of the order.
     *
     * @return total order price
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Updates the total amount of the order.
     *
     * @param totalPrice new total order price
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


    /**
     * Returns the current status of the order.
     *
     * @return current order status
     */
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    /**
     * Updates the current status of the order.
     *
     * @param orderStatus new order status
     */
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }


    /**
     * Returns the date and time when the order was placed.
     *
     * @return order date and time
     */
    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    /**
     * Updates the date and time when the order was placed.
     *
     * @param orderDate new order date and time
     */
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }


    /**
     * Returns the date and time when the order was delivered.
     *
     * @return delivered date and time
     */
    public LocalDateTime getDeliveredDate() {
        return deliveredDate;
    }

    /**
     * Updates the date and time when the order was delivered.
     *
     * @param deliveredDate new delivered date and time
     */
    public void setDeliveredDate(LocalDateTime deliveredDate) {
        this.deliveredDate = deliveredDate;
    }


    /**
     * Returns the tracking number associated with the order shipment.
     *
     * @return shipment tracking number
     */
    public String getTrackingNumber() {
        return trackingNumber;
    }

    /**
     * Updates the tracking number associated with the order shipment.
     *
     * @param trackingNumber new shipment tracking number
     */
    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
}