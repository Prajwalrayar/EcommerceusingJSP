package com.crimsonlogic.ecommerce.exception;

public class InsufficientWalletBalanceException
        extends RuntimeException {

    private final double walletBalance;
    private final double remainingAmount;
    private final double orderAmount;

    public InsufficientWalletBalanceException(
            double walletBalance,
            double remainingAmount,
            double orderAmount) {

        super(
                "Insufficient wallet balance. "
                + "Wallet Balance: ₹"
                + String.format("%.2f", walletBalance)
                + ", "
                + "Amount Required: ₹"
                + String.format("%.2f", remainingAmount)
        );

        this.walletBalance = walletBalance;
        this.remainingAmount = remainingAmount;
        this.orderAmount = orderAmount;
    }

    public double getWalletBalance() {
        return walletBalance;
    }

    public double getRemainingAmount() {
        return remainingAmount;
    }

    public double getOrderAmount() {
        return orderAmount;
    }
}