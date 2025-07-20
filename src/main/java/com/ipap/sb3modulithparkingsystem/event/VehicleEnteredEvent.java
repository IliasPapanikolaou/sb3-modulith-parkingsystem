package com.ipap.sb3modulithparkingsystem.event;

import java.time.LocalDateTime;

public record VehicleEnteredEvent(String vehicleNumber, LocalDateTime entryTime) {
    // This record can be used to encapsulate the event of a vehicle entering the parking system
    // It contains the vehicle number and the time of entry
}
