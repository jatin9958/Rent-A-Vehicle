package com.service.vehicle_rental_service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "booking_point")
public class BookingPoint {


    @Id
    @Column(name = "booking_id")
    private String bookingId;
    @Column(name = "username")
    private String userName;
    @Column(name = "owner")
    private String owner;
    @Column(name = "booking_date")
    private Date bookingDate;
    @Column(name = "return_date")
    private Date returnDate;

}
