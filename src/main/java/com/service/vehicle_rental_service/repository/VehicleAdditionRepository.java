package com.service.vehicle_rental_service.repository;

import com.service.vehicle_rental_service.entity.VehicleAddition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleAdditionRepository extends JpaRepository<VehicleAddition, String> {
}
