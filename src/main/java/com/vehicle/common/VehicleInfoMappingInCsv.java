package com.vehicle.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleInfoMappingInCsv {

    @NotEmpty
    private String code;

    @NotEmpty
    @JsonProperty("make/model")
    private String makeAndModel;

    @NotEmpty
    @JsonProperty("power-in-ps")
    private int power;

    @NotEmpty
    private int year;

    @NotEmpty
    private String color;

    @NotEmpty
    private double price;
}
