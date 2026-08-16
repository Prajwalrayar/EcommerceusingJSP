package com.crimsonlogic.ecommerce.model.report;

public class CustomerReport {

    private String customerId;

    private String customerName;

    private long orderCount;

    private long quantityPurchased;

    private double totalSpent;

    private double averageOrderValue;


    public CustomerReport() {
    }


    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    public long getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(long orderCount) {
        this.orderCount = orderCount;
    }


    public long getQuantityPurchased() {
        return quantityPurchased;
    }

    public void setQuantityPurchased(long quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }


    public double getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(double totalSpent) {
        this.totalSpent = totalSpent;
    }


    public double getAverageOrderValue() {
        return averageOrderValue;
    }

    public void setAverageOrderValue(double averageOrderValue) {
        this.averageOrderValue = averageOrderValue;
    }
}