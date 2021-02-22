package com.vehicle.repositiory;

import com.vehicle.entity.Dealer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DealerRepository extends CrudRepository<Dealer, Integer> {
    Optional<Dealer> findByDealerIdAndCode(long dealerId, String listingId);
}
