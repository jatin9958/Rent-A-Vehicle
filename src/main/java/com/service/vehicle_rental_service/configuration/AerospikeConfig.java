package com.service.vehicle_rental_service.configuration;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Host;
import com.aerospike.client.policy.ClientPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.aerospike.repository.config.EnableAerospikeRepositories;

@Configuration
@EnableAerospikeRepositories(basePackages = "com.yourpackage.repository")
public class AerospikeConfig {

    @Value("${aerospike.connection.host}")
    private String host;

    @Value("${aerospike.connection.port}")
    private int port;

    @Value("${aerospike.connection.user}")
    private String user;

    @Value("${aerospike.connection.password}")
    private String password;

    @Bean(destroyMethod = "close")
    public AerospikeClient aerospikeClient() {
        ClientPolicy clientPolicy = new ClientPolicy();
        clientPolicy.user = user;
        clientPolicy.password = password;
        clientPolicy.failIfNotConnected = true;
        return new AerospikeClient(clientPolicy, new Host(host, port));
    }

}
