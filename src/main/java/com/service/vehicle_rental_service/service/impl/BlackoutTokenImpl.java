package com.service.vehicle_rental_service.service.impl;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.service.vehicle_rental_service.entity.BlackoutToken;
import com.service.vehicle_rental_service.repository.BlackoutTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class BlackoutTokenImpl {

    @Autowired
    private Environment env;

    @Autowired
    private BlackoutTokenRepository blackoutTokenRepository;

    @Autowired
    private AerospikeClient aerospikeClient;

    /**
     * Checks if a token is blacklisted.
     *
     * @param token the JWT token to check
     * @return true if the token is blacklisted, false otherwise
     */
    public boolean isTokenBlacklisted(String token) {
        Optional<BlackoutToken> blackoutToken = blackoutTokenRepository.findById(token);
        if (blackoutToken.isPresent()) {
            return true;
        }

        // If not found in the repository, check directly in Aerospike
        String namespace = env.getProperty("aerospike.namespace", "default");
        String setName = env.getProperty("aerospike.blackout-token-set", "blackoutTokens");
        Key key = new Key(namespace, setName, token);

        Record record = aerospikeClient.get(null, key);
        return record != null && record.getLong("expiry") > Instant.now().toEpochMilli();
    }
}
