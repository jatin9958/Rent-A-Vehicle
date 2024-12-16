package com.service.vehicle_rental_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "vehicle_addition")
public class VehicleAddition {

    @Id
    @Column(name = "vehicle_no")
    private String vehicleNo;

    @Column(name = "type")
    private String type;

    @Column(name = "model")
    private String model;

    @Column(name = "location")
    private String location;

    @Column(name = "charges")
    private Double price;

    @Column(name = "is_available")
    private Boolean availability;

    @Column(name = "username")
    private String userName;

}
