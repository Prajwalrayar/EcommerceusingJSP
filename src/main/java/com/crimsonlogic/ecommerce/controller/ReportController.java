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

    private final ReportService reportService;
    private final CategoryService categoryService;
    private final SellerService sellerService;


    // ==========================================================
    // CONSTRUCTOR
    // ==========================================================

    public ReportController(
            ReportService reportService,
            CategoryService categoryService,
            SellerService sellerService) {

        this.reportService = reportService;
        this.categoryService = categoryService;
        this.sellerService = sellerService;
    }


    // ==========================================================
    // ADMIN REPORT PAGE
    // URL: /reports/admin
    // JSP: /WEB-INF/views/admin/reports.jsp
    // ==========================================================

    @GetMapping("/admin")
    public String adminReports(
            @ModelAttribute ReportFilter filter,
            Model model) {

        loadAdminReportPage(
                filter,
                model
        );

        return "admin/reports";
    }


    // ==========================================================
    // ADMIN GENERATE REPORT
    // URL: /reports/admin
    // JSP: /WEB-INF/views/admin/reports.jsp
    // ==========================================================

    @PostMapping("/admin")
    public String generateAdminReport(
            @ModelAttribute ReportFilter filter,
            Model model) {

        loadAdminReportPage(
                filter,
                model
        );

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

        if ("seller".equals(filter.getReportType())
                && filter.getSellerId() != null
                && !filter.getSellerId().trim().isEmpty()){


            List<SellerSalesReport> sellerReports =
                    reportService.getSellerSalesReport(
                            filter
                    );

            model.addAttribute(
                    "sellerReports",
                    sellerReports
            );


            // --------------------------------------------------
            // Seller's products
            // --------------------------------------------------

            List<ProductSalesReport> productReports =
                    reportService.getProductSalesReport(
                            filter
                    );

            model.addAttribute(
                    "sellerProductReports",
                    productReports
            );


            // --------------------------------------------------
            // Seller summary
            // --------------------------------------------------

            SalesReport salesReport =
                    reportService.getSalesReport(
                            filter
                    );

            model.addAttribute(
                    "salesReport",
                    salesReport
            );


            // --------------------------------------------------
            // Seller category report
            // --------------------------------------------------

            List<CategorySalesReport> categoryReports =
                    reportService.getCategorySalesReport(
                            filter
                    );

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


        // ======================================================
        // NORMAL ADMIN SALES REPORT
        // ======================================================

        SalesReport salesReport =
                reportService.getSalesReport(
                        filter
                );

        model.addAttribute(
                "reportType",
                filter.getReportType()
        );


        // ------------------------------------------------------
        // Product reports
        // ------------------------------------------------------

        List<ProductSalesReport> productReports =
                reportService.getProductSalesReport(
                        filter
                );

        model.addAttribute(
                "productReports",
                productReports
        );


        // ------------------------------------------------------
        // Category reports
        // ------------------------------------------------------

        List<CategorySalesReport> categoryReports =
                reportService.getCategorySalesReport(
                        filter
                );

        model.addAttribute(
                "categoryReports",
                categoryReports
        );


        // ------------------------------------------------------
        // Seller reports
        // ------------------------------------------------------

        List<SellerSalesReport> sellerReports =
                reportService.getSellerSalesReport(
                        filter
                );

        model.addAttribute(
                "sellerReports",
                sellerReports
        );
    }


    // ==========================================================
    // SELLER REPORT
    // URL: /reports/seller/{sellerId}
    // ==========================================================

    @GetMapping("/seller/{sellerId}")
    public String sellerReport(
            @PathVariable("sellerId") String sellerId,
            @ModelAttribute("filter") ReportFilter filter,
            Model model) {

        // Always enforce seller ownership
        filter.setSellerId(sellerId);

        // Categories for filter dropdown
        model.addAttribute(
                "categories",
                categoryService.findAllCategories()
        );

        // Sales summary
        SalesReport salesReport =
                reportService.getSalesReport(filter);

        // Product sales
        List<ProductSalesReport> productReports =
                reportService.getProductSalesReport(filter);

        // Category sales
        List<CategorySalesReport> categoryReports =
                reportService.getCategorySalesReport(filter);

        model.addAttribute(
                "sellerId",
                sellerId
        );

        model.addAttribute(
                "filter",
                filter
        );

        model.addAttribute(
                "salesReport",
                salesReport
        );

        model.addAttribute(
                "productReports",
                productReports
        );

        model.addAttribute(
                "categoryReports",
                categoryReports
        );

        return "reports/seller-reports";
    }
    
    @PostMapping("/seller/{sellerId}")
    public String applySellerReportFilter(
            @PathVariable("sellerId") String sellerId,
            @ModelAttribute("filter") ReportFilter filter,
            Model model) {

        filter.setSellerId(sellerId);

        model.addAttribute(
                "categories",
                categoryService.findAllCategories()
        );

        SalesReport salesReport =
                reportService.getSalesReport(filter);

        List<ProductSalesReport> productReports =
                reportService.getProductSalesReport(filter);

        List<CategorySalesReport> categoryReports =
                reportService.getCategorySalesReport(filter);

        model.addAttribute(
                "sellerId",
                sellerId
        );

        model.addAttribute(
                "filter",
                filter
        );

        model.addAttribute(
                "salesReport",
                salesReport
        );

        model.addAttribute(
                "productReports",
                productReports
        );

        model.addAttribute(
                "categoryReports",
                categoryReports
        );

        return "reports/seller-reports";
    }


    // ==========================================================
    // CUSTOMER REPORT
    // URL: /reports/customer/{customerId}
    // ==========================================================

    @GetMapping("/customer/{customerId}")
    public String customerReports(

            @PathVariable String customerId,

            @ModelAttribute ReportFilter filter,

            Model model) {


        filter.setCustomerId(
                customerId
        );


        // ------------------------------------------------------
        // Customer summary
        // ------------------------------------------------------

        model.addAttribute(
                "customerReport",
                reportService.getCustomerReport(
                        filter
                )
        );


        // ------------------------------------------------------
        // Customer products
        // ------------------------------------------------------

        model.addAttribute(
                "productReports",
                reportService.getCustomerProductReport(
                        filter
                )
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