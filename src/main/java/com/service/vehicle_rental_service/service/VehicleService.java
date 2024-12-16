package com.service.vehicle_rental_service.service;


import com.service.vehicle_rental_service.model.VehicleModel;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface VehicleService {
    ResponseEntity<String> addVehicle(VehicleModel vehicleModel, String adminUserName);

    ResponseEntity<List<VehicleModel>> getAllVehicle();

    ResponseEntity<List<VehicleModel>> getVehiclesByAdmin(String adminUserName);

    ResponseEntity<String> makeCurrentVehicleChanges(VehicleModel vehicleModel);

    ResponseEntity<String> bookVehicle(String vehicleId, String customer, Date returnDate);
}
