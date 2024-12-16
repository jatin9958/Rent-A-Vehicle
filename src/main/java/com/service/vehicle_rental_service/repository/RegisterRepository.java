package com.service.vehicle_rental_service.repository;

import com.service.vehicle_rental_service.entity.RegisterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RegisterRepository extends JpaRepository<RegisterUser, Long> {
    Optional<RegisterUser> findByUserName(String username);
}
