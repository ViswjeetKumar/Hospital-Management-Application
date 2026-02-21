package com.patientManagement.hospital_management.DTO.Response;

import com.patientManagement.hospital_management.Entity.DoctorEntity;
import com.patientManagement.hospital_management.Entity.PatientEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentResponseDto {
    Long id;
    LocalDateTime appointmentTime;
    String reason;
    Long doctorId;
    String doctorName;
    Long patientId;
    String patientName;
}
