package com.practice.project.uber.uberApp.services.impl;

import com.practice.project.uber.uberApp.dto.DriverDto;
import com.practice.project.uber.uberApp.dto.SignupDto;
import com.practice.project.uber.uberApp.dto.UserDto;
import com.practice.project.uber.uberApp.entities.User;
import com.practice.project.uber.uberApp.entities.enums.Role;
import com.practice.project.uber.uberApp.exceptions.RuntimeConflictException;
import com.practice.project.uber.uberApp.repositories.UserRepository;
import com.practice.project.uber.uberApp.services.AuthService;
import com.practice.project.uber.uberApp.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;

    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    @Transactional
    public UserDto signup(SignupDto signupDto) {
        User user = userRepository.findByEmail(signupDto.getEmail()).orElse(null);
        if(user != null){
            throw new RuntimeConflictException("Cannot signup, User already exists with email "+ signupDto.getEmail());
        }


        User mappedUser = modelMapper.map(signupDto, User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
        User savedUser = userRepository.save(mappedUser);

        //create user related entities
        riderService.createNewRider(savedUser);
        // TODO add wallet related service

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public DriverDto onboardNewDriver(Long userId) {
        return null;
    }
}
