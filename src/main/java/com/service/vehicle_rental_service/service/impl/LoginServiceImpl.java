package com.service.vehicle_rental_service.service.impl;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.WritePolicy;
import com.service.vehicle_rental_service.entity.BlackoutToken;
import com.service.vehicle_rental_service.entity.RegisterUser;
import com.service.vehicle_rental_service.model.LoginModel;
import com.service.vehicle_rental_service.model.RegisterModel;
import com.service.vehicle_rental_service.repository.BlackoutTokenRepository;
import com.service.vehicle_rental_service.repository.RegisterRepository;
import com.service.vehicle_rental_service.service.LoginService;


import com.service.vehicle_rental_service.utility.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {


    @Autowired
    RegisterRepository registerRepository;

    @Autowired
    BlackoutTokenRepository blackoutTokenRepository;

    @Autowired
    private AerospikeClient aerospikeClient;

    @Autowired
    private CustomUserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private Environment env;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<String> register(RegisterModel registerModel) {
        try {
         Optional<RegisterUser> registerUser = Optional.ofNullable(registerRepository.findByUserName(registerModel.getUsername()).orElse(null));

        if(registerUser.isEmpty()){
            String hashedPassword = passwordEncoder.encode(registerModel.getPassword());
            RegisterUser registerUser1 = new RegisterUser();
            registerUser1.setUserName(registerModel.getUsername());
            registerUser1.setPassword(hashedPassword);
            registerUser1.setRole(registerModel.getRole());
            try {
                registerRepository.save(registerUser1);
            }
            catch (Exception e) {
                log.error("Exception occurred during saving userdata in db", e);
                return ResponseEntity.internalServerError().body("Please try again later");
            }
            log.info("User registered successfully");
            return ResponseEntity.ok(registerModel.getRole() + " registered successfully");
        }

       else return ResponseEntity.badRequest().body("Username already exists");

    } catch (Exception e) {
        log.error("exception occurred during registration of data", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register user");
        }
    }

    @Override
    public ResponseEntity<String> login(LoginModel loginModel) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginModel.getUsername(),loginModel.getPassword()));
            if (authentication.isAuthenticated()) {
              String token = jwtUtil.generateToken(loginModel.getUsername());
           return ResponseEntity.ok(token);
            }
            else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        }
        catch (AuthenticationException e) {
            log.error("Invalid username or password", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Username or Password");
        }
    }

    @Override
    public ResponseEntity<String> logout(String token) {
        Duration expiry = Duration.ofDays(1);
        long expirationTime = Instant.now().plus(expiry).toEpochMilli();
        BlackoutToken blackoutToken = new BlackoutToken(token, expirationTime);
        blackoutTokenRepository.save(blackoutToken);
        return ResponseEntity.status(HttpStatus.OK).body("User Logout Successfully");
    }


}
