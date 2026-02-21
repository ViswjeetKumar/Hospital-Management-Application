package com.patientManagement.hospital_management.Repository;

import com.patientManagement.hospital_management.Entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentEntityRepository extends JpaRepository<DepartmentEntity, Long> {
}