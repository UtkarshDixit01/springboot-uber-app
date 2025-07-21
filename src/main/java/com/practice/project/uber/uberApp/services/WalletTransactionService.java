package com.practice.project.uber.uberApp.services;

import com.practice.project.uber.uberApp.dto.WalletTransactionDto;
import com.practice.project.uber.uberApp.entities.WalletTransaction;

public interface WalletTransactionService {

    void createNewWalletTransaction(WalletTransaction walletTransaction);
}
