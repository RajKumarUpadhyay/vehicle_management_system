package com.vehicle.repositiory;

import com.vehicle.entity.Vehicle;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VehicleRepository extends CrudRepository<Vehicle, Integer>, JpaSpecificationExecutor<Vehicle> {
    List<Vehicle> findAll(Specification<Vehicle> specification);
}
