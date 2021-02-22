package com.vehicle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vehicle.common.VehicleInfoMappingInJson;
import com.vehicle.entity.Dealer;
import com.vehicle.entity.Vehicle;
import com.vehicle.repositiory.VehicleRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class VehicleManagementControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Before
    public void setup() {
        Vehicle vehicle = new Vehicle();
        vehicle.setYear(2014);
        vehicle.setPrice(15500);
        vehicle.setColor("RED");
        vehicle.setKW(100);
        vehicle.setModel("Megane");
        vehicle.setMake("Renault");

        vehicleRepository.save(vehicle);

        vehicle.setYear(2015);
        vehicle.setPrice(15600);
        vehicle.setColor("BLACK");
        vehicle.setKW(1000);
        vehicle.setModel("X7");
        vehicle.setMake("Audi");
        vehicleRepository.save(vehicle);

        vehicle.setYear(2021);
        vehicle.setPrice(15700);
        vehicle.setColor("Green");
        vehicle.setKW(1000);
        vehicle.setModel("A");
        vehicle.setMake("Mercedes");
        vehicleRepository.save(vehicle);
    }

    @After
    public void tearDown() {
        vehicleRepository.deleteAll();
    }

    @Test
    void saveOrUpdateVehicleViaCSV() throws Exception {
        String vehicleDataInfo = "code,make/model,power-in-ps,year,color,price\n"
                + "1,mercedes/a 180,123,2014,black,15950\n"
                + "2,audi/a3,111,2016,white,17210\n"
                + "3,vw/golf,86,2018,green,14980\n"
                + "4,skoda/octavia,86,2018,16990";

        MockMultipartFile csvFile = new MockMultipartFile(
                "vehicle.csv", "", "multipart/form-data", vehicleDataInfo.getBytes());
        mockMvc.perform(MockMvcRequestBuilders.multipart("/vehicle-management-service/upload_csv/1/")
                .file("vehicle", csvFile.getBytes())
                .characterEncoding("UTF-8"))
                .andExpect(status().isCreated());
    }

    @Test
    void saveOrUpdateVehicleViaJson() throws Exception {

        List<VehicleInfoMappingInJson> vehicleInfoMappingInJsonList = new ArrayList<>();
        VehicleInfoMappingInJson vehicleInfoMappingInJson = new VehicleInfoMappingInJson();

        vehicleInfoMappingInJson.setCode("a");
        vehicleInfoMappingInJson.setColor("red");
        vehicleInfoMappingInJson.setKW(123);
        vehicleInfoMappingInJson.setMake("Mercedes");
        vehicleInfoMappingInJson.setModel("megane");
        vehicleInfoMappingInJson.setPrice(13900);
        vehicleInfoMappingInJson.setYear(2014);

        vehicleInfoMappingInJsonList.add(vehicleInfoMappingInJson);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String dealerVehicleInfo = ow.writeValueAsString(vehicleInfoMappingInJsonList);

        mockMvc.perform(post("/vehicle-management-service/vehicle_listings/1/").content(dealerVehicleInfo)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        List<Vehicle> result = (List<Vehicle>) vehicleRepository.findAll();
        Assert.assertEquals(1, result.size());
    }

    @Test
    void searchVehicleBasedOnCriteriaDefinition() throws Exception {
        mockMvc.perform(get("/vehicle-management-service/searchVehicle?make=Mercedes"))
                .andExpect(status().isOk());
    }
}