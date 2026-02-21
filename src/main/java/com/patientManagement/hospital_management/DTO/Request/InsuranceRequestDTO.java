package com.patientManagement.hospital_management.DTO.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InsuranceRequestDTO {
    String policyNumber;
    String provider;
    LocalDate validUntil;
}
