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

/**
 * Controller responsible for handling administrator-related requests.
 *
 * The controller manages administrative operations such as dashboard
 * information, customer management, seller management, product management,
 * category management, inventory management, profile management, and reports.
 *
 * The controller delegates business operations to the appropriate service
 * layer instead of directly accessing the database.
 */
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

	/**
	 * Creates the AdminController with all required service dependencies.
	 *
	 * Constructor injection allows Spring to provide the service instances
	 * required for administrator operations.
	 *
	 * @param adminService service used for administrator and administrative customer/seller operations
	 * @param customerService service used for customer-related operations
	 * @param sellerService service used for seller-related operations
	 * @param productService service used for product-related operations
	 * @param reportService service used for generating reports
	 * @param orderService service used for order-related operations
	 * @param categoryService service used for category-related operations
	 * @param inventoryService service used for inventory-related operations
	 */
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

	/**
	 * Displays the administrator dashboard.
	 *
	 * The dashboard gathers summary information about customers, sellers,
	 * products, orders, revenue, and pending orders and makes these values
	 * available to the dashboard view.
	 *
	 * HTTP method: GET
	 * Endpoint: /admin/dashboard
	 *
	 * @param model model used to pass dashboard information to the view
	 * @return administrator dashboard view
	 */
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

		// Use zero revenue when the sales report is not available.
		double totalRevenue = (salesReport != null) ? salesReport.getTotalSales() : 0.0;

		// 6. Pending Orders
		// Count orders whose status contains the PENDING status value.
		long pendingOrders = allOrders.stream()
				.filter(o -> o.getOrderStatus() != null && (o.getOrderStatus().name().contains("PENDING"))).count();

		// Add all dashboard summary values to the model for the JSP view.
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
	 * The administrator can view the complete list of registered customers.
	 *
	 * URL:
	 *
	 * /admin/customers
	 *
	 * @param model model used to pass customer data to the view
	 * @return customer management view
	 */
	@GetMapping("/customers")
	public String viewAllCustomers(Model model) {

		// Retrieve all customers through the administrator service.
		List<Customer> customers = adminService.getAllCustomers();

		// Make the customer list available to the JSP view.
		model.addAttribute("customers", customers);

		return "admin/customers";
	}

	/**
	 * Displays one customer's profile.
	 *
	 * Admin can only VIEW the customer.
	 *
	 * The customer's profile and associated addresses are retrieved
	 * and made available to the profile view.
	 *
	 * URL:
	 *
	 * /admin/customer/{customerId}
	 *
	 * @param customerId customer ID supplied in the URL
	 * @param model model used to pass customer information to the view
	 * @return customer profile view or customer list when the customer is not found
	 */
	@GetMapping("/customer/{customerId}")
	public String viewCustomerProfile(@PathVariable String customerId, Model model) {

		// Retrieve the customer using the ID supplied in the URL.
		Customer customer = customerService.findCustomerById(customerId);

		if (customer == null) {

			// Redirect to the customer list when the requested customer does not exist.
			return "redirect:/admin/customers";
		}

		// Retrieve all addresses associated with the selected customer.
		List<Address> addresses = customerService.findCustomerAddresses(customerId);

		// Add the customer and address information to the model.
		model.addAttribute("customer", customer);

		model.addAttribute("addresses", addresses);

		return "admin/customer-profile";
	}

	/**
	 * Deletes a customer.
	 *
	 * The customer ID is passed to the service layer so that the
	 * corresponding customer record can be removed.
	 *
	 * URL:
	 *
	 * /admin/customers/delete/{customerId}
	 *
	 * @param customerId customer ID supplied in the URL
	 * @return redirect to the customer list after deletion
	 */
	@PostMapping("/customers/delete/{customerId}")
	public String deleteCustomer(@PathVariable String customerId) {

		// Delegate customer deletion to the administrator service.
		adminService.deleteCustomer(customerId);

		// Redirect to the customer list after the deletion is completed.
		return "redirect:/admin/customers";
	}

	// =====================================================
	// SELLER MANAGEMENT
	// =====================================================

	/**
	 * Displays all sellers.
	 *
	 * The administrator can view the complete list of registered sellers.
	 *
	 * URL:
	 *
	 * /admin/sellers
	 *
	 * @param model model used to pass seller data to the view
	 * @return seller management view
	 */
	@GetMapping("/sellers")
	public String viewAllSellers(Model model) {

		// Retrieve all sellers through the administrator service.
		List<Seller> sellers = adminService.getAllSellers();

		// Make the seller list available to the JSP view.
		model.addAttribute("sellers", sellers);

		return "admin/sellers";
	}

	/**
	 * Displays one seller's profile.
	 *
	 * Admin can only VIEW the seller.
	 *
	 * The seller is retrieved using the ID supplied in the URL.
	 *
	 * URL:
	 *
	 * /admin/seller/{sellerId}
	 *
	 * @param sellerId seller ID supplied in the URL
	 * @param model model used to pass seller information to the view
	 * @return seller profile view or seller list when the seller is not found
	 */
	@GetMapping("/seller/{sellerId}")
	public String viewSellerProfile(@PathVariable String sellerId, Model model) {

		// Retrieve the seller using the ID supplied in the URL.
		Seller seller = sellerService.findSellerById(sellerId);

		if (seller == null) {

			// Redirect to the seller list when the requested seller does not exist.
			return "redirect:/admin/sellers";
		}

		// Make the selected seller available to the profile view.
		model.addAttribute("seller", seller);

		return "admin/seller-profile";
	}

	/**
	 * Deletes a seller.
	 *
	 * The seller ID is passed to the service layer so that the
	 * corresponding seller record can be removed.
	 *
	 * URL:
	 *
	 * /admin/sellers/delete/{sellerId}
	 *
	 * @param sellerId seller ID supplied in the URL
	 * @return redirect to the seller list after deletion
	 */
	@PostMapping("/sellers/delete/{sellerId}")
	public String deleteSeller(@PathVariable String sellerId) {

		// Delegate seller deletion to the administrator service.
		adminService.deleteSeller(sellerId);

		// Redirect to the seller list after the deletion is completed.
		return "redirect:/admin/sellers";
	}

	// =====================================================
	// ADMIN PROFILE
	// =====================================================

	/**
	 * Displays Admin Profile.
	 *
	 * The administrator ID is retrieved from the current HTTP session
	 * and used to load the administrator profile.
	 *
	 * URL:
	 *
	 * /admin/profile
	 *
	 * @param session current HTTP session containing the logged-in administrator ID
	 * @param model model used to pass administrator information to the view
	 * @return administrator profile view or login page when no administrator session exists
	 */
	@GetMapping("/profile")
	public String viewProfile(HttpSession session, Model model) {

		// Retrieve the logged-in administrator ID from the HTTP session.
		String adminId = (String) session.getAttribute("userId");

		if (adminId == null) {

			// Redirect to login when no administrator is associated with the session.
			return "redirect:/admin/login";
		}

		// Retrieve the administrator profile using the session user ID.
		Admin admin = adminService.getAdminProfile(adminId);

		// Make the administrator profile available to the view.
		model.addAttribute("admin", admin);

		return "admin/profile";
	}

	// =====================================================
	// EDIT ADMIN PROFILE
	// =====================================================

	/**
	 * Displays the administrator profile edit page.
	 *
	 * The administrator ID is obtained from the session and used
	 * to retrieve the current profile information.
	 *
	 * HTTP method: GET
	 * Endpoint: /admin/profile/edit
	 *
	 * @param session current HTTP session containing the logged-in administrator ID
	 * @param model model used to pass administrator information to the form
	 * @return administrator profile edit view or login page when no session exists
	 */
	@GetMapping("/profile/edit")
	public String editProfile(HttpSession session, Model model) {

		// Retrieve the logged-in administrator ID from the HTTP session.
		String adminId = (String) session.getAttribute("userId");

		if (adminId == null) {

			// Redirect to login when the administrator is not authenticated.
			return "redirect:/admin/login";
		}

		// Retrieve the current administrator profile for editing.
		Admin admin = adminService.getAdminProfile(adminId);

		// Make the administrator information available to the edit form.
		model.addAttribute("admin", admin);

		return "admin/edit-profile";
	}

	// =====================================================
	// UPDATE ADMIN PHONE
	// =====================================================

	/**
	 * Updates the administrator's phone information.
	 *
	 * ValidationException is handled here so that the error message
	 * can be displayed again on the profile edit page.
	 *
	 * HTTP method: POST
	 * Endpoint: /admin/profile/update
	 *
	 * @param admin administrator information submitted from the form
	 * @param model model used to pass validation errors and administrator data to the view
	 * @return redirect to profile after successful update or edit profile view when validation fails
	 */
	@PostMapping("/profile/update")
	public String updateProfile(@ModelAttribute Admin admin, Model model) {

		try {

			// Delegate administrator phone update and validation to the service layer.
			adminService.updateAdminPhone(admin);

			// Redirect to the profile page after a successful update.
			return "redirect:/admin/profile";

		} catch (ValidationException exception) {

			// Display the validation error on the profile edit page.
			model.addAttribute("error", exception.getMessage());

			// Preserve the submitted administrator data so the form can be displayed again.
			model.addAttribute("admin", admin);

			return "admin/edit-profile";
		}
	}

	// =====================================================
	// CHANGE PASSWORD - SHOW PAGE
	// =====================================================

	/**
	 * Displays the administrator change-password page.
	 *
	 * The administrator ID is passed as a request parameter so that
	 * the password change form knows which administrator is being updated.
	 *
	 * HTTP method: GET
	 * Endpoint: /admin/profile/change-password
	 *
	 * @param adminId administrator ID supplied as a request parameter
	 * @param model model used to pass the administrator ID to the view
	 * @return change-password view
	 */
	@GetMapping("/profile/change-password")
	public String showChangePasswordPage(@RequestParam String adminId, Model model) {

		// Provide the administrator ID to the password change form.
		model.addAttribute("adminId", adminId);

		return "admin/change-password";
	}

	// =====================================================
	// CHANGE PASSWORD
	// =====================================================

	/**
	 * Changes the administrator password.
	 *
	 * The current password, new password, and confirmation password
	 * are passed to the service layer for validation and processing.
	 *
	 * ValidationException is handled so that the appropriate error
	 * message can be displayed on the same page.
	 *
	 * HTTP method: POST
	 * Endpoint: /admin/profile/change-password
	 *
	 * @param adminId administrator ID supplied as a request parameter
	 * @param currentPassword current administrator password
	 * @param newPassword new password requested by the administrator
	 * @param confirmPassword confirmation of the new password
	 * @param model model used to pass success or error information to the view
	 * @return change-password view
	 */
	@PostMapping("/profile/change-password")
	public String changePassword(@RequestParam String adminId, @RequestParam String currentPassword,
			@RequestParam String newPassword, @RequestParam String confirmPassword, Model model) {

		try {

			// Delegate password validation and update processing to the service layer.
			adminService.changePassword(adminId, currentPassword, newPassword, confirmPassword);

			// Display a success message after the password is changed successfully.
			model.addAttribute("success", "Password changed successfully.");

			// Preserve the administrator ID for the change-password page.
			model.addAttribute("adminId", adminId);

			return "admin/change-password";

		} catch (ValidationException exception) {

			// Display the validation error returned by the service layer.
			model.addAttribute("error", exception.getMessage());

			// Preserve the administrator ID so the form remains associated with the administrator.
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
	 * The administrator can view the complete list of products
	 * available in the ecommerce application.
	 *
	 * URL:
	 *
	 * /admin/products
	 *
	 * @param model model used to pass product data to the view
	 * @return product management view
	 */
	@GetMapping("/products")
	public String viewAllProducts(Model model) {

		// Retrieve all products through the product service.
		List<Product> products = productService.findAllProducts();

		// Make the product list available to the JSP view.
		model.addAttribute("products", products);

		return "admin/products";
	}

	// =====================================================
	// ADD PRODUCT
	// =====================================================

	/**
	 * Displays the add-product form for the administrator.
	 *
	 * A new Product object is created and assigned a generated product ID.
	 * Categories are also loaded so that the administrator can select
	 * the appropriate category from the form.
	 *
	 * HTTP method: GET
	 * Endpoint: /admin/products/add
	 *
	 * @param model model used to provide product form data to the view
	 * @return common product form view
	 */
	@GetMapping("/products/add")
	public String showAddProductForm(Model model) {

	    // Create a new Product object that will hold the submitted form data.
	    Product product = new Product();

	    // Product ID will be generated automatically
	    product.setProductId(
	            com.crimsonlogic.ecommerce.util.IdGenerator.generateId("PRO")
	    );

	    // Add the new product to the model for form data binding.
	    model.addAttribute("product", product);

	    // Load categories for dropdown
	    model.addAttribute(
	            "categories",
	            categoryService.findAllCategories()
	    );

	    // Admin is adding the product, so seller is NOT required
	    model.addAttribute("adminMode", true);

	    // Provide the form submission URL to the shared product form.
	    model.addAttribute(
	            "formAction",
	            "/admin/products/add"
	    );

	    // Provide the URL used when the administrator cancels product creation.
	    model.addAttribute(
	            "cancelBackUrl",
	            "/admin/products"
	    );

	    return "product/product-form";
	}

	// =====================================================
	// CATEGORY MANAGEMENT
	// =====================================================

	/**
	 * Displays all product categories.
	 *
	 * The administrator can view the available categories and perform
	 * category management operations from the category management page.
	 *
	 * HTTP method: GET
	 * Endpoint: /admin/categories
	 *
	 * @param model model used to pass category information to the view
	 * @return category management view
	 */
	@GetMapping("/categories")
	public String viewAllCategories(Model model) {

		// Retrieve all categories through the category service.
		List<Category> categories = categoryService.findAllCategories();

		// Make the category list available to the view.
		model.addAttribute("categories", categories);

		// Indicate that the shared category page is being accessed by an administrator.
		model.addAttribute("adminMode", true);

		return "category/categories";
	}

	/**
	 * Displays the add-category form.
	 *
	 * A new Category object is supplied to the form so that submitted
	 * category information can be bound to it.
	 *
	 * HTTP method: GET
	 * Endpoint: /admin/categories/add
	 *
	 * @param model model used to provide category form information
	 * @return category form view
	 */
	@GetMapping("/categories/add")
	public String showAddCategoryForm(Model model) {

		// Provide an empty Category object for form data binding.
		model.addAttribute("category", new Category());

		// Provide the URL where the category form will be submitted.
		model.addAttribute("formAction", "/admin/categories/add");

		// Provide the URL used when the administrator cancels the operation.
		model.addAttribute("cancelBackUrl", "/admin/categories");

		// Indicate that the shared category form is being used in administrator mode.
		model.addAttribute("adminMode", true);

		return "category/category-form";
	}

	/**
	 * Adds a new category.
	 *
	 * The submitted category is passed to the service layer for insertion.
	 *
	 * HTTP method: POST
	 * Endpoint: /admin/categories/add
	 *
	 * @param category category information submitted from the form
	 * @return redirect to the category list after insertion
	 */
	@PostMapping("/categories/add")
	public String addCategory(@ModelAttribute Category category) {

		// Delegate category insertion to the category service.
		categoryService.insertCategory(category);

		return "redirect:/admin/categories";
	}

	/**
	 * Displays the edit-category form.
	 *
	 * The category is retrieved using the supplied category ID.
	 * If the category does not exist, the administrator is redirected
	 * back to the category list.
	 *
	 * HTTP method: GET
	 * Endpoint: /admin/categories/edit/{categoryId}
	 *
	 * @param categoryId category ID supplied in the URL
	 * @param model model used to pass category information to the view
	 * @return category edit form or category list when the category is not found
	 */
	@GetMapping("/categories/edit/{categoryId}")
	public String showEditCategoryForm(@PathVariable String categoryId, Model model) {

		// Retrieve the existing category using the supplied category ID.
		Category category = categoryService.findCategoryById(categoryId);

		if (category == null) {

			// Redirect to the category list when the category does not exist.
			return "redirect:/admin/categories";
		}

		// Make the existing category available to the edit form.
		model.addAttribute("category", category);

		// Provide the URL where the edited category will be submitted.
		model.addAttribute("formAction", "/admin/categories/edit");

		// Provide the URL used when the administrator cancels the edit operation.
		model.addAttribute("cancelBackUrl", "/admin/categories");

		// Indicate that the shared category form is being used in administrator mode.
		model.addAttribute("adminMode", true);

		return "category/category-form";
	}

	/**
	 * Updates an existing category.
	 *
	 * The submitted category information is passed to the service layer
	 * so that the existing category can be updated.
	 *
	 * HTTP method: POST
	 * Endpoint: /admin/categories/edit
	 *
	 * @param category updated category information submitted from the form
	 * @return redirect to the category list after the update
	 */
	@PostMapping("/categories/edit")
	public String updateCategory(@ModelAttribute Category category) {

		// Delegate category update processing to the service layer.
		categoryService.updateCategory(category);

		return "redirect:/admin/categories";
	}

	/**
	 * Deletes a category.
	 *
	 * The category ID is passed to the service layer for deletion.
	 *
	 * HTTP method: POST
	 * Endpoint: /admin/categories/delete/{categoryId}
	 *
	 * @param categoryId category ID supplied in the URL
	 * @return redirect to the category list after deletion
	 */
	@PostMapping("/categories/delete/{categoryId}")
	public String deleteCategory(@PathVariable String categoryId) {

		// Delegate category deletion to the category service.
		categoryService.deleteCategory(categoryId);

		return "redirect:/admin/categories";
	}

	//=====================================================
	//INVENTORY MANAGEMENT
	//=====================================================

	/**
	 * Displays all inventory records.
	 *
	 * The administrator can view the current inventory information
	 * maintained by the application.
	 *
	 * HTTP method: GET
	 * Endpoint: /admin/inventory
	 *
	 * @param model model used to pass inventory data to the view
	 * @return inventory list view
	 */
	@GetMapping("/inventory")
	public String viewAllInventory(Model model) {

		// Retrieve all inventory records through the inventory service.
		List<Inventory> inventoryList = inventoryService.findAllInventory();

		// Make the inventory records available to the JSP view.
		model.addAttribute("inventoryList", inventoryList);

		// Indicate that the inventory page is being accessed by an administrator.
		model.addAttribute("adminMode", true);

		return "inventory/inventories";
	}

	/**
	 * Displays the details of a specific inventory record.
	 *
	 * The inventory ID is used to retrieve the corresponding inventory.
	 * When the inventory does not exist, the administrator is redirected
	 * to the inventory list.
	 *
	 * HTTP method: GET
	 * Endpoint: /admin/inventory/view/{inventoryId}
	 *
	 * @param inventoryId inventory ID supplied in the URL
	 * @param model model used to pass inventory information to the view
	 * @return inventory details view or inventory list when the record is not found
	 */
	@GetMapping("/inventory/view/{inventoryId}")
	public String viewInventory(@PathVariable String inventoryId, Model model) {

		// Retrieve the inventory record using the supplied inventory ID.
		Inventory inventory = inventoryService.findInventoryById(inventoryId);

		if (inventory == null) {

			// Redirect to the inventory list when the requested record does not exist.
			return "redirect:/admin/inventory";
		}

		// Make the selected inventory record available to the details view.
		model.addAttribute("inventory", inventory);

		return "inventory/inventory-details";
	}

	/**
	 * Updates the quantity of an inventory record.
	 *
	 * The submitted inventory object is passed to the service layer
	 * so that the inventory quantity can be updated.
	 *
	 * HTTP method: POST
	 * Endpoint: /admin/inventory/quantity/update
	 *
	 * @param inventory inventory information submitted from the form
	 * @return redirect to the inventory list after the update
	 */
	@PostMapping("/inventory/quantity/update")
	public String updateInventoryQuantity(@ModelAttribute Inventory inventory) {

		// Delegate inventory quantity update processing to the service layer.
		inventoryService.updateQuantity(inventory);

		return "redirect:/admin/inventory";
	}
	// =====================================================
	// REPORTS
	// =====================================================

	/**
	 * Displays the Reports page.
	 *
	 * The page is initialized with an empty filter and the available
	 * sellers, customers, and categories so that the administrator
	 * can select the required report criteria.
	 *
	 * URL:
	 *
	 * /admin/reports
	 *
	 * @param model model used to pass report filter and selection data to the view
	 * @return administrator reports view
	 */
	@GetMapping("/reports")
	public String showReports(Model model) {

	    // Create an empty filter for the initial reports page.
	    ReportFilter filter = new ReportFilter();

	    model.addAttribute(
	            "filter",
	            filter
	    );

	    // Load sellers so that the administrator can filter reports by seller.
	    List<Seller> sellers =
	            sellerService.findAllSellers();

	    model.addAttribute(
	            "sellers",
	            sellers
	    );

	    // Load customers so that the administrator can filter reports by customer.
	    List<Customer> customers =
	            customerService.findAllCustomers();

	    model.addAttribute(
	            "customers",
	            customers
	    );

	    // Load categories so that the administrator can filter reports by category.
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
	 * The report type determines which report service method is executed.
	 * The selected filter and supporting seller information are retained
	 * in the model so that the report page can continue to display
	 * the selected criteria.
	 *
	 * URL:
	 *
	 * /admin/reports
	 *
	 * @param filter report filter submitted from the report form
	 * @param reportType type of report requested by the administrator
	 * @param model model used to pass report results and form data to the view
	 * @return administrator reports view
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

	        // Generate the overall sales report using the selected filter.
	        SalesReport report = reportService.getSalesReport(filter);

	        model.addAttribute("salesReport", report);

	        return "admin/reports";
	    }

	    // =================================================
	    // PRODUCT SALES REPORT
	    // =================================================

	    if ("product".equals(reportType)) {

	        // Generate sales information grouped by product.
	        List<ProductSalesReport> report = reportService.getProductSalesReport(filter);

	        model.addAttribute("productSalesReport", report);

	        return "admin/reports";
	    }

	    // =================================================
	    // CATEGORY SALES REPORT
	    // =================================================

	    if ("category".equals(reportType)) {

	        // Generate sales information grouped by category.
	        List<CategorySalesReport> report = reportService.getCategorySalesReport(filter);

	        model.addAttribute("categorySalesReport", report);

	        return "admin/reports";
	    }

	    // =================================================
	    // SELLER SALES REPORT
	    // =================================================

	    if ("seller".equals(reportType)) {

	        // Generate sales information grouped by seller.
	        List<SellerSalesReport> report = reportService.getSellerSalesReport(filter);

	        model.addAttribute("sellerSalesReport", report);

	        return "admin/reports";
	    }

	    // =================================================
	    // CUSTOMER REPORT
	    // =================================================

	    if ("customer".equals(reportType)) {

	        // Generate the customer-specific report using the selected filter.
	        CustomerReport report = reportService.getCustomerReport(filter);

	        model.addAttribute("customerReport", report);

	        return "admin/reports";
	    }

	    // =================================================
	    // CUSTOMER PRODUCT REPORT
	    // =================================================

	    if ("customerProduct".equals(reportType)) {

	        // Generate product sales information for the selected customer criteria.
	        List<ProductSalesReport> report = reportService.getCustomerProductReport(filter);

	        model.addAttribute("customerProductReport", report);

	        return "admin/reports";
	    }

	    // =================================================
	    // INVALID REPORT TYPE
	    // =================================================

	    // Display an error when the submitted report type is not supported.
	    model.addAttribute("error", "Invalid report type.");

	    return "admin/reports";
	}

}