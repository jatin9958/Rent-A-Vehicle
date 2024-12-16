package com.service.vehicle_rental_service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BookingModel {

    @NotNull
    @NotBlank(message = "vehicle Id must be Present")
    private String vehicleId;

    @NotNull
    @NotBlank(message = "return date should be mention")
    private Date returnDate;

}
