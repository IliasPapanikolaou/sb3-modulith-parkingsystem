package com.ipap.sb3modulithparkingsystem.entry;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkingEntryRepository extends JpaRepository<ParkingEntry, Long> {
    // Find latest active entry by vehicle number where active is true
    Optional<ParkingEntry> findTopByVehicleNumberAndActiveTrueOrderByIdDesc(String vehicleNumber);
}
