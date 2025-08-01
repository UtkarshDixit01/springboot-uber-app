package com.practice.project.uber.uberApp.repositories;

import com.practice.project.uber.uberApp.entities.Driver;
import com.practice.project.uber.uberApp.entities.Rating;
import com.practice.project.uber.uberApp.entities.Ride;
import com.practice.project.uber.uberApp.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByRider(Rider rider);
    List<Rating> findByDriver(Driver driver);

    Optional<Rating> findByRide(Ride ride);
}
