package com.crimsonlogic.ecommerce.model.report;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class ReportFilter {

    private String categoryId;

    private String productId;

    private String sellerId;

    private String customerId;

    private String productName;

    private String orderStatus;

    private String paymentStatus;

    private Double minPrice;

    private Double maxPrice;

    private Integer minQuantity;

    private Integer maxQuantity;


    @DateTimeFormat(
            iso = DateTimeFormat.ISO.DATE_TIME
    )
    private LocalDateTime fromDate;


    @DateTimeFormat(
            iso = DateTimeFormat.ISO.DATE_TIME
    )
    private LocalDateTime toDate;


    public ReportFilter() {
    }


    public String getCategoryId() {
        return categoryId;
    }


    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }


    public String getProductId() {
        return productId;
    }


    public void setProductId(String productId) {
        this.productId = productId;
    }


    public String getSellerId() {
        return sellerId;
    }


    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }


    public String getCustomerId() {
        return customerId;
    }


    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }


    public String getProductName() {
        return productName;
    }


    public void setProductName(String productName) {
        this.productName = productName;
    }


    public String getOrderStatus() {
        return orderStatus;
    }


    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }


    public String getPaymentStatus() {
        return paymentStatus;
    }


    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }


    public Double getMinPrice() {
        return minPrice;
    }


    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }


    public Double getMaxPrice() {
        return maxPrice;
    }


    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }


    public Integer getMinQuantity() {
        return minQuantity;
    }


    public void setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
    }


    public Integer getMaxQuantity() {
        return maxQuantity;
    }


    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }


    public LocalDateTime getFromDate() {
        return fromDate;
    }


    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }


    public LocalDateTime getToDate() {
        return toDate;
    }


    public void setToDate(LocalDateTime toDate) {
        this.toDate = toDate;
    }
}