package com.practice.project.uber.uberApp.strategies.impl;

import com.practice.project.uber.uberApp.entities.Driver;
import com.practice.project.uber.uberApp.entities.Payment;
import com.practice.project.uber.uberApp.entities.enums.PaymentStatus;
import com.practice.project.uber.uberApp.entities.enums.TransactionMethod;
import com.practice.project.uber.uberApp.repositories.PaymentRepository;
import com.practice.project.uber.uberApp.services.PaymentService;
import com.practice.project.uber.uberApp.services.WalletService;
import com.practice.project.uber.uberApp.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    public void processPayment(Payment payment) {

        Driver driver = payment.getRide().getDriver();

        double platformCommission = payment.getAmount() * PLATFORM_COMMISSION;

        walletService.deductMoneyFromWallet(driver.getUser(), platformCommission, null, payment.getRide(), TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);

    }
}
