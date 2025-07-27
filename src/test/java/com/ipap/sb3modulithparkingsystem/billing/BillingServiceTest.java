package com.ipap.sb3modulithparkingsystem.billing;

import com.ipap.sb3modulithparkingsystem.event.VehicleExitedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

// @ApplicationModuleTest
class BillingServiceTest {

    @Mock
    private BillingRepository billingRepository;

    @InjectMocks
    private BillingService billingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleVehicleExit_createsBillingRecordWithCorrectAmount() {
        // Arrange
        String vehicleNumber = "ABC123";
        LocalDateTime entryTime = LocalDateTime.now().minusHours(3);
        LocalDateTime exitTime = LocalDateTime.now();
        VehicleExitedEvent event = new VehicleExitedEvent(vehicleNumber, entryTime, exitTime);

        // Act
        billingService.handleVehicleExit(event);

        // Assert
        ArgumentCaptor<BillingRecord> captor = ArgumentCaptor.forClass(BillingRecord.class);
        verify(billingRepository).save(captor.capture());
        BillingRecord savedRecord = captor.getValue();
        assertThat(savedRecord.getVehicleNumber()).isEqualTo(vehicleNumber);
        assertThat(savedRecord.getAmount()).isGreaterThanOrEqualTo(20.0);
        assertThat(savedRecord.getBillingTime()).isNotNull();
    }

    @Test
    void handleVehicleExit_minimumAmountIs20() {
        // Arrange
        String vehicleNumber = "XYZ789";
        LocalDateTime entryTime = LocalDateTime.now().minusMinutes(30);
        LocalDateTime exitTime = LocalDateTime.now();
        VehicleExitedEvent event = new VehicleExitedEvent(vehicleNumber, entryTime, exitTime);

        // Act
        billingService.handleVehicleExit(event);

        // Assert
        ArgumentCaptor<BillingRecord> captor = ArgumentCaptor.forClass(BillingRecord.class);
        verify(billingRepository).save(captor.capture());
        BillingRecord savedRecord = captor.getValue();
        assertThat(savedRecord.getAmount()).isEqualTo(20.0);
    }
}

