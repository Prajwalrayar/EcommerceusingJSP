package com.crimsonlogic.ecommerce.model.report;

public class ReportFilter {

    private String reportType;

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

    /*
     * Keep dates as String because the JSP uses:
     * <input type="datetime-local">
     *
     * Browser sends values like:
     * 2026-08-18T10:30
     */
    private String fromDate;
    private String toDate;


    // ==========================================================
    // CONSTRUCTOR
    // ==========================================================

    public ReportFilter() {
    }


    // ==========================================================
    // REPORT TYPE
    // ==========================================================

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }


    // ==========================================================
    // CATEGORY
    // ==========================================================

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }


    // ==========================================================
    // PRODUCT
    // ==========================================================

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }


    // ==========================================================
    // SELLER
    // ==========================================================

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }


    // ==========================================================
    // CUSTOMER
    // ==========================================================

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }


    // ==========================================================
    // PRODUCT NAME
    // ==========================================================

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    // ==========================================================
    // ORDER STATUS
    // ==========================================================

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }


    // ==========================================================
    // PAYMENT STATUS
    // ==========================================================

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }


    // ==========================================================
    // MIN PRICE
    // ==========================================================

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }


    // ==========================================================
    // MAX PRICE
    // ==========================================================

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }


    // ==========================================================
    // MIN QUANTITY
    // ==========================================================

    public Integer getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
    }


    // ==========================================================
    // MAX QUANTITY
    // ==========================================================

    public Integer getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }


    // ==========================================================
    // FROM DATE
    // ==========================================================

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }


    // ==========================================================
    // TO DATE
    // ==========================================================

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
}