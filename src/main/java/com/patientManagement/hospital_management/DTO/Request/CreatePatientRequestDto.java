package com.patientManagement.hospital_management.DTO.Request;

import com.patientManagement.hospital_management.Entity.TypeOptions.BloodGroupType;
import com.patientManagement.hospital_management.Entity.TypeOptions.GenderType;
import com.patientManagement.hospital_management.GroupValidations.CreatePatient;
import com.patientManagement.hospital_management.GroupValidations.UpdatePatient;
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
public class CreatePatientRequestDto {
    @NotBlank(message = "name should not blank", groups = CreatePatient.class)
    @Size(min = 3, max = 40, message = "name must be under 3 and 40 characters")
    String name;
    @NotNull(message = "dob is required field ")
    LocalDate dob;

    @Email(message = "enter a valid email")
    @NotBlank(message = "gmail is required field.")
    @Size(min = 7, max = 50, message = "enter length should be under 7 & 50 characters" )
    String gmail;

    @NotNull(message = "Gender is required")
    GenderType genderType;

    @NotNull(message = "blood group is required")
    BloodGroupType bloodGroupType;
}
