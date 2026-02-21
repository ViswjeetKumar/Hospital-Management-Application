package com.patientManagement.hospital_management.DTO.Response;

import com.patientManagement.hospital_management.Entity.TypeOptions.BloodGroupType;
import com.patientManagement.hospital_management.Entity.TypeOptions.GenderType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PatientResponseDto {
    Long id;
    String patientName;
    LocalDate DOB;
    String gmail;
    GenderType genderType;
    BloodGroupType bloodGroupType;
    LocalDateTime createdAt;
}
