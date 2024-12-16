package com.service.vehicle_rental_service.service;

import com.service.vehicle_rental_service.model.LoginModel;
import com.service.vehicle_rental_service.model.RegisterModel;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
     ResponseEntity<String> register(RegisterModel registerModel);
     ResponseEntity<String> login(LoginModel loginModel);
     ResponseEntity<String> logout(String token);
}
