package com.practice.project.uber.uberApp.services.impl;

import com.practice.project.uber.uberApp.dto.DriverDto;
import com.practice.project.uber.uberApp.dto.RiderDto;
import com.practice.project.uber.uberApp.entities.Driver;
import com.practice.project.uber.uberApp.entities.Rating;
import com.practice.project.uber.uberApp.entities.Ride;
import com.practice.project.uber.uberApp.entities.Rider;
import com.practice.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.practice.project.uber.uberApp.repositories.DriverRepository;
import com.practice.project.uber.uberApp.repositories.RatingRepository;
import com.practice.project.uber.uberApp.repositories.RideRepository;
import com.practice.project.uber.uberApp.repositories.RiderRepository;
import com.practice.project.uber.uberApp.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final DriverRepository driverRepository;
    private final RiderRepository riderRepository;
    private final RideRepository rideRepository;
    private final ModelMapper modelMapper;

    @Override
    public DriverDto rateDriver(Ride ride, Integer rating) {
        Driver driver = ride.getDriver();
        Rating ratingObj = ratingRepository.findByRide(ride).orElseThrow(
                () -> new ResourceNotFoundException("rating not found for ride with id: "+ride.getId())
        );

        if(ratingObj.getDriverRating() != null){
            throw new RuntimeException("Driver has already been rated, cannot rate again");
        }

        ratingObj.setDriverRating(rating);

        ratingRepository.save(ratingObj);

        Double newRating = ratingRepository.findByDriver(driver)
                .stream().mapToDouble(Rating::getDriverRating)
                .average().orElse(0.0);
        driver.setRating(newRating);

        Driver savedDriver = driverRepository.save(driver);
        return modelMapper.map(savedDriver, DriverDto.class);
    }

    @Override
    public RiderDto rateRider(Ride ride, Integer rating) {
        Rider rider = ride.getRider();
        Rating ratingObj = ratingRepository.findByRide(ride).orElseThrow(
                () -> new ResourceNotFoundException("rating not found for ride with id: "+ride.getId())
        );

        if(ratingObj.getRiderRating() != null){
            throw new RuntimeException("Rider has already been rated, cannot rate again");
        }

        ratingObj.setRiderRating(rating);

        ratingRepository.save(ratingObj);

        Double newRating = ratingRepository.findByRider(rider)
                .stream().mapToDouble(Rating::getRiderRating)
                .average().orElse(0.0);
        rider.setRating(newRating);

        Rider savedRider = riderRepository.save(rider);
        return modelMapper.map(savedRider, RiderDto.class);
    }

    @Override
    public void createNewRating(Ride ride) {
        Rating rating = Rating.builder()
                .rider(ride.getRider())
                .driver(ride.getDriver())
                .ride(ride)
                .build();
        ratingRepository.save(rating);
    }
}
