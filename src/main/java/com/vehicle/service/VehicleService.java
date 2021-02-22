package com.vehicle.service;

import com.vehicle.common.VehicleSearchRequest;
import com.vehicle.entity.Vehicle;

import java.util.List;

public interface VehicleService {
     int createVehicle(Vehicle vehicle);
     void updateVehicle(Vehicle source);
     List<Vehicle> searchVehicleBasedOnCriteria(VehicleSearchRequest vehicleSearchRequest);
}
