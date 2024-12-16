package com.service.vehicle_rental_service.repository;

import com.service.vehicle_rental_service.entity.BookingPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingPointRepository extends JpaRepository<BookingPoint, String> {
}
