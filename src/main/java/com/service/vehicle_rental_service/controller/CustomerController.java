package com.service.vehicle_rental_service.controller;

import com.service.vehicle_rental_service.model.BookingModel;
import com.service.vehicle_rental_service.service.VehicleService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/")
@Slf4j
public class CustomerController {

    @Autowired
    private VehicleService vehicleService;

    @PostAuthorize("hasRole('USER')")
    @PostMapping(value = "/bookVehicle")
    public ResponseEntity<String> bookVehicle(@RequestBody @Valid BookingModel bookingModel) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String customer = authentication.getName();
        return vehicleService.bookVehicle(bookingModel.getVehicleId(), customer, bookingModel.getReturnDate());
    }

}
