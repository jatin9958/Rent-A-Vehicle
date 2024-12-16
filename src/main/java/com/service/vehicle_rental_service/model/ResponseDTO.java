package com.service.vehicle_rental_service.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseDTO<T> {

    List<ErrorDTO> errors;

    private Meta meta;

    private T data;

    public ResponseDTO(Meta meta) {
        this.meta = meta;
    }
}