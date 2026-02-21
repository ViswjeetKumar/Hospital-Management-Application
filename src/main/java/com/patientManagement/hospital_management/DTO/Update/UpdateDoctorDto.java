package com.patientManagement.hospital_management.DTO.Update;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateDoctorDto {
    @Size(max = 70)
    String name;

    @Size(max = 70)
    String specialization;

    @Email(message = "Enter valid email")
    String gmail;

    boolean isActive;
}
