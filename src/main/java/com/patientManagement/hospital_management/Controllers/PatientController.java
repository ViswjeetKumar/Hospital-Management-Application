package com.patientManagement.hospital_management.Controllers;

import com.patientManagement.hospital_management.DTO.Request.CreatePatientRequestDto;
import com.patientManagement.hospital_management.DTO.Response.PatientResponseDto;
import com.patientManagement.hospital_management.DTO.Update.UpdatePatientRequestDto;
import com.patientManagement.hospital_management.Entity.TypeOptions.BloodGroupType;
import com.patientManagement.hospital_management.Entity.TypeOptions.GenderType;
import com.patientManagement.hospital_management.Service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class PatientController {
 private final PatientService patientService;

 @PostMapping("/create")
    public ResponseEntity<PatientResponseDto> createPatient(
         @Valid
            @RequestBody CreatePatientRequestDto patientDto
         ){
        return new ResponseEntity<>(patientService.createPatient(patientDto), HttpStatus.CREATED);
 }

 @GetMapping("/all")
 public ResponseEntity<Page<PatientResponseDto>> getAllPatient(
         @RequestParam(defaultValue = "0" ) int page,
         @RequestParam(defaultValue = "5") int size,
         @RequestParam(defaultValue = "name") String sortBy,
         @RequestParam(defaultValue = "desc") String direction
 ){
     return ResponseEntity.ok(patientService.getAllPatients(page, size,sortBy, direction));
 }

 @GetMapping("/id/{patientId}")
 public ResponseEntity<PatientResponseDto> getPatientById(
         @PathVariable  Long patientId
 ){
     return ResponseEntity.ok(patientService.getPatientById(patientId));
 }

@PatchMapping("/update/{patientId}")
public ResponseEntity<PatientResponseDto> updatePatientPartially(
        @PathVariable Long patientId,
        @Valid
        @RequestBody UpdatePatientRequestDto dto
        ){
     return ResponseEntity.ok(patientService.updatePatientPartially(patientId, dto));
}

 @GetMapping("/meta/genders")
    public GenderType [] getAllGentdType(){
     return GenderType.values();
 }

 @GetMapping("/meta/all-blood-groups")
    public BloodGroupType[] getAllBloodGroups(){
     return BloodGroupType.values();
 }
}
