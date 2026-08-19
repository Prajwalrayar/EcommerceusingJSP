package com.crimsonlogic.ecommerce.service.impl;

import com.crimsonlogic.ecommerce.dao.WalletMapper;
import com.crimsonlogic.ecommerce.exception.ValidationException;
import com.crimsonlogic.ecommerce.model.Customer;
import com.crimsonlogic.ecommerce.model.Wallet;
import com.crimsonlogic.ecommerce.service.WalletService;
import com.crimsonlogic.ecommerce.util.IdGenerator;
import com.crimsonlogic.ecommerce.util.ValidationUtil;

public class WalletServiceImpl implements WalletService {

    private WalletMapper walletMapper;

    public void setWalletMapper(
            WalletMapper walletMapper) {

        this.walletMapper = walletMapper;
    }


    @Override
    public Wallet findByCustomerId(
            String customerId) {

        return walletMapper.findByCustomerId(
                customerId
        );
    }


    @Override
    public double getBalance(
            String customerId) {

        Wallet wallet =
                walletMapper.findByCustomerId(
                        customerId
                );

        if (wallet == null) {
            return 0.0;
        }

        return wallet.getBalance();
    }


    @Override
    public void createWallet(
            String customerId) {

        Wallet existing =
                walletMapper.findByCustomerId(
                        customerId
                );

        if (existing != null) {
            return;
        }

        Wallet wallet =
                new Wallet();

        // ID generated ONCE
        wallet.setWalletId(
                IdGenerator.generateId("WAL")
        );

        wallet.setBalance(0.0);

        /*
         * Customer association should be populated
         * according to your existing Customer model.
         */

        Customer customer =
                new Customer();

        customer.setUserId(customerId);

        wallet.setCustomer(customer);

        walletMapper.insertWallet(wallet);
    }


    @Override
    public void recharge(
            String customerId,
            double amount,
            String upiId) {

        if (amount <= 0) {

            throw new ValidationException(
                    "Recharge amount must be greater than zero."
            );
        }

        /*
         * Use your EXISTING UPI validation method here.
         *
         * Replace the method name below with the exact
         * method already present in your ValidationUtil.
         */

        ValidationUtil.validateUpiId(upiId);

        Wallet wallet =
                walletMapper.findByCustomerId(
                        customerId
                );

        if (wallet == null) {

            createWallet(customerId);

            wallet =
                    walletMapper.findByCustomerId(
                            customerId
                    );
        }

        walletMapper.updateBalance(
                customerId,
                amount
        );
    }


    @Override
    public boolean hasSufficientBalance(
            String customerId,
            double amount) {

        return getBalance(customerId) >= amount;
    }


    @Override
    public void deduct(
            String customerId,
            double amount) {

        if (amount <= 0) {

            throw new ValidationException(
                    "Invalid payment amount."
            );
        }

        if (!hasSufficientBalance(
                customerId,
                amount)) {

            throw new ValidationException(
                    "Insufficient wallet balance."
            );
        }

        walletMapper.deductBalance(
                customerId,
                amount
        );
    }
}