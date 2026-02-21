package com.patientManagement.hospital_management.Controllers;

import com.patientManagement.hospital_management.DTO.Request.DoctorRequestDto;
import com.patientManagement.hospital_management.DTO.Response.DoctorResponseDto;
import com.patientManagement.hospital_management.DTO.Update.UpdateDoctorDto;
import com.patientManagement.hospital_management.Service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/doctor")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @GetMapping("/all")
    public ResponseEntity<Page<DoctorResponseDto>> getAllDoctors(
            @RequestParam(defaultValue = "0" ) int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ){
        return ResponseEntity.ok(doctorService.getAllDoctorsPagingWay(page, size, sortBy, direction));
    }

    @GetMapping("/id/{doctorId}")
    public ResponseEntity<DoctorResponseDto> getDoctorById(
            @PathVariable Long doctorId
    ){
        return ResponseEntity.ok(doctorService.getDoctorById(doctorId));
    }

    @PostMapping("/create")
    public ResponseEntity<DoctorResponseDto> createDoctor(
            @Valid
            @RequestBody DoctorRequestDto doctorDto
            ){
return ResponseEntity.ok(doctorService.createDoctor(doctorDto));
    }

    @PatchMapping("/update/{doctorId}")
    public ResponseEntity<DoctorResponseDto> updateDoctor(
            @Valid
            @PathVariable  Long doctorId,
            @RequestBody UpdateDoctorDto doctorDto
            ){
        return ResponseEntity.ok(doctorService.updateDoctor(doctorId , doctorDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id){
        return ResponseEntity.ok(doctorService.deleteDoctor(id));
    }
}
