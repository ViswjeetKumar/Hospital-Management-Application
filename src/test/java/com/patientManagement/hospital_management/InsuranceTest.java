package com.patientManagement.hospital_management;

import com.patientManagement.hospital_management.Entity.InsuranceEntity;
import com.patientManagement.hospital_management.Entity.PatientEntity;
import com.patientManagement.hospital_management.Service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class InsuranceTest {
    @Autowired
    private InsuranceService insuranceService;

//    @Test
//    void insuranceTesting(){
//        InsuranceEntity insurance = InsuranceEntity.builder()
//                .policyNumber("HDFC_10077")
//                .provider("HDFC")
//                .validUntil(LocalDate.of(2030,12,12))
//                .build();
//
//        insuranceService.assignInsuranceToPatient(insurance, 1L);
//    }


}
