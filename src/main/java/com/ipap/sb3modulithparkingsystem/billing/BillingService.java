package com.ipap.sb3modulithparkingsystem.billing;

import com.ipap.sb3modulithparkingsystem.event.VehicleExitedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class BillingService {

    private final BillingRepository billingRepository;

    @EventListener
    public void handleVehicleExit(VehicleExitedEvent vehicleExitedEvent) {
        // Create a new billing record
        BillingRecord billingRecord = new BillingRecord();
        billingRecord.setVehicleNumber(vehicleExitedEvent.vehicleNumber());

        // Calculate the billing amount based on the entry and exit times
        Duration duration = Duration.between(vehicleExitedEvent.enteredTime(), vehicleExitedEvent.exitTime());
        Double amount = Math.max(20, (duration.toMinutes() / 60.0) * 10); // Example: 10 per hour, minimum 20
        billingRecord.setAmount(amount);
        billingRecord.setBillingTime(LocalDateTime.now());

        // Save the billing record to the repository
        billingRepository.save(billingRecord);

        // Log the billing information
        log.info("Billing record created for vehicle {}: amount = {}", vehicleExitedEvent.vehicleNumber(), amount);
    }
}
