package com.ipap.sb3modulithparkingsystem.notification;

import com.ipap.sb3modulithparkingsystem.event.VehicleEnteredEvent;
import com.ipap.sb3modulithparkingsystem.event.VehicleExitedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationService {

    // This service can be used to send notifications to users
    // For example, it could send an email or SMS notification
    // when a vehicle enters or exits the parking system.

    @EventListener
    public void notifyOnVehicleEntry(VehicleEnteredEvent event) {
        // Logic to send notification (e.g., email, SMS)
        // For now, we will just log the message
        log.info("Notification: Vehicle {} has entered the parking system at {}",
                event.vehicleNumber(), event.entryTime());
    }

    @EventListener
    public void notifyOnVehicleExit(VehicleExitedEvent event) {
        // Logic to send notification for vehicle exit
        // For now, we will just log the message
        log.info("Notification: Vehicle {} has exited the parking system", event.vehicleNumber());
    }
}
