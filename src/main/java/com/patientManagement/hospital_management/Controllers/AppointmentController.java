package com.patientManagement.hospital_management.Controllers;

import com.patientManagement.hospital_management.DTO.Request.AppointmentRequestDto;
import com.patientManagement.hospital_management.DTO.Response.AppointmentResponseDto;
import com.patientManagement.hospital_management.DTO.Response.PatientWithAppointmentResponseDto;
import com.patientManagement.hospital_management.DTO.Response.ReAssignAppointmentToAnotherDoctorResponseDto;
import com.patientManagement.hospital_management.Service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/api/appointment")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentResponseDto> setAppointment(
            @RequestParam Long doctorId,
            @RequestParam Long patientId,
            @RequestBody AppointmentRequestDto appointmentRequestDto
            ){
        return ResponseEntity.ok(
                appointmentService.createNewAppointment(
                        appointmentRequestDto,
                        doctorId,
                        patientId
                )
        );
    }

    @PostMapping("/re-assign-doctor-to-appointment")
    public ResponseEntity<ReAssignAppointmentToAnotherDoctorResponseDto> reAssignAppointmentToAnotherDoctor(
            @RequestParam Long appointmentId,
            @RequestParam Long doctorId
    ){
        return ResponseEntity.ok(appointmentService.reAssignAppointmentToAnotherDoctor(appointmentId, doctorId));
    }

    @GetMapping("/patient-with-appointment")
    public ResponseEntity<List<PatientWithAppointmentResponseDto>> findAllPatientWithAppointments(){
        return ResponseEntity.ok(appointmentService.findAllPatientWithAppointments());
    }
}
