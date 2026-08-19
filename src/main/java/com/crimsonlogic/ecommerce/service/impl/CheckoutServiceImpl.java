package com.crimsonlogic.ecommerce.service.impl;

import com.crimsonlogic.ecommerce.enumeration.OrderStatus;
import com.crimsonlogic.ecommerce.enumeration.PaymentMethod;
import com.crimsonlogic.ecommerce.enumeration.PaymentStatus;
import com.crimsonlogic.ecommerce.exception.InsufficientWalletBalanceException;
import com.crimsonlogic.ecommerce.model.Address;
import com.crimsonlogic.ecommerce.model.Cart;
import com.crimsonlogic.ecommerce.model.Customer;
import com.crimsonlogic.ecommerce.model.Inventory;
import com.crimsonlogic.ecommerce.model.Order;
import com.crimsonlogic.ecommerce.model.Payment;
import com.crimsonlogic.ecommerce.model.Product;
import com.crimsonlogic.ecommerce.service.AddressService;
import com.crimsonlogic.ecommerce.service.CartService;
import com.crimsonlogic.ecommerce.service.CheckoutService;
import com.crimsonlogic.ecommerce.service.CustomerService;
import com.crimsonlogic.ecommerce.service.InventoryService;
import com.crimsonlogic.ecommerce.service.OrderService;
import com.crimsonlogic.ecommerce.service.PaymentService;
import com.crimsonlogic.ecommerce.util.IdGenerator;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of CheckoutService.
 *
 * This class contains the complete business logic
 * required to complete the customer checkout process.
 */
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerService customerService;

    private AddressService addressService;

    private CartService cartService;

    private OrderService orderService;

    private PaymentService paymentService;

    private InventoryService inventoryService;


    // =========================================================
    // SET CUSTOMER SERVICE
    // =========================================================

    /**
     * Sets CustomerService.
     *
     * @param customerService customer service
     */
    public void setCustomerService(
            CustomerService customerService) {

        this.customerService = customerService;
    }


    // =========================================================
    // SET ADDRESS SERVICE
    // =========================================================

    /**
     * Sets AddressService.
     *
     * @param addressService address service
     */
    public void setAddressService(
            AddressService addressService) {

        this.addressService = addressService;
    }


    // =========================================================
    // SET CART SERVICE
    // =========================================================

    /**
     * Sets CartService.
     *
     * @param cartService cart service
     */
    public void setCartService(
            CartService cartService) {

        this.cartService = cartService;
    }


    // =========================================================
    // SET ORDER SERVICE
    // =========================================================

    /**
     * Sets OrderService.
     *
     * @param orderService order service
     */
    public void setOrderService(
            OrderService orderService) {

        this.orderService = orderService;
    }


    // =========================================================
    // SET PAYMENT SERVICE
    // =========================================================

    /**
     * Sets PaymentService.
     *
     * @param paymentService payment service
     */
    public void setPaymentService(
            PaymentService paymentService) {

        this.paymentService = paymentService;
    }


    // =========================================================
    // SET INVENTORY SERVICE
    // =========================================================

    /**
     * Sets InventoryService.
     *
     * @param inventoryService inventory service
     */
    public void setInventoryService(
            InventoryService inventoryService) {

        this.inventoryService = inventoryService;
    }


    // =========================================================
    // PLACE ORDER
    // =========================================================

    /**
     * Completes the checkout process.
     *
     * The method:
     *
     * 1. Validates customer.
     * 2. Validates address.
     * 3. Verifies that the address belongs to customer.
     * 4. Retrieves customer cart.
     * 5. Calculates total amount.
     * 6. Validates payment method.
     * 7. Validates inventory.
     * 8. Checks wallet balance when WALLET is selected.
     * 9. Deducts wallet balance when sufficient.
     * 10. Creates orders.
     * 11. Creates payments.
     * 12. Updates inventory.
     * 13. Clears cart.
     *
     * If wallet balance is insufficient, no order is created,
     * no payment is created, no inventory is changed,
     * and no cart item is removed.
     *
     * @param customerId customer ID
     * @param addressId selected address ID
     * @param paymentMethod selected payment method
     * @param upiId UPI ID for UPI payments
     */
    @Override
    @Transactional
    public void placeOrder(
            String customerId,
            String addressId,
            String paymentMethod,
            String upiId) {


        // =====================================================
        // VALIDATE CUSTOMER ID
        // =====================================================

        if (customerId == null
                || customerId.trim().isEmpty()) {

            throw new RuntimeException(
                    "Customer ID is required."
            );
        }


        // =====================================================
        // FIND CUSTOMER
        // =====================================================

        Customer customer =
                customerService.findCustomerById(customerId);

        if (customer == null) {

            throw new RuntimeException(
                    "Customer not found."
            );
        }


        // =====================================================
        // VALIDATE ADDRESS ID
        // =====================================================

        if (addressId == null
                || addressId.trim().isEmpty()) {

            throw new RuntimeException(
                    "Delivery address is required."
            );
        }


        // =====================================================
        // VERIFY ADDRESS BELONGS TO CUSTOMER
        // =====================================================

        List<Address> customerAddresses =
                addressService.findAddressesByCustomer(
                        customerId
                );

        boolean addressBelongsToCustomer = false;

        if (customerAddresses != null) {

            for (Address address : customerAddresses) {

                if (address != null
                        && address.getAddressId() != null
                        && address.getAddressId()
                        .equals(addressId)) {

                    addressBelongsToCustomer = true;

                    break;
                }
            }
        }


        if (!addressBelongsToCustomer) {

            throw new RuntimeException(
                    "Selected address does not belong to this customer."
            );
        }


        // =====================================================
        // GET CUSTOMER CART
        // =====================================================

        List<Cart> cartItems =
                cartService.findCartByCustomer(
                        customerId
                );

        if (cartItems == null
                || cartItems.isEmpty()) {

            throw new RuntimeException(
                    "Your cart is empty."
            );
        }


        // =====================================================
        // CALCULATE TOTAL AMOUNT
        // =====================================================

        double totalAmount = 0.0;


        for (Cart cart : cartItems) {

            if (cart == null) {
                continue;
            }

            if (cart.getProduct() == null) {
                continue;
            }

            if (cart.getQuantity() <= 0) {

                throw new RuntimeException(
                        "Invalid cart quantity."
                );
            }

            totalAmount += cart.getTotalPrice();
        }


        // =====================================================
        // VALIDATE TOTAL
        // =====================================================

        if (totalAmount <= 0) {

            throw new RuntimeException(
                    "Invalid order amount."
            );
        }


        // =====================================================
        // VALIDATE PAYMENT METHOD
        // =====================================================

        if (paymentMethod == null
                || paymentMethod.trim().isEmpty()) {

            throw new RuntimeException(
                    "Payment method is required."
            );
        }


        // =====================================================
        // NORMALIZE PAYMENT METHOD
        // =====================================================

        String normalizedPaymentMethod =
                paymentMethod.trim().toUpperCase();


        // =====================================================
        // VALIDATE PAYMENT METHOD VALUE
        // =====================================================

        try {

            PaymentMethod.valueOf(
                    normalizedPaymentMethod
            );

        } catch (IllegalArgumentException exception) {

            throw new RuntimeException(
                    "Invalid payment method: "
                            + paymentMethod
            );
        }


        // =====================================================
        // UPI VALIDATION
        // =====================================================

        if ("UPI".equals(normalizedPaymentMethod)) {

            if (upiId == null
                    || upiId.trim().isEmpty()) {

                throw new RuntimeException(
                        "UPI ID is required for UPI payment."
                );
            }
        }


        // =====================================================
        // INVENTORY VALIDATION
        // =====================================================
        //
        // IMPORTANT:
        // Inventory is checked BEFORE wallet deduction.
        //
        // This prevents the customer's wallet from being
        // deducted when an item is out of stock.
        //
        // =====================================================

        for (Cart cart : cartItems) {

            if (cart == null
                    || cart.getProduct() == null) {

                continue;
            }


            Product product =
                    cart.getProduct();


            Inventory inventory =
                    inventoryService.findInventoryByProduct(
                            product.getProductId()
                    );


            if (inventory == null) {

                throw new RuntimeException(
                        "Inventory not found for product: "
                                + product.getProductName()
                );
            }


            if (inventory.getQuantity()
                    < cart.getQuantity()) {

                throw new RuntimeException(
                        "Insufficient inventory for product: "
                                + product.getProductName()
                );
            }
        }


        // =====================================================
        // WALLET PAYMENT
        // =====================================================

        if ("WALLET".equals(normalizedPaymentMethod)) {


            // -------------------------------------------------
            // GET CURRENT WALLET BALANCE
            // -------------------------------------------------

            double walletBalance =
                    customer.getWalletBalance();


            // -------------------------------------------------
            // CHECK WALLET BALANCE
            // -------------------------------------------------

            if (walletBalance < totalAmount) {


                // Amount still required
                double remainingAmount =
                        totalAmount - walletBalance;


                /*
                 * IMPORTANT:
                 *
                 * DO NOT deduct wallet balance here.
                 *
                 * DO NOT create order here.
                 *
                 * DO NOT create payment here.
                 *
                 * DO NOT update inventory here.
                 *
                 * DO NOT clear cart here.
                 *
                 * The controller catches this exception
                 * and sends the user back to checkout where
                 * the remaining amount can be displayed.
                 */

                throw new InsufficientWalletBalanceException(
                        walletBalance,
                        remainingAmount,
                        totalAmount
                );
            }


            // -------------------------------------------------
            // WALLET HAS ENOUGH MONEY
            // -------------------------------------------------

            double newBalance =
                    walletBalance - totalAmount;


            // -------------------------------------------------
            // UPDATE DATABASE WALLET BALANCE
            // -------------------------------------------------

            customerService.updateWalletBalance(
                    customerId,
                    newBalance
            );


            // -------------------------------------------------
            // KEEP CUSTOMER OBJECT IN SYNC
            // -------------------------------------------------

            customer.setWalletBalance(
                    newBalance
            );
        }


        // =====================================================
        // CREATE ORDERS
        // =====================================================

        for (Cart cart : cartItems) {


            if (cart == null
                    || cart.getProduct() == null) {

                continue;
            }


            // -------------------------------------------------
            // GET PRODUCT
            // -------------------------------------------------

            Product product =
                    cart.getProduct();


            // -------------------------------------------------
            // GET INVENTORY
            // -------------------------------------------------

            Inventory inventory =
                    inventoryService.findInventoryByProduct(
                            product.getProductId()
                    );


            if (inventory == null) {

                throw new RuntimeException(
                        "Inventory not found for product: "
                                + product.getProductName()
                );
            }


            // -------------------------------------------------
            // FINAL INVENTORY CHECK
            // -------------------------------------------------

            if (inventory.getQuantity()
                    < cart.getQuantity()) {

                throw new RuntimeException(
                        "Insufficient inventory for product: "
                                + product.getProductName()
                );
            }


            // -------------------------------------------------
            // CREATE ORDER
            // -------------------------------------------------

            Order order =
                    new Order(
                            generateTrackingNumber(),
                            customer,
                            product,
                            cart.getQuantity(),
                            cart.getTotalPrice(),
                            OrderStatus.PENDING_APPROVAL,
                            LocalDateTime.now()
                    );


            // -------------------------------------------------
            // INSERT ORDER
            // -------------------------------------------------
            //
            // Your previous code used orderDAO here even
            // though orderDAO was not declared in this class.
            //
            // CheckoutServiceImpl already has OrderService,
            // so use OrderService.
            //
            // -------------------------------------------------

            orderService.insertOrder(order);


            // =================================================
            // CREATE PAYMENT
            // =================================================

            Payment payment =
                    new Payment();


            // -------------------------------------------------
            // PAYMENT ID
            // -------------------------------------------------

            payment.setPaymentId(
                    IdGenerator.generateId("PAY")
            );


            // -------------------------------------------------
            // TRANSACTION ID
            // -------------------------------------------------

            payment.setTransactionId(
                    IdGenerator.generateId("TXN")
            );


            // -------------------------------------------------
            // CUSTOMER
            // -------------------------------------------------

            payment.setCustomer(
                    customer
            );


            // -------------------------------------------------
            // ORDER
            // -------------------------------------------------

            payment.setOrder(
                    order
            );


            // -------------------------------------------------
            // PAYMENT METHOD
            // -------------------------------------------------

            payment.setPaymentMethod(
                    PaymentMethod.valueOf(
                            normalizedPaymentMethod
                    )
            );


            // -------------------------------------------------
            // PAYMENT AMOUNT
            // -------------------------------------------------

            payment.setAmount(
                    cart.getTotalPrice()
            );


            // -------------------------------------------------
            // UPI ID
            // -------------------------------------------------

            if ("UPI".equals(normalizedPaymentMethod)) {

                payment.setUpiId(
                        upiId.trim()
                );

            } else {

                payment.setUpiId(
                        null
                );
            }


            // -------------------------------------------------
            // PAYMENT DATE
            // -------------------------------------------------

            payment.setPaymentDate(
                    LocalDateTime.now()
            );


            // =================================================
            // PAYMENT STATUS
            // =================================================

            if ("CASH_ON_DELIVERY"
                    .equals(normalizedPaymentMethod)) {

                payment.setPaymentStatus(
                        PaymentStatus.PENDING
                );

            } else {

                /*
                 * UPI and WALLET are considered successful
                 * once this checkout operation completes.
                 */

                payment.setPaymentStatus(
                        PaymentStatus.SUCCESS
                );
            }


            // =================================================
            // INSERT PAYMENT
            // =================================================

            paymentService.insertPayment(
                    payment
            );


            // =================================================
            // UPDATE INVENTORY
            // =================================================

            inventory.setQuantity(
                    inventory.getQuantity()
                            - cart.getQuantity()
            );


            inventoryService.updateQuantity(
                    inventory
            );
        }


        // =====================================================
        // CLEAR CUSTOMER CART
        // =====================================================

        cartService.clearCart(
                customerId
        );
    }


    // =========================================================
    // GENERATE TRACKING NUMBER
    // =========================================================

    /**
     * Generates a unique tracking number for an order.
     *
     * @return generated tracking number
     */
    private String generateTrackingNumber() {

        return IdGenerator.generateId(
                "ORD"
        );
    }
}