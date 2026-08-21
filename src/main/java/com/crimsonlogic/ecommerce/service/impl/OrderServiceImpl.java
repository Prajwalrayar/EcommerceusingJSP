package com.crimsonlogic.ecommerce.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.crimsonlogic.ecommerce.dao.CartMapper;
import com.crimsonlogic.ecommerce.dao.OrderMapper;
import com.crimsonlogic.ecommerce.enumeration.OrderStatus;
import com.crimsonlogic.ecommerce.exception.ValidationException;
import com.crimsonlogic.ecommerce.model.Order;
import com.crimsonlogic.ecommerce.service.CartService;
import com.crimsonlogic.ecommerce.service.OrderService;
import com.crimsonlogic.ecommerce.util.IdGenerator;

public class OrderServiceImpl implements OrderService {

    private OrderMapper orderMapper;
    private CartService cartService;
    private CartMapper cartMapper;


    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    public void setCartMapper(CartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }
    
    @Override
    public void insertOrder(Order order) {

        if (order == null) {

            throw new ValidationException(
                    "Order information is required."
            );
        }

        if (order.getOrderId() == null
                || order.getOrderId().trim().isEmpty()) {

            order.setOrderId(
                    IdGenerator.generateId("ORD")
            );
        }

        if (order.getCustomer() == null
                || order.getCustomer().getUserId() == null
                || order.getCustomer().getUserId().trim().isEmpty()) {

            throw new ValidationException(
                    "Customer is required."
            );
        }

        if (order.getProduct() == null
                || order.getProduct().getProductId() == null
                || order.getProduct().getProductId().trim().isEmpty()) {

            throw new ValidationException(
                    "Product is required."
            );
        }

        if (order.getQuantity() <= 0) {

            throw new ValidationException(
                    "Order quantity must be greater than 0."
            );
        }

        if (order.getTotalPrice() <= 0) {

            throw new ValidationException(
                    "Order total price must be greater than 0."
            );
        }

        if (order.getOrderStatus() == null) {

            order.setOrderStatus(
                    OrderStatus.PENDING_APPROVAL
            );
        }

        orderMapper.insertOrder(order);
    }


    @Override
    public void updateOrder(Order order) {

        orderMapper.updateOrder(order);
    }


    @Override
    public void deleteOrder(String orderId) {

        orderMapper.deleteOrder(orderId);
    }


    @Override
    public Order findOrderById(String orderId) {

        return orderMapper.findOrderById(orderId);
    }


    @Override
    public List<Order> findOrdersByCustomer(String customerId) {

        return orderMapper.findOrdersByCustomer(customerId);
    }


    @Override
    public List<Order> findOrdersBySeller(String sellerId) {

        return orderMapper.findOrdersBySeller(sellerId);
    }


    @Override
    public List<Order> findAllOrders() {

        return orderMapper.findAllOrders();
    }


    @Override
    public void updateOrderStatus(Order order) {

        if (order == null) {

            throw new ValidationException(
                    "Order information is required."
            );
        }

        if (order.getOrderId() == null
                || order.getOrderId().trim().isEmpty()) {

            throw new ValidationException(
                    "Order ID is required."
            );
        }

        if (order.getOrderStatus() == null) {

            throw new ValidationException(
                    "Order status is required."
            );
        }

        Order existingOrder =
                orderMapper.findOrderById(
                        order.getOrderId()
                );

        if (existingOrder == null) {

            throw new ValidationException(
                    "Order not found."
            );
        }

        validateStatusTransition(
                existingOrder.getOrderStatus(),
                order.getOrderStatus()
        );

        existingOrder.setOrderStatus(
                order.getOrderStatus()
        );

        if (order.getOrderStatus()
                == OrderStatus.SHIPPED) {

            if (existingOrder.getTrackingNumber() == null
                    || existingOrder.getTrackingNumber()
                            .trim()
                            .isEmpty()) {

                existingOrder.setTrackingNumber(
                        IdGenerator.generateId("TRK")
                );
            }
        }

        if (order.getOrderStatus()
                == OrderStatus.DELIVERED) {

            existingOrder.setDeliveredDate(
                    LocalDateTime.now()
            );
        }

        orderMapper.updateOrderStatus(
                existingOrder
        );
    }


    @Override
    public List<Order> findPendingApprovalOrdersBySeller(
            String sellerId) {

        return orderMapper.findPendingApprovalOrdersBySeller(sellerId);
    }


    @Override
    public List<Order> findOrdersWithoutPayment(
            String customerId) {

        return orderMapper.findOrdersWithoutPayment(customerId);
    }


    @Override
    public List<Order> findOrdersByStatus(
            OrderStatus status) {

        return orderMapper.findOrdersByStatus(status);
    }


    @Override
    public List<Order> findOrdersByCustomerAndProduct(
            String customerId,
            String productName) {

        return orderMapper.findOrdersByCustomerAndProduct(
                customerId,
                productName);
    }


    @Override
    public List<Order> findOrdersBySellerAndProduct(
            String sellerId,
            String productName) {

        return orderMapper.findOrdersBySellerAndProduct(
                sellerId,
                productName);
    }


    @Override
    public List<Order> findOrdersByKeyword(
            String keyword) {

        return orderMapper.findOrdersByKeyword(keyword);
    }


    @Override
    public Order findOrderByIdAndCustomer(
            String orderId,
            String customerId) {

        return orderMapper.findOrderByIdAndCustomer(
                orderId,
                customerId);
    }


    @Override
    public List<Order> findCancelableOrders(
            String customerId) {

        return orderMapper.findCancelableOrders(customerId);
    }


    @Override
    public Order findOrderByIdAndSeller(
            String orderId,
            String sellerId) {

        return orderMapper.findOrderByIdAndSeller(
                orderId,
                sellerId);
    }
    
    private void validateStatusTransition(
            OrderStatus current,
            OrderStatus next) {

        if (current == null) {

            throw new ValidationException(
                    "Current order status is missing."
            );
        }

        if (next == null) {

            throw new ValidationException(
                    "New order status is required."
            );
        }

        if (current == next) {
            return;
        }

        boolean valid = false;

        switch (current) {

            case PENDING_APPROVAL:

                valid = next == OrderStatus.CONFIRMED
                        || next == OrderStatus.CANCELLED
                        || next == OrderStatus.REJECTED;

                break;


            case CONFIRMED:

                valid = next == OrderStatus.SHIPPED
                        || next == OrderStatus.CANCELLED;

                break;


            case SHIPPED:

                valid = next == OrderStatus.IN_TRANSIT;

                break;


            case IN_TRANSIT:

                valid = next == OrderStatus.OUT_FOR_DELIVERY;

                break;


            case OUT_FOR_DELIVERY:

                valid = next == OrderStatus.DELIVERED;

                break;


            case DELIVERED:

                valid = next == OrderStatus.RETURN_REQUESTED;

                break;


            case RETURN_REQUESTED:

            case CANCELLED:

            case REJECTED:

                valid = false;

                break;
        }

        if (!valid) {

            throw new ValidationException(
                    "Invalid order status transition: "
                    + current
                    + " → "
                    + next
            );
        }
    }
    
    @Override
    public List<Order> findOrdersBySellerAndStatus(
            String sellerId,
            OrderStatus status) {

        return orderMapper.findOrdersBySellerAndStatus(
                sellerId,
                status
        );
    }


    @Override
    public List<Order> findOrdersBySellerAndOrderId(
            String sellerId,
            String orderId) {

        return orderMapper.findOrdersBySellerAndOrderId(
                sellerId,
                orderId
        );
    }


    @Override
    public void placeOrder(
            String customerId,
            String addressId,
            String paymentMethod,
            String upiId) {

        if (customerId == null ||
                customerId.trim().isEmpty()) {

            throw new ValidationException(
                    "Customer ID is required."
            );
        }

        if (addressId == null ||
                addressId.trim().isEmpty()) {

            throw new ValidationException(
                    "Delivery address is required."
            );
        }

        if (paymentMethod == null ||
                paymentMethod.trim().isEmpty()) {

            throw new ValidationException(
                    "Payment method is required."
            );
        }

        /*
         * Get customer's cart.
         */
        List<com.crimsonlogic.ecommerce.model.Cart> cartItems =
                cartMapper.findCartByCustomer(customerId);

        if (cartItems == null ||
                cartItems.isEmpty()) {

            throw new ValidationException(
                    "Your cart is empty."
            );
        }

        /*
         * Create one Order for each cart item.
         */
        for (com.crimsonlogic.ecommerce.model.Cart cart : cartItems) {

            if (cart.getProduct() == null) {

                throw new ValidationException(
                        "Product information is missing."
                );
            }

            double totalPrice =
                    cart.getProduct().getProductPrice()
                            * cart.getQuantity();

            Order order = new Order();

            order.setOrderId(
                    IdGenerator.generateId("ORD")
            );

            order.setCustomer(
                    cart.getCustomer()
            );

            order.setProduct(
                    cart.getProduct()
            );

            order.setQuantity(
                    cart.getQuantity()
            );

            order.setTotalPrice(
                    totalPrice
            );

            /*
             * Order becomes visible to seller only
             * after successful payment.
             */
            order.setOrderStatus(
                    OrderStatus.PENDING_APPROVAL
            );

            order.setOrderDate(
                    LocalDateTime.now()
            );

            order.setDeliveredDate(null);
            order.setTrackingNumber(null);

            /*
             * Insert Order.
             */
            orderMapper.insertOrder(order);

            /*
             * Payment will be created by the controller
             * after order creation.
             *
             * This method only creates the order.
             */
        }
    }
}