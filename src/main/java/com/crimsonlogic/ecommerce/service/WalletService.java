package com.crimsonlogic.ecommerce.service;

import com.crimsonlogic.ecommerce.model.Wallet;

public interface WalletService {

    Wallet findByCustomerId(String customerId);

    double getBalance(String customerId);

    void createWallet(String customerId);

    void recharge(
            String customerId,
            double amount,
            String upiId
    );

    boolean hasSufficientBalance(
            String customerId,
            double amount
    );

    void deduct(
            String customerId,
            double amount
    );
}