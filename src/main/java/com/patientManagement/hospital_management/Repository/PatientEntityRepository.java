package com.patientManagement.hospital_management.Repository;

import com.patientManagement.hospital_management.Entity.PatientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientEntityRepository extends JpaRepository<PatientEntity, Long> {
//    @Query("SELECT DISTINCT p FROM PatientEntity p LEFT JOIN FETCH p.appointmentEntities")
@Query("""
    SELECT DISTINCT p 
    FROM PatientEntity p
    LEFT JOIN FETCH p.appointmentEntities a
    LEFT JOIN FETCH a.doctorEntity
""")
    List<PatientEntity> findAllPatientWithAppointments();

    Page<PatientEntity> findAll(Pageable pageable);

}