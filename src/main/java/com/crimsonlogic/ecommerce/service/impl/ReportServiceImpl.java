package com.crimsonlogic.ecommerce.service.impl;

import com.crimsonlogic.ecommerce.dao.ReportMapper;
import com.crimsonlogic.ecommerce.model.report.CategorySalesReport;
import com.crimsonlogic.ecommerce.model.report.CustomerReport;
import com.crimsonlogic.ecommerce.model.report.ProductSalesReport;
import com.crimsonlogic.ecommerce.model.report.ReportFilter;
import com.crimsonlogic.ecommerce.model.report.SalesReport;
import com.crimsonlogic.ecommerce.model.report.SellerSalesReport;
import com.crimsonlogic.ecommerce.service.ReportService;

import java.util.List;

public class ReportServiceImpl implements ReportService {

    private ReportMapper reportMapper;


    public void setReportMapper(ReportMapper reportMapper) {
        this.reportMapper = reportMapper;
    }


    @Override
    public SalesReport getSalesReport(
            ReportFilter filter) {

        return reportMapper.getSalesReport(filter);
    }


    @Override
    public List<ProductSalesReport> getProductSalesReport(
            ReportFilter filter) {

        return reportMapper.getProductSalesReport(filter);
    }


    @Override
    public List<CategorySalesReport> getCategorySalesReport(
            ReportFilter filter) {

        return reportMapper.getCategorySalesReport(filter);
    }


    @Override
    public List<SellerSalesReport> getSellerSalesReport(
            ReportFilter filter) {

        return reportMapper.getSellerSalesReport(filter);
    }


    @Override
    public CustomerReport getCustomerReport(
            ReportFilter filter) {

        return reportMapper.getCustomerReport(filter);
    }


    @Override
    public List<ProductSalesReport> getCustomerProductReport(
            ReportFilter filter) {

        return reportMapper.getCustomerProductReport(filter);
    }
}