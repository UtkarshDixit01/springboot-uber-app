package com.practice.project.uber.uberApp.strategies.impl;

import com.practice.project.uber.uberApp.entities.Driver;
import com.practice.project.uber.uberApp.entities.Payment;
import com.practice.project.uber.uberApp.entities.Rider;
import com.practice.project.uber.uberApp.entities.enums.PaymentStatus;
import com.practice.project.uber.uberApp.entities.enums.TransactionMethod;
import com.practice.project.uber.uberApp.repositories.PaymentRepository;
import com.practice.project.uber.uberApp.services.PaymentService;
import com.practice.project.uber.uberApp.services.WalletService;
import com.practice.project.uber.uberApp.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();

        Rider rider = payment.getRide().getRider();

        walletService.deductMoneyFromWallet(rider.getUser(), payment.getAmount(), null, payment.getRide(), TransactionMethod.RIDE);

        double driversCut = payment.getAmount() * (1 - PLATFORM_COMMISSION);

        walletService.addMoneyToWallet(driver.getUser(), driversCut, null, payment.getRide(), TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);

    }
}
