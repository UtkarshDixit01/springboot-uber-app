package com.practice.project.uber.uberApp.strategies.impl;

import com.practice.project.uber.uberApp.dto.RideRequestDto;
import com.practice.project.uber.uberApp.entities.Driver;
import com.practice.project.uber.uberApp.strategies.DriverMatchingStrategy;

import java.util.List;

public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {
    @Override
    public List<Driver> findMatchingDriver(RideRequestDto rideRequestDto) {
        return List.of();
    }
}
