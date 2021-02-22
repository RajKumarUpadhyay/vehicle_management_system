package com.vehicle.controller;

import com.vehicle.common.VehicleInfoMappingInJson;
import com.vehicle.common.VehicleSearchRequest;
import com.vehicle.entity.Dealer;
import com.vehicle.entity.Vehicle;
import com.vehicle.parser.VehicleCsvParser;
import com.vehicle.parser.VehicleJsonParser;
import com.vehicle.service.DealerService;
import com.vehicle.service.VehicleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/vehicle-management-service")
public class VehicleManagementController {

    public static final Logger logger = Logger.getLogger(VehicleManagementController.class);

    @Autowired
    VehicleCsvParser vehicleCsvParser;

    @Autowired
    VehicleJsonParser vehicleJsonParser;

    @Autowired
    DealerService dealerService;

    @Autowired
    VehicleService vehicleService;

    @PostMapping(value = "/upload_csv/{dealerId}/")
    public ResponseEntity<Object> saveOrUpdateVehicleViaCSV(@PathVariable(required = true) long dealerId,
                                                                @RequestParam("file") MultipartFile csv) throws IOException {
        logger.info("Parse multiform csv data into defined list of object");
        List<Dealer> dealerVehicleList = vehicleCsvParser.vehicleInfoCsvParser(csv, dealerId);

        dealerService.saveOrUpdate(dealerVehicleList);
        logger.info("Vehicle data has been persisted into db.");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/vehicle_listings/{dealerId}/")
    public ResponseEntity<Object> saveOrUpdateVehicleViaJson(@Valid @RequestBody List<VehicleInfoMappingInJson> vehicleInfoMappingInJsonList,
                                      @PathVariable(required = true) long dealerId) {
        List<Dealer> dealerVehicleList = vehicleJsonParser.vehicleInfoJsonParser(vehicleInfoMappingInJsonList, dealerId);
        dealerService.saveOrUpdate(dealerVehicleList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "/searchVehicle")
    public List<Vehicle> searchVehicleBasedOnCriteriaDefinition(@Valid @ModelAttribute VehicleSearchRequest vehicleSearchRequest) {
        return vehicleService.searchVehicleBasedOnCriteria(vehicleSearchRequest);
    }
}
