package com.practice.project.uber.uberApp.entities;

import com.practice.project.uber.uberApp.entities.enums.PaymentMethod;
import com.practice.project.uber.uberApp.entities.enums.PaymentStatus;
import jakarta.persistence.*;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @OneToOne(fetch = FetchType.LAZY)
    private Ride ride;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}
