package com.ipap.sb3modulithparkingsystem.allocation;

import com.ipap.sb3modulithparkingsystem.event.VehicleEnteredEvent;
import com.ipap.sb3modulithparkingsystem.event.VehicleExitedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SlotAllocationService {

    private final SlotRepository slotRepository;

    @EventListener
    public void handleVehicleEntry(VehicleEnteredEvent vehicleEnteredEvent) {
        // Logic to allocate a slot for the vehicle
        // This could involve checking available slots and updating the slot status
        // For example, find an available slot and mark it as occupied by the vehicle
        Slot slot = slotRepository.findFirstByAvailableTrue()
                .orElseThrow(() -> new RuntimeException("No available parking slots found"));
        slot.setAvailable(false);
        slot.setVehicleNumber(vehicleEnteredEvent.vehicleNumber());
        slotRepository.save(slot);
        log.info("Allocated slot {} for vehicle {}", slot.getSlotCode(), vehicleEnteredEvent.vehicleNumber());
    }

    @EventListener
    public void handleVehicleExit(VehicleExitedEvent vehicleExitedEvent) {
        // Logic to free up the slot when a vehicle exits
        // This could involve finding the slot occupied by the vehicle and marking it as available
        Optional<Slot> optionalSlot = slotRepository.findByVehicleNumber(vehicleExitedEvent.vehicleNumber());
        if (optionalSlot.isPresent()) {
            Slot slot = optionalSlot.get();
            slot.setAvailable(true);
            slot.setVehicleNumber(null); // Clear the vehicle number
            slotRepository.save(slot);
            log.info("Freed up slot {} for vehicle {}", slot.getSlotCode(), vehicleExitedEvent.vehicleNumber());
        } else {
            log.warn("No slot found for vehicle {}", vehicleExitedEvent.vehicleNumber());
        }
    }
}
