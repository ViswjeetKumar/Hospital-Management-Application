package com.patientManagement.hospital_management.DTO.Response;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReAssignAppointmentToAnotherDoctorResponseDto {
    Long id;
    LocalDateTime appointmentTime;
    String reason;
    Long newDoctorId;
    String newDoctorName;
    Long patientId;
    String patientName;
}
