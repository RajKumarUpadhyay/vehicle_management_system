package com.vehicle.parser;

import com.vehicle.common.VehicleInfoMappingInJson;
import com.vehicle.controller.VehicleManagementController;
import com.vehicle.entity.Dealer;
import com.vehicle.entity.Vehicle;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VehicleJsonParser {
    public static final Logger logger = Logger.getLogger(VehicleJsonParser.class);
    public List<Dealer> vehicleInfoJsonParser(List<VehicleInfoMappingInJson> vehicleInfoMappingInJsonList, long dealerId) {
        logger.info("vehicleInfoJsonParser() invoked...");
        return vehicleInfoMappingInJsonList.stream().map(vehicleInfoMappingInJson -> {
            Dealer dealer = new Dealer();
            Vehicle vehicle = new Vehicle();

            vehicle.setMake(vehicleInfoMappingInJson.getMake());
            vehicle.setModel(vehicleInfoMappingInJson.getModel());
            vehicle.setKW(vehicleInfoMappingInJson.getKW());
            vehicle.setColor(vehicleInfoMappingInJson.getColor());
            vehicle.setPrice(vehicleInfoMappingInJson.getPrice());
            vehicle.setYear(vehicleInfoMappingInJson.getYear());
            dealer.setDealerId(dealerId);
            dealer.setCode(vehicleInfoMappingInJson.getCode());
            dealer.setVehicle(vehicle);
            return dealer;
        }).collect(Collectors.toList());
    }
}
