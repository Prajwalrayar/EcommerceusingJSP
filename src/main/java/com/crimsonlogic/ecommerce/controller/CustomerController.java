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

import com.crimsonlogic.ecommerce.model.Address;
import com.crimsonlogic.ecommerce.model.Cart;
import com.crimsonlogic.ecommerce.model.Customer;
import com.crimsonlogic.ecommerce.model.Product;
import com.crimsonlogic.ecommerce.service.AddressService;
import com.crimsonlogic.ecommerce.service.CartService;
import com.crimsonlogic.ecommerce.service.CategoryService;
import com.crimsonlogic.ecommerce.service.CustomerService;
import com.crimsonlogic.ecommerce.service.ProductService;
import com.crimsonlogic.ecommerce.service.SellerService;
import com.crimsonlogic.ecommerce.util.IdGenerator;


@Controller
@RequestMapping("/customer")
public class CustomerController {


    private final CustomerService customerService;

    private final AddressService addressService;

    private final ProductService productService;

    private final CartService cartService;
    
    private final CategoryService categoryService;
    
    private final SellerService sellerService;


    public CustomerController(
            CustomerService customerService,
            AddressService addressService,
            ProductService productService,
            CartService cartService,
            SellerService sellerService,
            CategoryService categoryService) {

        this.customerService =
                customerService;

        this.addressService =
                addressService;

        this.productService =
                productService;

        this.cartService =
                cartService;
        
        this.categoryService = categoryService;
        this.sellerService = sellerService;
    }


    // =====================================================
    // CUSTOMER DASHBOARD
    // =====================================================

    @GetMapping("/dashboard")
    public String customerDashboard(
            HttpSession session,
            Model model) {

        Customer customer =
                getLoggedInCustomer(session);

        if (customer == null) {

            return "redirect:/customer/login";
        }


        // =====================================================
        // REFRESH CUSTOMER FROM DATABASE
        // =====================================================

        Customer currentCustomer =
                customerService.findCustomerById(
                        customer.getUserId()
                );


        if (currentCustomer == null) {

            session.invalidate();

            return "redirect:/customer/login";
        }


        // =====================================================
        // UPDATE SESSION
        // =====================================================

        session.setAttribute(
                "loggedInUser",
                currentCustomer
        );


        // =====================================================
        // CUSTOMER
        // =====================================================

        model.addAttribute(
                "customer",
                currentCustomer
        );


        // =====================================================
        // WALLET BALANCE
        // =====================================================

        model.addAttribute(
                "walletBalance",
                currentCustomer.getWalletBalance()
        );


        // =====================================================
        // CART ITEMS
        // =====================================================

        List<Cart> cartItems =
                cartService.findCartByCustomer(
                        currentCustomer.getUserId()
                );


        model.addAttribute(
                "cartItems",
                cartItems
        );


        model.addAttribute(
                "cartItemCount",
                cartItems.size()
        );


        return "customer/dashboard";
    }


    // =====================================================
    // CUSTOMER PROFILE
    // =====================================================

    @GetMapping("/profile/{customerId}")
    public String viewCustomerProfile(
            @PathVariable String customerId,
            HttpSession session,
            Model model) {

        Customer loggedInCustomer =
                getLoggedInCustomer(session);


        if (loggedInCustomer == null) {

            return "redirect:/customer/login";
        }


        /*
         * A customer can view only his/her own profile.
         */
        if (!loggedInCustomer.getUserId()
                .equals(customerId)) {

            return "redirect:/customer/profile/"
                    + loggedInCustomer.getUserId();
        }


        Customer customer =
                customerService.findCustomerById(
                        customerId);


        if (customer == null) {

            return "redirect:/customer/dashboard";
        }


        List<Address> addresses =
                customerService.findCustomerAddresses(
                        customerId);


        model.addAttribute(
                "customer",
                customer);


        model.addAttribute(
                "addresses",
                addresses);


        return "customer/customer-profile";
    }


    // =====================================================
    // EDIT CUSTOMER PROFILE
    // =====================================================

    @GetMapping("/profile/edit/{customerId}")
    public String editProfile(
            @PathVariable String customerId,
            HttpSession session,
            Model model) {

        Customer loggedInCustomer =
                getLoggedInCustomer(session);


        if (loggedInCustomer == null) {

            return "redirect:/customer/login";
        }


        /*
         * A customer can edit only his/her own profile.
         */
        if (!loggedInCustomer.getUserId()
                .equals(customerId)) {

            return "redirect:/customer/profile/"
                    + loggedInCustomer.getUserId();
        }


        Customer customer =
                customerService.findCustomerById(
                        customerId);


        if (customer == null) {

            return "redirect:/customer/dashboard";
        }


        model.addAttribute(
                "customer",
                customer);


        return "customer/edit-profile";
    }


    // =====================================================
    // UPDATE CUSTOMER PROFILE
    // =====================================================

    @PostMapping("/profile/update")
    public String updateProfile(
            @ModelAttribute Customer customer,
            HttpSession session,
            Model model) {

        Customer loggedInCustomer =
                getLoggedInCustomer(session);


        if (loggedInCustomer == null) {

            return "redirect:/customer/login";
        }


        /*
         * Never trust a customer ID coming only from the form.
         *
         * The submitted ID must belong to the logged-in user.
         */
        if (customer.getUserId() == null ||
                !loggedInCustomer.getUserId()
                        .equals(customer.getUserId())) {

            return "redirect:/customer/profile/"
                    + loggedInCustomer.getUserId();
        }


        try {

            /*
             * All validation and business logic are handled
             * inside CustomerServiceImpl.
             */
            customerService.updateCustomer(
                    customer);


            /*
             * Reload the updated Customer.
             */
            Customer updatedCustomer =
                    customerService.findCustomerById(
                            loggedInCustomer.getUserId());


            /*
             * Update session with latest Customer data.
             */
            session.setAttribute(
                    "loggedInUser",
                    updatedCustomer);


            return "redirect:/customer/profile/"
                    + updatedCustomer.getUserId();

        } catch (RuntimeException exception) {

            /*
             * Validation/business rules remain in service layer.
             *
             * Controller only passes the service error to JSP.
             */
            model.addAttribute(
                    "error",
                    exception.getMessage());


            model.addAttribute(
                    "customer",
                    customer);


            return "customer/edit-profile";
        }
    }


    
    @GetMapping("/addresses")
    public String myAddresses(
            HttpSession session,
            Model model) {

        Customer customer =
                getLoggedInCustomer(session);

        if (customer == null) {
            return "redirect:/customer/login";
        }

        List<Address> addresses =
                customerService.findCustomerAddresses(
                        customer.getUserId()
                );

        model.addAttribute(
                "customer",
                customer
        );

        model.addAttribute(
                "addresses",
                addresses
        );

        return "address/addresses";
    }
    // =====================================================
    // SHOW ASSIGN ADDRESS PAGE
    // =====================================================

    @GetMapping("/{customerId}/addresses/add")
    public String showAddAddressPage(
            @PathVariable String customerId,
            HttpSession session,
            Model model) {

        Customer loggedInCustomer =
                getLoggedInCustomer(session);

        if (loggedInCustomer == null) {
            return "redirect:/customer/login";
        }

        if (!loggedInCustomer.getUserId()
                .equals(customerId)) {

            return "redirect:/customer/profile/"
                    + loggedInCustomer.getUserId();
        }

        Customer customer =
                customerService.findCustomerById(
                        customerId
                );

        if (customer == null) {
            return "redirect:/customer/dashboard";
        }

        model.addAttribute(
                "customer",
                customer
        );

        model.addAttribute(
                "address",
                new Address()
        );

        return "address/add-address";
    }

    
	 // =====================================================
	 // CUSTOMER WALLET
	 // =====================================================
	
	 @GetMapping("/wallet")
	 public String viewWallet(
	         HttpSession session,
	         Model model) {
	
	     Customer customer =
	             getLoggedInCustomer(session);
	
	     if (customer == null) {
	
	         return "redirect:/customer/login";
	     }
	
	
	     /*
	      * Always load the latest wallet balance
	      * from the database.
	      */
	     Customer freshCustomer =
	             customerService.findCustomerById(
	                     customer.getUserId());
	
	
	     if (freshCustomer == null) {
	
	         session.invalidate();
	
	         return "redirect:/customer/login";
	     }
	
	
	     model.addAttribute(
	             "customer",
	             freshCustomer);
	
	
	     return "customer/wallet";
	 }
    
	 
	// =====================================================
	// RECHARGE CUSTOMER WALLET
	// =====================================================

	@PostMapping("/wallet/recharge")
	public String rechargeWallet(
	        @RequestParam("amount") double amount,
	        @RequestParam("paymentMethod") String paymentMethod,
	        @RequestParam(value = "upiId", required = false) String upiId,
	        HttpSession session,
	        Model model) {

	    Customer customer =
	            getLoggedInCustomer(session);

	    if (customer == null) {

	        return "redirect:/customer/login";
	    }


	    try {

	        customerService.rechargeWallet(
	                customer.getUserId(),
	                amount,
	                paymentMethod,
	                upiId
	        );


	        return "redirect:/customer/wallet";

	    } catch (RuntimeException exception) {

	        Customer freshCustomer =
	                customerService.findCustomerById(
	                        customer.getUserId()
	                );


	        model.addAttribute(
	                "customer",
	                freshCustomer
	        );


	        model.addAttribute(
	                "error",
	                exception.getMessage()
	        );


	        return "customer/wallet";
	    }
	}

	// =====================================================
	// ADD ADDRESS
	// =====================================================

	@PostMapping("/{customerId}/addresses/add")
	public String addAddress(
	        @PathVariable String customerId,
	        @ModelAttribute Address address,
	        HttpSession session,
	        Model model) {

	    Customer loggedInCustomer =
	            getLoggedInCustomer(session);

	    if (loggedInCustomer == null) {
	        return "redirect:/customer/login";
	    }

	    if (!loggedInCustomer.getUserId()
	            .equals(customerId)) {

	        return "redirect:/customer/profile/"
	                + loggedInCustomer.getUserId();
	    }

	    try {

	        // -------------------------------------------------
	        // GENERATE ADDRESS ID
	        // -------------------------------------------------

	        address.setAddressId(
	                IdGenerator.generateId("ADDR")
	        );

	        // -------------------------------------------------
	        // INSERT ADDRESS
	        // -------------------------------------------------

	        addressService.insertAddress(address);

	        // -------------------------------------------------
	        // ASSIGN ADDRESS TO CUSTOMER
	        // -------------------------------------------------

	        customerService.assignAddress(
	                customerId,
	                address.getAddressId()
	        );

	        // -------------------------------------------------
	        // REDIRECT TO CHECKOUT
	        // -------------------------------------------------

	        return "redirect:/checkout/" + customerId;

	    } catch (RuntimeException exception) {

	        Customer customer =
	                customerService.findCustomerById(
	                        customerId
	                );

	        model.addAttribute(
	                "customer",
	                customer
	        );

	        model.addAttribute(
	                "address",
	                address
	        );

	        model.addAttribute(
	                "error",
	                exception.getMessage()
	        );

	        return "address/add-address";
	    }
	}


    // =====================================================
    // REMOVE ADDRESS
    // =====================================================

    @PostMapping(
            "/{customerId}/addresses/remove/{addressId}")
    public String removeAddress(
            @PathVariable String customerId,
            @PathVariable String addressId,
            HttpSession session) {

        Customer loggedInCustomer =
                getLoggedInCustomer(session);


        if (loggedInCustomer == null) {

            return "redirect:/customer/login";
        }


        /*
         * Customer can remove an address only from
         * his/her own account.
         */
        if (!loggedInCustomer.getUserId()
                .equals(customerId)) {

            return "redirect:/customer/profile/"
                    + loggedInCustomer.getUserId();
        }


        /*
         * Address ownership and validation are handled
         * by CustomerServiceImpl.
         */
        customerService.removeAddress(
                customerId,
                addressId);


        return "redirect:/customer/profile/"
                + customerId;
    }


    // =====================================================
    // VIEW PRODUCTS
    // =====================================================

    @GetMapping("/products")
    public String products(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String categoryId,
            @RequestParam(required = false) String sellerId,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            HttpSession session,
            Model model) {

        Customer customer =
                getLoggedInCustomer(session);

        if (customer == null) {
            return "redirect:/customer/login";
        }

        List<Product> products =
                productService.searchAvailableProducts(
                        keyword,
                        categoryId,
                        sellerId,
                        minPrice,
                        maxPrice
                );

        model.addAttribute("products", products);
        model.addAttribute("customer", customer);

        // These are only for displaying dropdown options.
        model.addAttribute(
                "categories",
                categoryService.findAllCategories()
        );

        model.addAttribute(
                "sellers",
                sellerService.findAllSellers()
        );

        model.addAttribute("keyword", keyword);
        model.addAttribute("selectedCategoryId", categoryId);
        model.addAttribute("selectedSellerId", sellerId);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);

        return "customer/products";
    }


    // =====================================================
    // ADD PRODUCT TO CART
    // =====================================================

    @PostMapping("/cart/add")
    public String addToCart(
            @RequestParam("productId") String productId,
            @RequestParam("quantity") int quantity,
            HttpSession session,
            Model model) {

        Customer customer =
                getLoggedInCustomer(session);


        if (customer == null) {

            return "redirect:/customer/login";
        }


        try {

            /*
             * IMPORTANT:
             *
             * The controller does NOT:
             *
             * - create Cart
             * - generate Cart ID
             * - find existing Cart
             * - calculate Cart quantity
             * - validate Product
             * - validate Inventory
             * - validate stock
             *
             * All of these business rules belong to
             * CartServiceImpl.
             *
             * The project uses Cart, NOT CartItem.
             */
            cartService.addToCart(
                    customer.getUserId(),
                    productId,
                    quantity);


            return "redirect:/customer/cart";

        } catch (RuntimeException exception) {

            /*
             * Business validation remains in service layer.
             */
            model.addAttribute(
                    "error",
                    exception.getMessage());


            /*
             * Return to product page so the customer can
             * correct the request.
             */
            List<Product> products =
                    productService.searchAvailableProducts(
                            null,
                            null,
                            null,
                            null,
                            null
                    );


            model.addAttribute(
                    "products",
                    products);


            model.addAttribute(
                    "customer",
                    customer);


            return "customer/products";
        }
    }


 // =====================================================
 // VIEW CUSTOMER CART
 // =====================================================

 @GetMapping("/cart")
 public String viewCart(
         HttpSession session,
         Model model) {

     Customer customer =
             getLoggedInCustomer(session);

     if (customer == null) {
         return "redirect:/customer/login";
     }

     List<com.crimsonlogic.ecommerce.model.Cart> cartItems =
             cartService.findCartByCustomer(
                     customer.getUserId()
             );

     model.addAttribute(
             "customer",
             customer
     );

     model.addAttribute(
             "cartItems",
             cartItems
     );

     return "cart/cart";
 }
 
 
    // =====================================================
    // GET LOGGED-IN CUSTOMER
    // =====================================================

    private Customer getLoggedInCustomer(
            HttpSession session) {

        Object loggedInUser =
                session.getAttribute(
                        "loggedInUser");


        if (!(loggedInUser instanceof Customer)) {

            return null;
        }


        return (Customer) loggedInUser;
    }
}