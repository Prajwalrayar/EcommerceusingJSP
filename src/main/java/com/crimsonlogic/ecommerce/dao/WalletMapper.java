package com.crimsonlogic.ecommerce.dao;

import com.crimsonlogic.ecommerce.model.Wallet;
import org.apache.ibatis.annotations.Param;

public interface WalletMapper {

    void insertWallet(Wallet wallet);

    Wallet findByCustomerId(
            @Param("customerId")
            String customerId
    );

    void updateBalance(
            @Param("customerId")
            String customerId,

            @Param("amount")
            double amount
    );

    void deductBalance(
            @Param("customerId")
            String customerId,

            @Param("amount")
            double amount
    );
}