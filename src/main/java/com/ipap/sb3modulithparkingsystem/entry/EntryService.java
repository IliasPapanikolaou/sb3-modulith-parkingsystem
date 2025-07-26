package com.ipap.sb3modulithparkingsystem.entry;

import com.ipap.sb3modulithparkingsystem.event.VehicleEnteredEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class EntryService {

    private final ParkingEntryRepository parkingEntryRepository;
    private final ApplicationEventPublisher publisher;

    // Save vehicle entry details to DB

    // Allocate a parking slot for the vehicle

    // Send a notification to the user with entry details

    @Transactional
    public void vehicleEntry(String vehicleNumber) {
        log.info("Vehicle entry initiated for vehicle number: {}", vehicleNumber);

        // Here you would typically interact with the repository to save the entry
        // and allocate a slot, but for now, we just log the action.

        ParkingEntry entry = new ParkingEntry(null, vehicleNumber, LocalDateTime.now(), null, true);
        parkingEntryRepository.save(entry);

        // Publish an event for Slot Allocation
        publisher.publishEvent(new VehicleEnteredEvent(vehicleNumber, LocalDateTime.now()));

        // Notify user (this could be an email, SMS, etc.)
        log.info("Notification sent to user for vehicle entry: {}", vehicleNumber);
    }


}
