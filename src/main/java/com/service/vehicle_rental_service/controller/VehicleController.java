package com.service.vehicle_rental_service.controller;

import com.service.vehicle_rental_service.model.VehicleModel;
import com.service.vehicle_rental_service.service.VehicleService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/")
@Slf4j
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/addVehicle")
    public ResponseEntity<String> addVehicle(@RequestBody @Valid VehicleModel vehicleModel) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String adminUsername = authentication.getName();
        return vehicleService.addVehicle(vehicleModel, adminUsername);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping(value = "/getAllVehicle")
    public ResponseEntity<List<VehicleModel>> getAllVehicle() {
        return vehicleService.getAllVehicle();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/getVehiclesByAdmin")
    public ResponseEntity<List<VehicleModel>> getVehiclesByAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String adminUsername = authentication.getName();
        return vehicleService.getVehiclesByAdmin(adminUsername);
    }

    @PostAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/currentVehicleChanges")
    public ResponseEntity<String> makeCurrentVehicleChanges(@RequestBody @Valid VehicleModel vehicleModel) {
        return vehicleService.makeCurrentVehicleChanges(vehicleModel);
    }

}
