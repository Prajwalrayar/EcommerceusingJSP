package com.crimsonlogic.ecommerce.dao;

import com.crimsonlogic.ecommerce.model.report.CategorySalesReport;
import com.crimsonlogic.ecommerce.model.report.CustomerReport;
import com.crimsonlogic.ecommerce.model.report.ProductSalesReport;
import com.crimsonlogic.ecommerce.model.report.ReportFilter;
import com.crimsonlogic.ecommerce.model.report.SalesReport;
import com.crimsonlogic.ecommerce.model.report.SellerSalesReport;

import java.util.List;

public interface ReportMapper {
    SalesReport getSalesReport(ReportFilter filter);

    List<ProductSalesReport> getProductSalesReport(ReportFilter filter);

    List<CategorySalesReport> getCategorySalesReport(ReportFilter filter);

    List<SellerSalesReport> getSellerSalesReport(ReportFilter filter);

    CustomerReport getCustomerReport(ReportFilter filter);

    List<ProductSalesReport> getCustomerProductReport(ReportFilter filter);
}