package com.vehicle.parser;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.vehicle.common.VehicleInfoMappingInCsv;
import com.vehicle.entity.Dealer;
import com.vehicle.entity.Vehicle;
import com.vehicle.exception.UnrecognizedPropertyException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.vehicle.constant.Constant.SLASH;

@Component
public class VehicleCsvParser {

    public static final Logger logger = Logger.getLogger(VehicleCsvParser.class);

    public List<Dealer> vehicleInfoCsvParser(MultipartFile vehicleCsvData, long dealerId) throws IOException {
        logger.info("Parser has been invoked to parse csv data into object...");
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        ObjectReader objectReader = csvMapper.readerFor(VehicleInfoMappingInCsv.class).with(schema);
        List<VehicleInfoMappingInCsv> vehicleInfoMappingInCsvs = new ArrayList<>();

        try (Reader reader = new FileReader(convert(vehicleCsvData))){
            MappingIterator<VehicleInfoMappingInCsv> infoMappingInCSVMappingIterator = objectReader.readValues(reader);
            while (infoMappingInCSVMappingIterator.hasNext())
                vehicleInfoMappingInCsvs.add(infoMappingInCSVMappingIterator.next());
        } catch (IOException exception) {
            logger.error("Parsing failed due to : "+exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        } catch (UnrecognizedPropertyException unrecognizedPropertyException) {
            logger.error("Parsing failed due to : "+unrecognizedPropertyException.getMessage());
            throw new UnrecognizedPropertyException(unrecognizedPropertyException.getMessage());
        }
        return buildDealerPersistentObjectList(vehicleInfoMappingInCsvs, dealerId);
    }

    private List<Dealer> buildDealerPersistentObjectList(List<VehicleInfoMappingInCsv> vehicleInfoMappingInCSV, long dealerId) {
        logger.info("build Dealer Persistent Object List....");
        return vehicleInfoMappingInCSV.stream().map(vehicleInfo -> {
            Dealer dealer = new Dealer();
            Vehicle vehicle = new Vehicle();
            vehicle.setColor(vehicleInfo.getColor());
            vehicle.setPrice(vehicleInfo.getPrice());
            vehicle.setYear(vehicleInfo.getYear());
            String[] modeAndMake = vehicleInfo.getMakeAndModel().split(SLASH);
            vehicle.setMake(modeAndMake[0]);
            vehicle.setModel(modeAndMake[1]);
            dealer.setDealerId(dealerId);
            dealer.setCode(vehicleInfo.getCode());
            dealer.setVehicle(vehicle);
            return dealer;
        }).collect(Collectors.toList());
    }

    private File convert(MultipartFile file) throws IOException {
        logger.info("Convert multipart file object into file object");
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
