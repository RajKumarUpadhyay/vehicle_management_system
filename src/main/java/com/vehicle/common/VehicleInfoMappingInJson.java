package com.vehicle.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleInfoMappingInJson {

    @NotEmpty
    private String code;

    @NotEmpty
    private String make;

    @NotEmpty
    private String model;

    @NotNull
    @JsonProperty("kW")
    private int kW;

    @NotEmpty
    private String color;

    @NotNull
    private double price;

    @NotNull
    private int year;
}