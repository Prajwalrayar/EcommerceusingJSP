package com.crimsonlogic.ecommerce.controller;

import com.crimsonlogic.ecommerce.model.report.ReportFilter;
import com.crimsonlogic.ecommerce.service.CategoryService;
import com.crimsonlogic.ecommerce.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reports")
public class ReportController {

    private ReportService reportService;

    private CategoryService categoryService;


    public void setReportService(
            ReportService reportService) {

        this.reportService = reportService;
    }


    public void setCategoryService(
            CategoryService categoryService) {

        this.categoryService = categoryService;
    }


    // ==========================================================
    // ADMIN REPORT
    // ==========================================================

    @GetMapping("/admin")
    public String adminReports(
            @ModelAttribute ReportFilter filter,
            Model model) {


        model.addAttribute(
                "salesReport",
                reportService.getSalesReport(filter)
        );


        model.addAttribute(
                "productReports",
                reportService.getProductSalesReport(filter)
        );


        model.addAttribute(
                "categoryReports",
                reportService.getCategorySalesReport(filter)
        );


        model.addAttribute(
                "sellerReports",
                reportService.getSellerSalesReport(filter)
        );


        model.addAttribute(
                "categories",
                categoryService.findAllCategories()
        );


        model.addAttribute(
                "filter",
                filter
        );


        return "reports/admin-reports";
    }


    // ==========================================================
    // SELLER REPORT
    // ==========================================================

    @GetMapping("/seller/{sellerId}")
    public String sellerReports(

            @PathVariable String sellerId,

            @ModelAttribute ReportFilter filter,

            Model model) {


        /*
         * The seller ID comes from the authenticated/
         * authorized seller context in a real application.
         *
         * For your current JSP application we accept
         * sellerId in the URL.
         */

        filter.setSellerId(sellerId);


        model.addAttribute(
                "salesReport",
                reportService.getSalesReport(filter)
        );


        model.addAttribute(
                "productReports",
                reportService.getProductSalesReport(filter)
        );


        model.addAttribute(
                "categoryReports",
                reportService.getCategorySalesReport(filter)
        );


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


        model.addAttribute(
                "customerReport",
                reportService.getCustomerReport(filter)
        );


        model.addAttribute(
                "productReports",
                reportService.getCustomerProductReport(filter)
        );


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