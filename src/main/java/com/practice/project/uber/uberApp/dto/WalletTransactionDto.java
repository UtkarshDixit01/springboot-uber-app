package com.practice.project.uber.uberApp.dto;

import com.practice.project.uber.uberApp.entities.Ride;
import com.practice.project.uber.uberApp.entities.Wallet;
import com.practice.project.uber.uberApp.entities.enums.TransactionMethod;
import com.practice.project.uber.uberApp.entities.enums.TransactionType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class WalletTransactionDto {

    private Long id;

    private Double amount;

    private TransactionType transactionType;

    private TransactionMethod transactionMethod;

    private RideDto ride;

    private String transactionId;


    private WalletDto wallet;

    private LocalDateTime timeStamp;
}
