package com.service.vehicle_rental_service.model;



import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class RegisterModel {

    @NotNull
    @NotBlank(message = "Username must not be blank")
    private String username;
    @NotNull
    @NotBlank(message = "Password is mandatory")
    private String password;
    @NotNull
    @NotBlank(message = "Role is mandatory")
    private String role;
}
