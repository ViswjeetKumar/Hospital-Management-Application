package com.patientManagement.hospital_management.Repository;

import com.patientManagement.hospital_management.Entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentEntityRepository extends JpaRepository<AppointmentEntity, Long> {
}