package com.practice.project.uber.uberApp.services;

import com.practice.project.uber.uberApp.dto.DriverDto;
import com.practice.project.uber.uberApp.dto.SignupDto;
import com.practice.project.uber.uberApp.dto.UserDto;

public interface AuthService  {

    String login(String email, String password);

    UserDto signup(SignupDto signupDto);

    DriverDto onboardNewDriver(Long userId, String vehicleId);
}
