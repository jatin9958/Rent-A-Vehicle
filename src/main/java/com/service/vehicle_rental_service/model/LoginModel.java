package com.service.vehicle_rental_service.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginModel {
    @NotNull
    @NotBlank(message = "Username must not be blank")
    private String username;
    @NotNull
    @NotBlank(message = "Password is mandatory")
    private String password;
}
