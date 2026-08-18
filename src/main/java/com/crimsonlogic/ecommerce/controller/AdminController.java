package com.crimsonlogic.ecommerce.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crimsonlogic.ecommerce.enumeration.OrderStatus;
import com.crimsonlogic.ecommerce.exception.ValidationException;
import com.crimsonlogic.ecommerce.model.Address;
import com.crimsonlogic.ecommerce.model.Admin;
import com.crimsonlogic.ecommerce.model.Category;
import com.crimsonlogic.ecommerce.model.Customer;
import com.crimsonlogic.ecommerce.model.Inventory;
import com.crimsonlogic.ecommerce.model.Order;
import com.crimsonlogic.ecommerce.model.Product;
import com.crimsonlogic.ecommerce.model.Seller;
import com.crimsonlogic.ecommerce.model.report.CategorySalesReport;
import com.crimsonlogic.ecommerce.model.report.CustomerReport;
import com.crimsonlogic.ecommerce.model.report.ProductSalesReport;
import com.crimsonlogic.ecommerce.model.report.ReportFilter;
import com.crimsonlogic.ecommerce.model.report.SalesReport;
import com.crimsonlogic.ecommerce.model.report.SellerSalesReport;
import com.crimsonlogic.ecommerce.service.AdminService;
import com.crimsonlogic.ecommerce.service.CategoryService;
import com.crimsonlogic.ecommerce.service.CustomerService;
import com.crimsonlogic.ecommerce.service.InventoryService;
import com.crimsonlogic.ecommerce.service.OrderService;
import com.crimsonlogic.ecommerce.service.ProductService;
import com.crimsonlogic.ecommerce.service.ReportService;
import com.crimsonlogic.ecommerce.service.SellerService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private final AdminService adminService;
	private final CustomerService customerService;
	private final SellerService sellerService;
	private final ProductService productService;
	private final ReportService reportService;
	private final OrderService orderService;
	private final CategoryService categoryService;
	private final InventoryService inventoryService;

	public AdminController(AdminService adminService, CustomerService customerService, SellerService sellerService,
			ProductService productService, ReportService reportService, OrderService orderService,
			CategoryService categoryService, InventoryService inventoryService) {

		this.adminService = adminService;
		this.customerService = customerService;
		this.sellerService = sellerService;
		this.productService = productService;
		this.reportService = reportService;
		this.orderService = orderService;
		this.categoryService = categoryService;
		this.inventoryService = inventoryService;
	}

	// =====================================================
	// ADMIN DASHBOARD
	// =====================================================

	@GetMapping("/dashboard")
	public String dashboard(Model model) {

		// 1. Total Customers
		int totalCustomers = adminService.getAllCustomers().size();

		// 2. Total Sellers
		int totalSellers = adminService.getAllSellers().size();

		// 3. Total Products
		int totalProducts = productService.findAllProducts().size();

		// 4. Total Orders
		List<Order> allOrders = orderService.findAllOrders();
		int totalOrders = allOrders.size();

		// 5. Total Revenue
		ReportFilter emptyFilter = new ReportFilter();
		SalesReport salesReport = reportService.getSalesReport(emptyFilter);
		double totalRevenue = (salesReport != null) ? salesReport.getTotalSales() : 0.0;

		// 6. Pending Orders
		long pendingOrders = allOrders.stream()
				.filter(o -> o.getOrderStatus() != null && (o.getOrderStatus().name().contains("PENDING"))).count();

		model.addAttribute("totalCustomers", totalCustomers);
		model.addAttribute("totalSellers", totalSellers);
		model.addAttribute("totalProducts", totalProducts);
		model.addAttribute("totalOrders", totalOrders);
		model.addAttribute("totalRevenue", totalRevenue);
		model.addAttribute("pendingOrders", pendingOrders);

		return "admin/dashboard";
	}

	// =====================================================
	// CUSTOMER MANAGEMENT
	// =====================================================

	/**
	 * Displays all customers.
	 *
	 * URL:
	 *
	 * /admin/customers
	 */
	@GetMapping("/customers")
	public String viewAllCustomers(Model model) {

		List<Customer> customers = adminService.getAllCustomers();

		model.addAttribute("customers", customers);

		return "admin/customers";
	}

	/**
	 * Displays one customer's profile.
	 *
	 * Admin can only VIEW the customer.
	 *
	 * URL:
	 *
	 * /admin/customer/{customerId}
	 */
	@GetMapping("/customer/{customerId}")
	public String viewCustomerProfile(@PathVariable String customerId, Model model) {

		Customer customer = customerService.findCustomerById(customerId);

		if (customer == null) {

			return "redirect:/admin/customers";
		}

		List<Address> addresses = customerService.findCustomerAddresses(customerId);

		model.addAttribute("customer", customer);

		model.addAttribute("addresses", addresses);

		return "admin/customer-profile";
	}

	/**
	 * Deletes a customer.
	 *
	 * URL:
	 *
	 * /admin/customers/delete/{customerId}
	 */
	@PostMapping("/customers/delete/{customerId}")
	public String deleteCustomer(@PathVariable String customerId) {

		adminService.deleteCustomer(customerId);

		return "redirect:/admin/customers";
	}

	// =====================================================
	// SELLER MANAGEMENT
	// =====================================================

	/**
	 * Displays all sellers.
	 *
	 * URL:
	 *
	 * /admin/sellers
	 */
	@GetMapping("/sellers")
	public String viewAllSellers(Model model) {

		List<Seller> sellers = adminService.getAllSellers();

		model.addAttribute("sellers", sellers);

		return "admin/sellers";
	}

	/**
	 * Displays one seller's profile.
	 *
	 * Admin can only VIEW the seller.
	 *
	 * URL:
	 *
	 * /admin/seller/{sellerId}
	 */
	@GetMapping("/seller/{sellerId}")
	public String viewSellerProfile(@PathVariable String sellerId, Model model) {

		Seller seller = sellerService.findSellerById(sellerId);

		if (seller == null) {

			return "redirect:/admin/sellers";
		}

		model.addAttribute("seller", seller);

		return "admin/seller-profile";
	}

	/**
	 * Deletes a seller.
	 *
	 * URL:
	 *
	 * /admin/sellers/delete/{sellerId}
	 */
	@PostMapping("/sellers/delete/{sellerId}")
	public String deleteSeller(@PathVariable String sellerId) {

		adminService.deleteSeller(sellerId);

		return "redirect:/admin/sellers";
	}

	// =====================================================
	// ADMIN PROFILE
	// =====================================================

	/**
	 * Displays Admin Profile.
	 *
	 * URL:
	 *
	 * /admin/profile
	 */
	@GetMapping("/profile")
	public String viewProfile(HttpSession session, Model model) {

		String adminId = (String) session.getAttribute("userId");

		if (adminId == null) {

			return "redirect:/admin/login";
		}

		Admin admin = adminService.getAdminProfile(adminId);

		model.addAttribute("admin", admin);

		return "admin/profile";
	}

	// =====================================================
	// EDIT ADMIN PROFILE
	// =====================================================

	@GetMapping("/profile/edit")
	public String editProfile(HttpSession session, Model model) {

		String adminId = (String) session.getAttribute("userId");

		if (adminId == null) {

			return "redirect:/admin/login";
		}

		Admin admin = adminService.getAdminProfile(adminId);

		model.addAttribute("admin", admin);

		return "admin/edit-profile";
	}

	// =====================================================
	// UPDATE ADMIN PHONE
	// =====================================================

	@PostMapping("/profile/update")
	public String updateProfile(@ModelAttribute Admin admin, Model model) {

		try {

			adminService.updateAdminPhone(admin);

			return "redirect:/admin/profile";

		} catch (ValidationException exception) {

			model.addAttribute("error", exception.getMessage());

			model.addAttribute("admin", admin);

			return "admin/edit-profile";
		}
	}

	// =====================================================
	// CHANGE PASSWORD - SHOW PAGE
	// =====================================================

	@GetMapping("/profile/change-password")
	public String showChangePasswordPage(@RequestParam String adminId, Model model) {

		model.addAttribute("adminId", adminId);

		return "admin/change-password";
	}

	// =====================================================
	// CHANGE PASSWORD
	// =====================================================

	@PostMapping("/profile/change-password")
	public String changePassword(@RequestParam String adminId, @RequestParam String currentPassword,
			@RequestParam String newPassword, @RequestParam String confirmPassword, Model model) {

		try {

			adminService.changePassword(adminId, currentPassword, newPassword, confirmPassword);

			model.addAttribute("success", "Password changed successfully.");

			model.addAttribute("adminId", adminId);

			return "admin/change-password";

		} catch (ValidationException exception) {

			model.addAttribute("error", exception.getMessage());

			model.addAttribute("adminId", adminId);

			return "admin/change-password";
		}
	}

	// =====================================================
	// PRODUCT MANAGEMENT
	// =====================================================

	/**
	 * Displays all products.
	 *
	 * URL:
	 *
	 * /admin/products
	 */
	@GetMapping("/products")
	public String viewAllProducts(Model model) {

		List<Product> products = productService.findAllProducts();

		model.addAttribute("products", products);

		return "admin/products";
	}

	// =====================================================
	// ADD PRODUCT
	// =====================================================

	@GetMapping("/products/add")
	public String showAddProductForm(Model model) {

	    Product product = new Product();

	    // Product ID will be generated automatically
	    product.setProductId(
	            com.crimsonlogic.ecommerce.util.IdGenerator.generateId("PRO")
	    );

	    model.addAttribute("product", product);

	    // Load categories for dropdown
	    model.addAttribute(
	            "categories",
	            categoryService.findAllCategories()
	    );

	    // Admin is adding the product, so seller is NOT required
	    model.addAttribute("adminMode", true);

	    model.addAttribute(
	            "formAction",
	            "/admin/products/add"
	    );

	    model.addAttribute(
	            "cancelBackUrl",
	            "/admin/products"
	    );

	    return "product/product-form";
	}
	// =====================================================
	// CATEGORY MANAGEMENT
	// =====================================================

	@GetMapping("/categories")
	public String viewAllCategories(Model model) {

		List<Category> categories = categoryService.findAllCategories();

		model.addAttribute("categories", categories);
		model.addAttribute("adminMode", true);

		return "category/categories";
	}

	@GetMapping("/categories/add")
	public String showAddCategoryForm(Model model) {

		model.addAttribute("category", new Category());

		model.addAttribute("formAction", "/admin/categories/add");

		model.addAttribute("cancelBackUrl", "/admin/categories");

		model.addAttribute("adminMode", true);

		return "category/category-form";
	}

	@PostMapping("/categories/add")
	public String addCategory(@ModelAttribute Category category) {

		categoryService.insertCategory(category);

		return "redirect:/admin/categories";
	}

	@GetMapping("/categories/edit/{categoryId}")
	public String showEditCategoryForm(@PathVariable String categoryId, Model model) {

		Category category = categoryService.findCategoryById(categoryId);

		if (category == null) {

			return "redirect:/admin/categories";
		}

		model.addAttribute("category", category);

		model.addAttribute("formAction", "/admin/categories/edit");

		model.addAttribute("cancelBackUrl", "/admin/categories");

		model.addAttribute("adminMode", true);

		return "category/category-form";
	}

	@PostMapping("/categories/edit")
	public String updateCategory(@ModelAttribute Category category) {

		categoryService.updateCategory(category);

		return "redirect:/admin/categories";
	}

	@PostMapping("/categories/delete/{categoryId}")
	public String deleteCategory(@PathVariable String categoryId) {

		categoryService.deleteCategory(categoryId);

		return "redirect:/admin/categories";
	}

//=====================================================
//INVENTORY MANAGEMENT
//=====================================================

	@GetMapping("/inventory")
	public String viewAllInventory(Model model) {

		List<Inventory> inventoryList = inventoryService.findAllInventory();

		model.addAttribute("inventoryList", inventoryList);

		model.addAttribute("adminMode", true);

		return "inventory/inventories";
	}

	@GetMapping("/inventory/view/{inventoryId}")
	public String viewInventory(@PathVariable String inventoryId, Model model) {

		Inventory inventory = inventoryService.findInventoryById(inventoryId);

		if (inventory == null) {
			return "redirect:/admin/inventory";
		}

		model.addAttribute("inventory", inventory);

		return "inventory/inventory-details";
	}

	@PostMapping("/inventory/quantity/update")
	public String updateInventoryQuantity(@ModelAttribute Inventory inventory) {

		inventoryService.updateQuantity(inventory);

		return "redirect:/admin/inventory";
	}
	// =====================================================
	// REPORTS
	// =====================================================

	/**
	 * Displays the Reports page.
	 *
	 * URL:
	 *
	 * /admin/reports
	 */
	@GetMapping("/reports")
	public String showReports(Model model) {

	    ReportFilter filter = new ReportFilter();

	    model.addAttribute(
	            "filter",
	            filter
	    );

	    List<Seller> sellers =
	            sellerService.findAllSellers();

	    model.addAttribute(
	            "sellers",
	            sellers
	    );

	    List<Customer> customers =
	            customerService.findAllCustomers();

	    model.addAttribute(
	            "customers",
	            customers
	    );

	    List<Category> categories =
	            categoryService.findAllCategories();

	    model.addAttribute(
	            "categories",
	            categories
	    );

	    return "admin/reports";
	}

	/**
	 * Generates the selected report.
	 *
	 * URL:
	 *
	 * /admin/reports
	 */
	@PostMapping("/reports")
	public String generateReport(@ModelAttribute("filter") ReportFilter filter,

			@RequestParam("reportType") String reportType,

			Model model) {

		/*
		 * Keep the filter on the page so that the entered values remain available.
		 */

		model.addAttribute("filter", filter);

		model.addAttribute("reportType", reportType);
		
		// Reload sellers after POST
	    List<Seller> sellers =
	            sellerService.findAllSellers();

	    model.addAttribute(
	            "sellers",
	            sellers);

		// =================================================
		// SALES REPORT
		// =================================================

		if ("sales".equals(reportType)) {

			SalesReport report = reportService.getSalesReport(filter);

			model.addAttribute("salesReport", report);

			return "admin/reports";
		}

		// =================================================
		// PRODUCT SALES REPORT
		// =================================================

		if ("product".equals(reportType)) {

			List<ProductSalesReport> report = reportService.getProductSalesReport(filter);

			model.addAttribute("productSalesReport", report);

			return "admin/reports";
		}

		// =================================================
		// CATEGORY SALES REPORT
		// =================================================

		if ("category".equals(reportType)) {

			List<CategorySalesReport> report = reportService.getCategorySalesReport(filter);

			model.addAttribute("categorySalesReport", report);

			return "admin/reports";
		}

		// =================================================
		// SELLER SALES REPORT
		// =================================================

		if ("seller".equals(reportType)) {

			List<SellerSalesReport> report = reportService.getSellerSalesReport(filter);

			model.addAttribute("sellerSalesReport", report);

			return "admin/reports";
		}

		// =================================================
		// CUSTOMER REPORT
		// =================================================

		if ("customer".equals(reportType)) {

			CustomerReport report = reportService.getCustomerReport(filter);

			model.addAttribute("customerReport", report);

			return "admin/reports";
		}

		// =================================================
		// CUSTOMER PRODUCT REPORT
		// =================================================

		if ("customerProduct".equals(reportType)) {

			List<ProductSalesReport> report = reportService.getCustomerProductReport(filter);

			model.addAttribute("customerProductReport", report);

			return "admin/reports";
		}

		// =================================================
		// INVALID REPORT TYPE
		// =================================================

		model.addAttribute("error", "Invalid report type.");

		return "admin/reports";
	}

}