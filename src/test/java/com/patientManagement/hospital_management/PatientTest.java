package com.patientManagement.hospital_management;

import com.patientManagement.hospital_management.Entity.PatientEntity;
import com.patientManagement.hospital_management.Repository.PatientEntityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PatientTest {
    @Autowired
    PatientEntityRepository patientRepository;

    @Test
    public void patientTesting(){
        List<PatientEntity> allPatient = patientRepository.findAllPatientWithAppointments();
        System.out.println(allPatient);
    }
}
