package com.ipap.sb3modulithparkingsystem.billing;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepository extends JpaRepository<BillingRecord, Long> {
    // Additional query methods can be defined here if needed
    // For example, you might want to find billing records by vehicle number or billing time
}
