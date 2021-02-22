package com.vehicle.specification;

import com.vehicle.constant.Constant;
import com.vehicle.entity.Vehicle;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class VehicleSearchCriteriaSpecification {

    public static Specification<Vehicle> hasMake(Object make) {
        return (vehicleRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(vehicleRoot.get(Constant.MAKE), make);
    }

    public static Specification<Vehicle> hasModel(Object model) {
        return (vehicleRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(vehicleRoot.get(Constant.MODEL), model);
    }

    public static Specification<Vehicle> hasYear(Object year) {
        return (vehicleRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(vehicleRoot.get(Constant.YEAR), year);
    }
    public static Specification<Vehicle> hasColor(Object color) {
        return (vehicleRoot, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(vehicleRoot.get(Constant.COLOR), color);
    }
}
