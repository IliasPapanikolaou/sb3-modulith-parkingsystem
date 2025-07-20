package com.ipap.sb3modulithparkingsystem.entry;

import com.ipap.sb3modulithparkingsystem.event.VehicleEnteredEvent;
import com.ipap.sb3modulithparkingsystem.event.VehicleExitedEvent;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExitService {

    private final ParkingEntryRepository parkingEntryRepository;
    private final ApplicationEventPublisher publisher;

    // Save vehicle exit details to DB

    @Transactional
    public void vehicleExit(String vehicleNumber) {
        // Fetch the entry details from the repository
        ParkingEntry parkingEntry = parkingEntryRepository
                .findTopByVehicleNumberAndActiveTrueOrderByIdDesc(vehicleNumber)
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found: " + vehicleNumber));

        // Log the exit event
        parkingEntry.setExitTime(LocalDateTime.now());
        parkingEntry.setActive(false); // Mark the entry as inactive
        log.info("Vehicle exiting: {} at: {}", vehicleNumber, parkingEntry.getExitTime());

        // Save the updated entry back to the repository
        parkingEntryRepository.save(parkingEntry);

        // Publish the VehicleEnteredEvent
        publisher.publishEvent(new VehicleExitedEvent(vehicleNumber, parkingEntry.getEntryTime(),
                parkingEntry.getExitTime()));

        // Remove the entry from the repository
        parkingEntryRepository.delete(parkingEntry);
    }


}
