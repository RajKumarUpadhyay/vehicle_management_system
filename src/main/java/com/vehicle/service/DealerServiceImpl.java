package com.vehicle.service;

import com.vehicle.entity.Dealer;
import com.vehicle.entity.Vehicle;
import com.vehicle.repositiory.DealerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DealerServiceImpl implements DealerService {

    @Autowired
    DealerRepository dealerRepository;
    @Autowired
    VehicleService vehicleService;

    @Override
    public void saveOrUpdate(List<Dealer> saveDealerWithVehiclesList) {
        try {
            saveDealerWithVehiclesList.forEach(saveDealerVehicleList -> {
                if (dealerRepository.findByDealerIdAndCode(saveDealerVehicleList.getDealerId(), saveDealerVehicleList.getCode())
                        .isPresent()) {
                    update(saveDealerVehicleList);
                } else {
                    create(saveDealerVehicleList);
                }
            });
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }


    @Transactional
    private void create(Dealer dealer) {
        vehicleService.createVehicle(dealer.getVehicle());
        dealerRepository.save(dealer);
    }

    @Transactional
    private void update(Dealer dealerVehicleInfo) {
        Optional<Dealer> existing = dealerRepository
                .findByDealerIdAndCode(dealerVehicleInfo.getDealerId(), dealerVehicleInfo.getCode());
        if (existing.isPresent()) {
            Dealer updateExistingDealer = existing.get();
            Vehicle sourceCar = dealerVehicleInfo.getVehicle();
            Vehicle updateExistingVehicle = updateExistingDealer.getVehicle();

            updateExistingVehicle.setPrice(sourceCar.getPrice());
            updateExistingVehicle.setModel(sourceCar.getModel());
            updateExistingVehicle.setMake(sourceCar.getMake());
            updateExistingVehicle.setKW(sourceCar.getKW());
            updateExistingVehicle.setColor(sourceCar.getColor());
            updateExistingVehicle.setYear(sourceCar.getYear());
            dealerRepository.save(updateExistingDealer);
        }
    }
}
