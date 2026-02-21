package com.patientManagement.hospital_management.DTO.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentResponseDto1 {
    Long id;
    LocalDateTime appointmentTime;
    String reason;
    Long doctorId;
    String doctorName;
}
