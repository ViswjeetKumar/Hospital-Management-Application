package com.patientManagement.hospital_management.Repository;

import com.patientManagement.hospital_management.Entity.InsuranceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceEntityRepository extends JpaRepository<InsuranceEntity, Long> {
}