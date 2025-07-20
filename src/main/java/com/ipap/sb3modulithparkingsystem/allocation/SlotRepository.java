package com.ipap.sb3modulithparkingsystem.allocation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SlotRepository extends JpaRepository<Slot, Long> {
    // Additional query methods can be defined here if needed
    Optional<Slot> findFirstByAvailableTrue();
    Optional<Slot> findByVehicleNumber(String vehicleNumber);
}
