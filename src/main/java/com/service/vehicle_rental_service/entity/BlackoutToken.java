package com.service.vehicle_rental_service.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.aerospike.mapping.Document;

@Document(collection = "blackout_tokens")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlackoutToken {

    @Id
    private String token;

    private long expiration;
}