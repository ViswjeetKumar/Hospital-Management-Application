package com.patientManagement.hospital_management.Service;

import com.patientManagement.hospital_management.DTO.Request.CreatePatientRequestDto;
import com.patientManagement.hospital_management.DTO.Response.PatientResponseDto;
import com.patientManagement.hospital_management.DTO.Update.UpdatePatientRequestDto;
import com.patientManagement.hospital_management.Entity.PatientEntity;
import com.patientManagement.hospital_management.Exceptions.ResourceNotFoundException;
import com.patientManagement.hospital_management.Repository.PatientEntityRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientEntityRepository patientRepository;
    private final ModelMapper mapper;

    public PatientResponseDto createPatient(CreatePatientRequestDto patientDto) {
        PatientEntity newPatient = mapper.map(patientDto, PatientEntity.class);
        PatientEntity savedPatient = patientRepository.save(newPatient);
        return mapper.map(savedPatient, PatientResponseDto.class);
    }

    public Page<PatientResponseDto> getAllPatients(int page,int  size,String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<PatientEntity> allPatients = patientRepository.findAll(pageable);
        return allPatients
                .map(patient->mapper.map(patient, PatientResponseDto.class));
    }

    public PatientResponseDto getPatientById(Long patientId) {
        PatientEntity patient = patientRepository.findById(patientId).orElseThrow(()->
                new ResourceNotFoundException("there is no patient with id: "+ patientId));
        return mapper.map(patient, PatientResponseDto.class);
    }

    public PatientResponseDto updatePatientPartially(Long patientId, @Valid UpdatePatientRequestDto dto) {
        PatientEntity patient = patientRepository.findById(patientId)
                .orElseThrow(()->new ResourceNotFoundException("there is no patient with id: "+ patientId));

        if(dto.getName() != null){
            patient.setName(dto.getName());
        }
        if(dto.getDob() != null){
            patient.setDOB(dto.getDob());
        }
        if(dto.getGmail() != null){
            patient.setGmail(dto.getGmail());
        }
        if(dto.getBloodGroupType() !=null){
            patient.setBloodGroupType(dto.getBloodGroupType());
        }
        if(dto.getGenderType() != null){
            patient.setGenderType(dto.getGenderType());
        }
        PatientEntity savedPatient= patientRepository.save(patient);
        return mapper.map(savedPatient, PatientResponseDto.class);
    }
}
