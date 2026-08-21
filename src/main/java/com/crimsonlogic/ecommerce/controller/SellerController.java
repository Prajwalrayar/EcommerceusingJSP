package com.crimsonlogic.ecommerce.controller;

import com.crimsonlogic.ecommerce.exception.ValidationException;
import com.crimsonlogic.ecommerce.model.Address;
import com.crimsonlogic.ecommerce.model.Customer;
import com.crimsonlogic.ecommerce.model.Product;
import com.crimsonlogic.ecommerce.model.Review;
import com.crimsonlogic.ecommerce.model.Seller;
import com.crimsonlogic.ecommerce.model.report.CategorySalesReport;
import com.crimsonlogic.ecommerce.model.report.ProductSalesReport;
import com.crimsonlogic.ecommerce.model.report.ReportFilter;
import com.crimsonlogic.ecommerce.model.report.SalesReport;
import com.crimsonlogic.ecommerce.service.AddressService;
import com.crimsonlogic.ecommerce.service.CategoryService;
import com.crimsonlogic.ecommerce.service.InventoryService;
import com.crimsonlogic.ecommerce.service.OrderService;
import com.crimsonlogic.ecommerce.service.ProductService;
import com.crimsonlogic.ecommerce.service.ReportService;
import com.crimsonlogic.ecommerce.service.ReviewService;
import com.crimsonlogic.ecommerce.service.SellerService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/seller")
public class SellerController {

    private final SellerService sellerService;
    private final AddressService addressService;
    private final ReviewService reviewService;
    private final ProductService productService;
    private final OrderService orderService;
    private final InventoryService inventoryService;
    private final ReportService reportService;
    private final CategoryService categoryService;


    public SellerController(
            SellerService sellerService,
            AddressService addressService,
            ProductService productService,
            OrderService orderService,
            ReviewService reviewService,
            InventoryService inventoryService,
            ReportService reportService,
            CategoryService categoryService) {

        this.sellerService = sellerService;
        this.addressService = addressService;
        this.productService = productService;
        this.orderService = orderService;
        this.reviewService = reviewService;
        this.inventoryService = inventoryService;
        this.reportService = reportService;
        this.categoryService = categoryService;
    }


    // ==========================================================
    // SELLER PROFILE
    // ==========================================================

    @GetMapping("/profile/{sellerId}")
    public String viewProfile(
            @PathVariable String sellerId,
            Model model) {

        Seller seller =
                sellerService.findSellerById(sellerId);

        if (seller == null) {

            return "redirect:/admin/sellers";
        }


        List<Address> addresses =
                sellerService.findAddressesBySeller(
                        sellerId);


        model.addAttribute(
                "seller",
                seller);

        model.addAttribute(
                "addresses",
                addresses);


        return "seller/seller-profile";
    }


    // ==========================================================
    // EDIT SELLER PROFILE
    // ==========================================================

    @GetMapping("/profile/edit/{sellerId}")
    public String editProfile(
            @PathVariable String sellerId,
            Model model) {

        Seller seller =
                sellerService.findSellerById(
                        sellerId);

        if (seller == null) {

            return "redirect:/admin/sellers";
        }


        model.addAttribute(
                "seller",
                seller);


        return "seller/edit-profile";
    }


    // ==========================================================
    // UPDATE SELLER PROFILE
    // ==========================================================

    @PostMapping("/profile/update")
    public String updateProfile(
            @ModelAttribute Seller seller) {

        sellerService.updateSeller(
                seller);


        return "redirect:/seller/profile/"
                + seller.getUserId();
    }


    // ==========================================================
    // ADD / ASSIGN ADDRESS PAGE
    // ==========================================================

    @GetMapping("/{sellerId}/addresses/add")
    public String showAssignAddressPage(
            @PathVariable String sellerId,
            Model model) {

        Seller seller =
                sellerService.findSellerById(
                        sellerId);

        if (seller == null) {

            return "redirect:/admin/sellers";
        }


        List<Address> addresses =
                addressService.findAllAddresses();


        List<Address> sellerAddresses =
                sellerService.findAddressesBySeller(
                        sellerId);


        model.addAttribute(
                "seller",
                seller);

        model.addAttribute(
                "addresses",
                addresses);

        model.addAttribute(
                "sellerAddresses",
                sellerAddresses);


        return "address/assign-seller-address";
    }


    // ==========================================================
    // ASSIGN ADDRESS
    // ==========================================================

    @PostMapping("/{sellerId}/addresses/add")
    public String assignAddress(
            @PathVariable String sellerId,
            @RequestParam String addressId) {

        sellerService.assignAddressToSeller(
                sellerId,
                addressId);


        return "redirect:/seller/profile/"
                + sellerId;
    }


    // ==========================================================
    // REMOVE ADDRESS
    // ==========================================================

    @PostMapping(
            "/{sellerId}/addresses/remove/{addressId}")
    public String removeAddress(
            @PathVariable String sellerId,
            @PathVariable String addressId) {

        sellerService.removeAddressFromSeller(
                sellerId,
                addressId);


        return "redirect:/seller/profile/"
                + sellerId;
    }


    // ==========================================================
    // SELLER LIST
    // ==========================================================

    @GetMapping("/list")
    public String sellerList(
            Model model) {

        List<Seller> sellers =
                sellerService.findAllSellers();


        model.addAttribute(
                "sellers",
                sellers);


        return "seller/sellers";
    }
    
    @GetMapping("/dashboard")
    public String dashboard(
            HttpSession session,
            Model model) {

        Seller seller =
                requireLoggedInSeller(session);

        String sellerId =
                seller.getUserId();


        Seller currentSeller =
                sellerService.findSellerById(
                        sellerId
                );


        // ======================================================
        // TOTAL PRODUCTS
        // ======================================================

        List<Product> products =
                productService.findProductsBySeller(
                        sellerId
                );

        int totalProducts =
                products.size();


        // ======================================================
        // SALES REPORT
        // ======================================================

        ReportFilter filter =
                new ReportFilter();

        filter.setSellerId(
                sellerId
        );

        /*
         * Sold means delivered.
         * All aggregation/filtering is performed by SQL.
         */
        filter.setOrderStatus(
                "DELIVERED"
        );


        SalesReport salesReport =
                reportService.getSalesReport(
                        filter
                );


        // ======================================================
        // REVIEWS
        // ======================================================

        List<Review> reviews =
                reviewService.findReviewsBySeller(
                        sellerId
                );


        double averageRating = 0.0;

        if (!reviews.isEmpty()) {

            double totalRating = 0;

            for (Review review : reviews) {

                totalRating += review.getRating();
            }

            averageRating =
                    totalRating / reviews.size();
        }


        // ======================================================
        // MODEL
        // ======================================================

        model.addAttribute(
                "seller",
                currentSeller
        );

        model.addAttribute(
                "totalProducts",
                totalProducts
        );

        model.addAttribute(
                "totalOrders",
                salesReport != null
                        ? salesReport.getTotalOrders()
                        : 0
        );

        model.addAttribute(
                "totalRevenue",
                salesReport != null
                        ? salesReport.getTotalSales()
                        : 0.0
        );

        model.addAttribute(
                "totalSoldQuantity",
                salesReport != null
                        ? salesReport.getTotalQuantity()
                        : 0
        );

        model.addAttribute(
                "averageRevenue",
                salesReport != null
                        ? salesReport.getAverageOrderValue()
                        : 0.0
        );

        model.addAttribute(
                "averageSoldQuantity",
                salesReport != null
                        ? salesReport.getAverageSoldQuantity()
                        : 0.0
        );

        model.addAttribute(
                "averageRating",
                averageRating
        );

        return "seller/dashboard";
    }
    
    @GetMapping("/reports")
    public String sellerReports(
            HttpSession session,
            Model model,
            @ModelAttribute ReportFilter filter) {

        Seller seller =
                requireLoggedInSeller(session);

        filter.setSellerId(
                seller.getUserId()
        );

        /*
         * Seller sees completed/sold orders only.
         */
        filter.setOrderStatus(
                "DELIVERED"
        );

        SalesReport salesReport =
                reportService.getSalesReport(
                        filter
                );

        List<ProductSalesReport> productSales =
                reportService.getProductSalesReport(
                        filter
                );

        List<CategorySalesReport> categorySales =
                reportService.getCategorySalesReport(
                        filter
                );

        model.addAttribute(
                "salesReport",
                salesReport
        );

        model.addAttribute(
                "productSales",
                productSales
        );

        model.addAttribute(
                "categorySales",
                categorySales
        );

        model.addAttribute(
                "filter",
                filter
        );

        model.addAttribute(
                "seller",
                seller
        );

        return "reports/seller-reports";
    }
    
    
    @GetMapping("/customers")
    public String sellerCustomers(
            HttpSession session,
            Model model) {

        Seller seller =
                requireLoggedInSeller(session);

        List<Customer> customers =
                sellerService.findCustomersBySeller(
                        seller.getUserId()
                );

        model.addAttribute(
                "customers",
                customers
        );

        return "seller/customers";
    }
    private Seller requireLoggedInSeller(
            HttpSession session) {

        Object loggedInUser =
                session.getAttribute("loggedInUser");

        if (!(loggedInUser instanceof Seller)) {

            throw new ValidationException(
                    "Please login as seller."
            );
        }

        return (Seller) loggedInUser;
    }
}