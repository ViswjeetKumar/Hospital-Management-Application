package com.patientManagement.hospital_management.DTO.Request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoctorRequestDto {
    @NotBlank(message = "doctor name is required field")
            @Size(min = 3, max = 50)
    String name;
    @NotBlank(message = "specialization is required field")
            @Size(min = 5, max = 60)
    String specialization;
    @Email
            @NotNull
            @Size(min = 5, max = 60)
    String gmail;

}
