package com.patientManagement.hospital_management.DTO.Response;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PatientWithAppointmentResponseDto {
    Long patientId;
    String patientName;
    LocalDate patientDob;
    String patientGmail;
    List<AppointmentResponseDto1> appointmentResponseDto1List;
}
