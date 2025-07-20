package com.ipap.sb3modulithparkingsystem.event;

import java.time.LocalDateTime;

public record VehicleExitedEvent(String vehicleNumber, LocalDateTime enteredTime, LocalDateTime exitTime) {
    // This record encapsulates the event of a vehicle exiting the parking system
    // It contains the vehicle number, the time of entry, and the time of exit
}
