package com.ipap.sb3modulithparkingsystem;

import com.ipap.sb3modulithparkingsystem.allocation.Slot;
import com.ipap.sb3modulithparkingsystem.allocation.SlotRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class Sb3ModulithParkingsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(Sb3ModulithParkingsystemApplication.class, args);
    }

    @Bean
    CommandLineRunner initSlotData(SlotRepository slotRepository) {
        return args -> {
            // Initialize some slots
            if (slotRepository.count() > 0) {
                log.info("Slots already initialized, skipping slot initialization.");
                return; // Slots already initialized
            }
            log.info("Initializing slots...");
            slotRepository.save(new Slot(null, "A1", true, null));
            slotRepository.save(new Slot(null, "A2", true, null));
            slotRepository.save(new Slot(null, "B1", true, null));
            slotRepository.save(new Slot(null, "B2", true, null));
        };
    }

}
