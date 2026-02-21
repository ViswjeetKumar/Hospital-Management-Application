package com.patientManagement.hospital_management.DTO.Response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoctorResponseDto {
    Long doctorId;
    @Size(min = 3, max = 50)
    String doctorName;

    @Size(min = 5, max = 60)
    String doctorSpecialization;
    @Email
    @Size(min = 5, max = 60)
    String doctorGmail;
    Boolean isActive;
}
