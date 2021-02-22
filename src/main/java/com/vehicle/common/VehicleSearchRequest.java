package com.vehicle.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleSearchRequest {
    private String make;

    private String model;

    private String year;

    private String color;
}
