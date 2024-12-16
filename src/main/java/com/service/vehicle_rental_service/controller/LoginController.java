package com.service.vehicle_rental_service.controller;

import com.service.vehicle_rental_service.model.LoginModel;
import com.service.vehicle_rental_service.model.RegisterModel;
import com.service.vehicle_rental_service.service.LoginService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/")
@Slf4j
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/register")
    public ResponseEntity<String> UserRegisteration(@RequestBody @Valid RegisterModel registerModel) {
        return loginService.register(registerModel);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> UserLogin(@RequestBody @Valid LoginModel loginModel) {
        return loginService.login(loginModel);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping(value = "/logout")
    public ResponseEntity<String> UserLogout(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = (String) authentication.getCredentials();
        return loginService.logout(token);
    }

}
