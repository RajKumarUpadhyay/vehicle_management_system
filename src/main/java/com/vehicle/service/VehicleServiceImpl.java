package com.vehicle.service;

import com.vehicle.common.VehicleSearchRequest;
import com.vehicle.entity.Vehicle;
import com.vehicle.exception.ResourceNotFoundException;
import com.vehicle.repositiory.VehicleRepository;
import com.vehicle.specification.VehicleSearchCriteriaSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    VehicleRepository vehicleRepository;

    @Transactional
    @Override
    public int createVehicle(Vehicle vehicleInfo) {
        Vehicle vehicle = vehicleRepository.save(vehicleInfo);
        return vehicle.getId();
    }

    @Transactional
    @Override
    public void updateVehicle(Vehicle vehicle) {
        Optional<Vehicle> optionalExisting = vehicleRepository.findById(vehicle.getId());
        if (optionalExisting.isPresent()) {
            Vehicle vehicleExist = optionalExisting.get();
            vehicleExist.setColor(vehicle.getColor());
            vehicleExist.setKW(vehicle.getKW());
            vehicleExist.setMake(vehicle.getMake());
            vehicleExist.setModel(vehicle.getModel());
            vehicleExist.setPrice(vehicle.getPrice());
        } else {
            throw new ResourceNotFoundException("Vehicle not found");
        }
    }

    @Override
    public List<Vehicle> searchVehicleBasedOnCriteria(VehicleSearchRequest vehicleSearchRequest) {
        List<Vehicle> vehicleList;
        Specification<Vehicle> vehicleSpecification = null;
        if (Objects.nonNull(vehicleSearchRequest.getMake()) && !vehicleSearchRequest.getMake().isEmpty())   {
            Specification<Vehicle> byParam = VehicleSearchCriteriaSpecification
                    .hasMake(vehicleSearchRequest.getMake());
            vehicleSpecification = vehicleSpecification != null ? vehicleSpecification.and(byParam) : byParam;
        }
        if (Objects.nonNull(vehicleSearchRequest.getModel()) && !vehicleSearchRequest.getModel().isEmpty()) {
            Specification<Vehicle> byParam = VehicleSearchCriteriaSpecification
                    .hasModel(vehicleSearchRequest.getModel());
            vehicleSpecification = vehicleSpecification != null ? vehicleSpecification.and(byParam) : byParam;
        }
        if (Objects.nonNull(vehicleSearchRequest.getYear()) && !vehicleSearchRequest.getYear().isEmpty()) {
            Specification<Vehicle> byParam = VehicleSearchCriteriaSpecification
                    .hasYear(vehicleSearchRequest.getYear());
            vehicleSpecification = vehicleSpecification != null ? vehicleSpecification.and(byParam) : byParam;
        }
        if (Objects.nonNull(vehicleSearchRequest.getColor()) && !vehicleSearchRequest.getColor().isEmpty()) {
            Specification<Vehicle> byParam = VehicleSearchCriteriaSpecification
                    .hasColor(vehicleSearchRequest.getColor());
            vehicleSpecification = vehicleSpecification != null ? vehicleSpecification.and(byParam) : byParam;
        }

        if (Objects.isNull(vehicleSpecification))
            vehicleList = (List<Vehicle>) vehicleRepository.findAll();
        vehicleList = vehicleRepository.findAll(vehicleSpecification);
        if (vehicleList.size() == 0)
            throw new ResourceNotFoundException("Resource Not Present");
        return vehicleList;
    }
}
