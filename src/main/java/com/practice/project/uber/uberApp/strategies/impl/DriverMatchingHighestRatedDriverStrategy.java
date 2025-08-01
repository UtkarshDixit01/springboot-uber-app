package com.practice.project.uber.uberApp.strategies.impl;

import com.practice.project.uber.uberApp.entities.Driver;
import com.practice.project.uber.uberApp.entities.RideRequest;
import com.practice.project.uber.uberApp.repositories.DriverRepository;
import com.practice.project.uber.uberApp.strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverMatchingHighestRatedDriverStrategy implements DriverMatchingStrategy {

    private final DriverRepository driverRepository;

    @Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest) {
        return driverRepository.findTenNearbyTopRatedDrivers(rideRequest.getPickupLocation());
    }
}
