package com.patientManagement.hospital_management.Service;

import com.patientManagement.hospital_management.DTO.Request.InsuranceRequestDTO;
import com.patientManagement.hospital_management.DTO.Response.InsuranceResponseDTO;
import com.patientManagement.hospital_management.Entity.InsuranceEntity;
import com.patientManagement.hospital_management.Entity.PatientEntity;
import com.patientManagement.hospital_management.Exceptions.ResourceNotFoundException;
import com.patientManagement.hospital_management.Repository.InsuranceEntityRepository;
import com.patientManagement.hospital_management.Repository.PatientEntityRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class InsuranceService {
    private final InsuranceEntityRepository insuranceEntityRepository;
    private final PatientEntityRepository patientEntityRepository;
    private final ModelMapper mapper;

    @Transactional
    public InsuranceResponseDTO assignInsuranceToPatient(InsuranceRequestDTO insuranceDto, Long patientId){
        PatientEntity newPatient = patientEntityRepository.findById(patientId)
                .orElseThrow(()-> new ResourceNotFoundException("patient not found with this ID: "+ patientId));
        //DTO to Entity
        InsuranceEntity insurance = mapper.map(insuranceDto, InsuranceEntity.class);
        //Business logic
        insurance.setCreatedAt(LocalDateTime.now());
        // Set relationship
        newPatient.setInsuranceEntity(insurance);
        insurance.setPatientEntity(newPatient); // ByDirectional mapping

//        patientEntityRepository.save(newPatient);
        InsuranceEntity savedInsurense = insuranceEntityRepository.save(insurance);
        return mapper.map(savedInsurense, InsuranceResponseDTO.class);
    }

    public InsuranceResponseDTO getInsurenseByPatientId(Long patientId) {
        PatientEntity patient = patientEntityRepository.findById(patientId)
                .orElseThrow(
                        ()->new ResourceNotFoundException("there id no patient with id : "+patientId)
                );
        InsuranceEntity insurance = patient.getInsuranceEntity();
        if(insurance==null) throw new ResourceNotFoundException("there is no insurance with id : " + patientId);
        return mapper.map(insurance, InsuranceResponseDTO.class);
    }

    @Transactional
    public String disaccociateInsuranseFromPatient(Long patientId, Long insuranceId) {
        PatientEntity patient = patientEntityRepository.findById(patientId).orElseThrow(()-> new ResourceNotFoundException("there is no patient with id : "+patientId));
        InsuranceEntity insurance = insuranceEntityRepository.findById(insuranceId).orElseThrow(()-> new ResourceNotFoundException("there is no  insurance with id : "+insuranceId));

        if(patient.getInsuranceEntity() == null){
            throw new IllegalStateException("Patient has no insurance to remove.");
        } else if (!patient.getInsuranceEntity().getId().equals(insuranceId)) {
            throw new IllegalStateException("This insurance is not linked to the given patient.");
        }

        patient.setInsuranceEntity(null); // Break relationship (owning side). orphanRemoval = true will automatically delete insurance
        insurance.setPatientEntity(null);  // If bidirectional

        return "Insurance removed successfully.";

    }
}
