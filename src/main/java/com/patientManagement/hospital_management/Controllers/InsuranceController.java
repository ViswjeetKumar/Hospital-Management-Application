package com.patientManagement.hospital_management.Controllers;


import com.patientManagement.hospital_management.DTO.Request.InsuranceRequestDTO;
import com.patientManagement.hospital_management.DTO.Response.InsuranceResponseDTO;
import com.patientManagement.hospital_management.Service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/insurance")
@RequiredArgsConstructor
public class InsuranceController {
    private final InsuranceService insuranceService;

    @PostMapping("/assign/{patientId}")
    public ResponseEntity<InsuranceResponseDTO> assignInsuranceToPatient(
            @PathVariable Long patientId,
            @RequestBody InsuranceRequestDTO insuranceDto
    ){
        return ResponseEntity.ok(insuranceService.assignInsuranceToPatient(insuranceDto, patientId));
    }

    @GetMapping("/id/{patientId}")
    public ResponseEntity<InsuranceResponseDTO> getInsurenseByPatientId(
            @PathVariable Long patientId
    ){
        return ResponseEntity.ok(insuranceService.getInsurenseByPatientId(patientId));
    }

    @DeleteMapping("remove")
    public ResponseEntity<String> disaccociateInsuranseFromPatient(
            @RequestParam Long patientId,
            @RequestParam Long insuranceId
    ){
      return ResponseEntity.ok(insuranceService.disaccociateInsuranseFromPatient(patientId, insuranceId));
    }
}
