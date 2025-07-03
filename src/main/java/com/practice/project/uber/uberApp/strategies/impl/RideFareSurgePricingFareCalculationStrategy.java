package com.practice.project.uber.uberApp.strategies.impl;

import com.practice.project.uber.uberApp.dto.RideRequestDto;
import com.practice.project.uber.uberApp.strategies.RideFareCalculationStrategy;

public class RideFareSurgePricingFareCalculationStrategy implements RideFareCalculationStrategy {
    @Override
    public double calculateFare(RideRequestDto rideRequestDto) {
        return 0;
    }
}
