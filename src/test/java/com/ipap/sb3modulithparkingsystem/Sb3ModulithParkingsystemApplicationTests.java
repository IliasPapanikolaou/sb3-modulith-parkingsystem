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
        ApplicationModules modules = ApplicationModules.of(Sb3ModulithParkingsystemApplication.class).verify();
        // This test ensures that the application context loads correctly
        new Documenter(modules).writeDocumentation();
    }

}
