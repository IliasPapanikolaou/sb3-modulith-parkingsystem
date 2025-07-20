package com.ipap.sb3modulithparkingsystem.entry;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/parking")
@RequiredArgsConstructor
public class EntryController {

    private final EntryService entryService;
    private final ExitService exitService;

    @PostMapping("/entry")
    public ResponseEntity<String> vehicleEntry(String vehicleNumber) {
        log.info("Vehicle entry request received for vehicle number: {}", vehicleNumber);
        entryService.vehicleEntry(vehicleNumber);
        return ResponseEntity.ok("Vehicle entry successful for vehicle number: " + vehicleNumber);
    }

    @PostMapping("/exit")
    public ResponseEntity<String> vehicleExit(String vehicleNumber) {
        log.info("Vehicle exit request received for vehicle number: {}", vehicleNumber);
        exitService.vehicleExit(vehicleNumber);
        return ResponseEntity.ok("Vehicle " + vehicleNumber + " has exited the parking system successfully.");
    }
}
