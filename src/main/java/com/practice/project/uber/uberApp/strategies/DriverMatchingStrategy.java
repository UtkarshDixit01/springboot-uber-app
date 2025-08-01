package com.practice.project.uber.uberApp.strategies;

import com.practice.project.uber.uberApp.entities.Driver;
import com.practice.project.uber.uberApp.entities.RideRequest;

import java.util.List;

public interface DriverMatchingStrategy {

    List<Driver> findMatchingDriver(RideRequest rideRequest);
}
