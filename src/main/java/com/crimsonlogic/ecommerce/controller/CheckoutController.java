package com.crimsonlogic.ecommerce.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crimsonlogic.ecommerce.exception.InsufficientWalletBalanceException;
import com.crimsonlogic.ecommerce.model.Address;
import com.crimsonlogic.ecommerce.model.Cart;
import com.crimsonlogic.ecommerce.model.Customer;
import com.crimsonlogic.ecommerce.service.AddressService;
import com.crimsonlogic.ecommerce.service.CartService;
import com.crimsonlogic.ecommerce.service.CheckoutService;
import com.crimsonlogic.ecommerce.service.CustomerService;

/**
 * Controller responsible for customer checkout operations.
 *
 * The controller handles HTTP requests and delegates checkout
 * business logic to CheckoutService.
 */
@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private final CustomerService customerService;

    private final CartService cartService;

    private final CheckoutService checkoutService;

    private AddressService addressService;

    /**
     * Creates CheckoutController.
     *
     * @param customerService customer service
     * @param cartService cart service
     * @param checkoutService checkout service
     */
    public CheckoutController(
            CustomerService customerService,
            CartService cartService,
            CheckoutService checkoutService,
            AddressService addressService) {

        this.customerService = customerService;

        this.cartService = cartService;

        this.checkoutService = checkoutService;
        
        this.addressService = addressService;
    }


    // =========================================================
    // SHOW CHECKOUT PAGE
    // =========================================================

    /**
     * Displays the checkout page.
     *
     * @param customerId customer ID
     * @param session current HTTP session
     * @param model MVC model
     * @return checkout JSP
     */
 // =========================================================
 // SHOW CHECKOUT PAGE
 // =========================================================

 @GetMapping("/{customerId}")
 public String checkout(
         @PathVariable String customerId,
         Model model) {

     // -----------------------------------------------------
     // FIND CUSTOMER
     // -----------------------------------------------------

     Customer customer =
             customerService.findCustomerById(customerId);

     if (customer == null) {
         return "redirect:/customer/login";
     }

     // -----------------------------------------------------
     // GET CUSTOMER CART
     // -----------------------------------------------------

     List<Cart> cartItems =
             cartService.findCartByCustomer(customerId);

     // -----------------------------------------------------
     // GET CUSTOMER ADDRESSES
     // -----------------------------------------------------

     List<Address> addresses =
             addressService.findAddressesByCustomer(
                     customerId
             );

     // -----------------------------------------------------
     // CALCULATE TOTAL AMOUNT
     // -----------------------------------------------------

     double totalAmount = 0.0;

     if (cartItems != null) {

         for (Cart cart : cartItems) {

             if (cart != null &&
                     cart.getProduct() != null) {

                 totalAmount += cart.getTotalPrice();
             }
         }
     }

     // -----------------------------------------------------
     // ADD DATA TO MODEL
     // -----------------------------------------------------

     model.addAttribute(
             "customer",
             customer
     );

     model.addAttribute(
             "cartItems",
             cartItems
     );

     model.addAttribute(
             "addresses",
             addresses
     );

     model.addAttribute(
             "totalAmount",
             totalAmount
     );

     // -----------------------------------------------------
     // WALLET BALANCE
     // -----------------------------------------------------

     model.addAttribute(
             "walletBalance",
             customer.getWalletBalance()
     );

     // -----------------------------------------------------
     // RETURN CHECKOUT JSP
     // -----------------------------------------------------

     return "checkout/checkout";
 }


    // =========================================================
    // CONFIRM ORDER
    // =========================================================

    /**
     * Confirms checkout and delegates the complete checkout
     * operation to CheckoutService.
     *
     * @param customerId customer ID
     * @param addressId selected address ID
     * @param paymentMethod selected payment method
     * @param upiId UPI ID
     * @param session current HTTP session
     * @param redirectAttributes redirect attributes
     * @return redirect URL
     */
    @PostMapping("/{customerId}/confirm")
    public String confirmOrder(
            @PathVariable String customerId,

            @RequestParam("addressId")
            String addressId,

            @RequestParam("paymentMethod")
            String paymentMethod,

            @RequestParam(
                    value = "upiId",
                    required = false)
            String upiId,

            HttpSession session,

            RedirectAttributes redirectAttributes) {


        // -----------------------------------------------------
        // LOGIN CHECK
        // -----------------------------------------------------

        Customer loggedInCustomer =
                (Customer) session.getAttribute(
                        "loggedInUser"
                );

        if (loggedInCustomer == null) {

            return "redirect:/customer/login";
        }


        // -----------------------------------------------------
        // CUSTOMER ACCESS CHECK
        // -----------------------------------------------------

        if (!loggedInCustomer.getUserId()
                .equals(customerId)) {

            return "redirect:/customer/dashboard";
        }


        try {

            // -------------------------------------------------
            // DELEGATE BUSINESS LOGIC TO SERVICE
            // -------------------------------------------------

            checkoutService.placeOrder(
                    customerId,
                    addressId,
                    paymentMethod,
                    upiId
            );


            redirectAttributes.addFlashAttribute(
                    "success",
                    "Order placed successfully."
            );


            return "redirect:/orders/customer/"
                    + customerId;


        } catch (InsufficientWalletBalanceException exception) {

            redirectAttributes.addFlashAttribute(
                    "walletInsufficient",
                    true
            );

            redirectAttributes.addFlashAttribute(
                    "walletBalance",
                    exception.getWalletBalance()
            );

            redirectAttributes.addFlashAttribute(
                    "remainingAmount",
                    exception.getRemainingAmount()
            );

            redirectAttributes.addFlashAttribute(
                    "orderAmount",
                    exception.getOrderAmount()
            );

            return "redirect:/checkout/"
                    + customerId;

        } catch (RuntimeException exception) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    exception.getMessage()
            );

            return "redirect:/checkout/"
                    + customerId;
        }
    }


    // =========================================================
    // CALCULATE DISPLAY TOTAL
    // =========================================================

    /**
     * Calculates the total displayed on the checkout page.
     *
     * This is only used for displaying the checkout summary.
     *
     * The actual order creation and payment business logic
     * is handled by CheckoutService.
     *
     * @param cartItems customer cart items
     * @return total cart amount
     */
    private double calculateTotal(
            List<Cart> cartItems) {

        double total = 0;

        for (Cart cart : cartItems) {

            if (cart != null
                    && cart.getProduct() != null) {

                total +=
                        cart.getProduct()
                                .getProductPrice()
                                * cart.getQuantity();
            }
        }

        return total;
    }
    
 // =========================================================
 // RECHARGE WALLET FROM CHECKOUT
 // =========================================================

 @PostMapping("/{customerId}/wallet/recharge")
 public String rechargeWalletFromCheckout(

         @PathVariable String customerId,

         @RequestParam("amount")
         double amount,

         @RequestParam("paymentMethod")
         String paymentMethod,

         @RequestParam(
                 value = "upiId",
                 required = false)
         String upiId,

         HttpSession session,

         RedirectAttributes redirectAttributes) {

     // -----------------------------------------------------
     // LOGIN CHECK
     // -----------------------------------------------------

     Customer loggedInCustomer =
             (Customer) session.getAttribute(
                     "loggedInUser");

     if (loggedInCustomer == null) {

         return "redirect:/customer/login";
     }

     // -----------------------------------------------------
     // CUSTOMER ACCESS CHECK
     // -----------------------------------------------------

     if (!loggedInCustomer.getUserId()
             .equals(customerId)) {

         return "redirect:/customer/dashboard";
     }

     // -----------------------------------------------------
     // VALIDATE AMOUNT
     // -----------------------------------------------------

     if (amount <= 0) {

         redirectAttributes.addFlashAttribute(
                 "error",
                 "Recharge amount must be greater than zero."
         );

         return "redirect:/checkout/"
                 + customerId;
     }

     try {

         // -------------------------------------------------
         // RECHARGE WALLET
         // -------------------------------------------------

         customerService.rechargeWallet(
                 customerId,
                 amount,
                 paymentMethod,
                 upiId
         );

         redirectAttributes.addFlashAttribute(
                 "success",
                 "Wallet recharged successfully. You can now complete your order payment."
         );

     } catch (RuntimeException exception) {

         redirectAttributes.addFlashAttribute(
                 "error",
                 exception.getMessage()
         );
     }

     return "redirect:/checkout/"
             + customerId;
 }
}