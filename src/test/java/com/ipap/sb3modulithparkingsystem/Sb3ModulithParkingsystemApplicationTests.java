package com.ipap.sb3modulithparkingsystem;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

@Slf4j
@SpringBootTest
class Sb3ModulithParkingsystemApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void createModuleDocumentation() {
        // This method is used to generate documentation for the application modules
        // It uses the Documenter class to create a documentation file
        ApplicationModules modules = ApplicationModules.of(Sb3ModulithParkingsystemApplication.class);

        // Log the names and base packages of the modules
        for (var module : modules) {
            log.info("Module: {} : {}", module.getName(), module.getBasePackage());
        }

        // Verify that the modules are loaded correctly
        modules.verify();

        // Create documentation for the modules
        new Documenter(modules)
                .writeDocumentation()
                .writeIndividualModulesAsPlantUml();
        log.info("Module documentation created successfully.");
    }
}
