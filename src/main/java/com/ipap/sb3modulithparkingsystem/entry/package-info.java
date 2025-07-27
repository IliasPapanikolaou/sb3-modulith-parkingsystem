// Set strict boundaries for the entry module
// It can only depend on the 'allocation' and 'event' modules, ensuring a clear
// separation of concerns and modularity in the application architecture.
// This helps maintain a clean architecture and allows for easier maintenance and testing.
// The 'entry' module is responsible for handling vehicle entries, while the 'allocation' module
// manages parking slot allocations, and the 'event' module handles events related to vehicle entries and
@ApplicationModule(displayName = "Entry Module", allowedDependencies = {"allocation","event"})
package com.ipap.sb3modulithparkingsystem.entry;

import org.springframework.modulith.ApplicationModule;