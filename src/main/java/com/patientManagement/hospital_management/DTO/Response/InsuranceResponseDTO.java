package com.patientManagement.hospital_management.DTO.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InsuranceResponseDTO {
    Long id;
    String policyNumber;
    String provider;
    LocalDate validUntil;
}
