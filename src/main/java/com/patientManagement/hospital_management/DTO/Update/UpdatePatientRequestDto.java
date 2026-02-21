package com.patientManagement.hospital_management.DTO.Update;


import com.patientManagement.hospital_management.Entity.TypeOptions.BloodGroupType;
import com.patientManagement.hospital_management.Entity.TypeOptions.GenderType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdatePatientRequestDto {
    @Size(min = 3, max = 40, message = "name must be under 3 and 40 characters")
    String name;

    LocalDate dob;

    @Email(message = "enter a valid gmail")
    @Size(min = 7, max = 50, message = "enter length should be under 7 & 50 characters" )
    String gmail;

    GenderType genderType;

    BloodGroupType bloodGroupType;
}
