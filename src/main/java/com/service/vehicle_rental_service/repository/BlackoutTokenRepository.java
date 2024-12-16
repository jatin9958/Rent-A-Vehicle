package com.service.vehicle_rental_service.repository;

import com.service.vehicle_rental_service.entity.BlackoutToken;
import org.springframework.data.aerospike.repository.AerospikeRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackoutTokenRepository extends AerospikeRepository<BlackoutToken, String> {
}