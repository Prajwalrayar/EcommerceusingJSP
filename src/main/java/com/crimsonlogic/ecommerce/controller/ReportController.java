package com.crimsonlogic.ecommerce.controller;

import com.crimsonlogic.ecommerce.model.Seller;
import com.crimsonlogic.ecommerce.model.report.CategorySalesReport;
import com.crimsonlogic.ecommerce.model.report.CustomerReport;
import com.crimsonlogic.ecommerce.model.report.ProductSalesReport;
import com.crimsonlogic.ecommerce.model.report.ReportFilter;
import com.crimsonlogic.ecommerce.model.report.SalesReport;
import com.crimsonlogic.ecommerce.model.report.SellerSalesReport;
import com.crimsonlogic.ecommerce.service.CategoryService;
import com.crimsonlogic.ecommerce.service.ReportService;
import com.crimsonlogic.ecommerce.service.SellerService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reports")
public class ReportController {

    private ReportService reportService;

    private CategoryService categoryService;

    private SellerService sellerService;


    // ==========================================================
    // SETTERS
    // ==========================================================

    public void setReportService(
            ReportService reportService) {

        this.reportService = reportService;
    }


    public void setCategoryService(
            CategoryService categoryService) {

        this.categoryService = categoryService;
    }


    public void setSellerService(
            SellerService sellerService) {

        this.sellerService = sellerService;
    }


    // ==========================================================
    // ADMIN REPORT PAGE
    // ==========================================================

    @GetMapping("/admin")
    public String adminReports(
            @ModelAttribute ReportFilter filter,
            Model model) {

        loadAdminReportPage(filter, model);

        return "admin/reports";
    }


    // ==========================================================
    // ADMIN GENERATE REPORT
    // ==========================================================

    @PostMapping("/admin")
    public String generateAdminReport(
            @ModelAttribute ReportFilter filter,
            Model model) {

        loadAdminReportPage(filter, model);

        return "admin/reports";
    }


    // ==========================================================
    // LOAD ADMIN REPORT PAGE
    // ==========================================================

    private void loadAdminReportPage(
            ReportFilter filter,
            Model model) {

        // ------------------------------------------------------
        // Categories for dropdown
        // ------------------------------------------------------

        model.addAttribute(
                "categories",
                categoryService.findAllCategories()
        );


        // ------------------------------------------------------
        // Sellers for dropdown
        // ------------------------------------------------------

        List<Seller> sellers =
                sellerService.findAllSellers();

        model.addAttribute(
                "sellers",
                sellers
        );


        // ------------------------------------------------------
        // Keep filter
        // ------------------------------------------------------

        model.addAttribute(
                "filter",
                filter
        );


        // ------------------------------------------------------
        // SELLER SALES REPORT
        // ------------------------------------------------------

        if (filter.getSellerId() != null
                && !filter.getSellerId().trim().isEmpty()) {

            List<SellerSalesReport> sellerReports =
                    reportService.getSellerSalesReport(filter);

            model.addAttribute(
                    "sellerReports",
                    sellerReports
            );


            // Seller's products
            List<ProductSalesReport> productReports =
                    reportService.getProductSalesReport(filter);

            model.addAttribute(
                    "sellerProductReports",
                    productReports
            );


            // Seller summary
            SalesReport salesReport =
                    reportService.getSalesReport(filter);

            model.addAttribute(
                    "salesReport",
                    salesReport
            );


            // Seller category report
            List<CategorySalesReport> categoryReports =
                    reportService.getCategorySalesReport(filter);

            model.addAttribute(
                    "categoryReports",
                    categoryReports
            );


            model.addAttribute(
                    "reportType",
                    "seller"
            );

            return;
        }


        // ------------------------------------------------------
        // NORMAL ADMIN SALES REPORT
        // ------------------------------------------------------

        SalesReport salesReport =
                reportService.getSalesReport(filter);

        model.addAttribute(
                "salesReport",
                salesReport
        );


        List<ProductSalesReport> productReports =
                reportService.getProductSalesReport(filter);

        model.addAttribute(
                "productReports",
                productReports
        );


        List<CategorySalesReport> categoryReports =
                reportService.getCategorySalesReport(filter);

        model.addAttribute(
                "categoryReports",
                categoryReports
        );


        List<SellerSalesReport> sellerReports =
                reportService.getSellerSalesReport(filter);

        model.addAttribute(
                "sellerReports",
                sellerReports
        );


        model.addAttribute(
                "reportType",
                "general"
        );
    }


    // ==========================================================
    // SELLER REPORT
    // ==========================================================

    @GetMapping("/seller/{sellerId}")
    public String sellerReports(

            @PathVariable String sellerId,

            @ModelAttribute ReportFilter filter,

            Model model) {

        filter.setSellerId(sellerId);


        // ------------------------------------------------------
        // Seller sales summary
        // ------------------------------------------------------

        model.addAttribute(
                "salesReport",
                reportService.getSalesReport(filter)
        );


        // ------------------------------------------------------
        // Seller products
        // ------------------------------------------------------

        model.addAttribute(
                "productReports",
                reportService.getProductSalesReport(filter)
        );


        // ------------------------------------------------------
        // Seller category report
        // ------------------------------------------------------

        model.addAttribute(
                "categoryReports",
                reportService.getCategorySalesReport(filter)
        );


        // ------------------------------------------------------
        // Categories
        // ------------------------------------------------------

        model.addAttribute(
                "categories",
                categoryService.findAllCategories()
        );


        model.addAttribute(
                "sellerId",
                sellerId
        );


        model.addAttribute(
                "filter",
                filter
        );


        return "reports/seller-reports";
    }


    // ==========================================================
    // CUSTOMER REPORT
    // ==========================================================

    @GetMapping("/customer/{customerId}")
    public String customerReports(

            @PathVariable String customerId,

            @ModelAttribute ReportFilter filter,

            Model model) {

        filter.setCustomerId(customerId);


        // ------------------------------------------------------
        // Customer summary
        // ------------------------------------------------------

        model.addAttribute(
                "customerReport",
                reportService.getCustomerReport(filter)
        );


        // ------------------------------------------------------
        // Customer products
        // ------------------------------------------------------

        model.addAttribute(
                "productReports",
                reportService.getCustomerProductReport(filter)
        );


        // ------------------------------------------------------
        // Categories
        // ------------------------------------------------------

        model.addAttribute(
                "categories",
                categoryService.findAllCategories()
        );


        model.addAttribute(
                "customerId",
                customerId
        );


        model.addAttribute(
                "filter",
                filter
        );


        return "reports/customer-reports";
    }
}