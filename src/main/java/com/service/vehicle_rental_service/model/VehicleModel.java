package com.service.vehicle_rental_service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class VehicleModel {

    @NotNull
    @NotBlank(message = "type must not be blank")
    private String type;

    @NotNull
    @NotBlank(message = "model is mandatory")
    private String model;

    @NotNull
    @NotBlank(message = "vehicleNo is mandatory")
    private String vehicleNo;

    @NotNull(message = "price must not be null")
    private Double price;

    @NotNull
    @NotBlank(message = "location must be valid")
    private String location;

    @NotNull(message = "availability is mandatory")
    private Boolean availability;
}
