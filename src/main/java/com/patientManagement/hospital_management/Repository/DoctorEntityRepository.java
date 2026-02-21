package com.patientManagement.hospital_management.Repository;

import com.patientManagement.hospital_management.Entity.DoctorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DoctorEntityRepository extends JpaRepository<DoctorEntity, Long> {
//    List<DoctorEntity> findByIsActiveTrue();
    Page<DoctorEntity> findByIsActiveTrue(Pageable pageable);
}