package com.crimsonlogic.ecommerce.model.report;

public class SalesReport {

    private long totalOrders;

    private long totalQuantity;

    private double totalSales;

    private double averageOrderValue;
    
    private double averageSoldQuantity;


    public SalesReport() {
    }


    public long getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(long totalOrders) {
        this.totalOrders = totalOrders;
    }


    public long getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(long totalQuantity) {
        this.totalQuantity = totalQuantity;
    }


    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }


    public double getAverageOrderValue() {
        return averageOrderValue;
    }

    public void setAverageOrderValue(double averageOrderValue) {
        this.averageOrderValue = averageOrderValue;
    }
    
    public double getAverageSoldQuantity() {
        return averageSoldQuantity;
    }

    public void setAverageSoldQuantity(
            double averageSoldQuantity) {

        this.averageSoldQuantity = averageSoldQuantity;
    }
}