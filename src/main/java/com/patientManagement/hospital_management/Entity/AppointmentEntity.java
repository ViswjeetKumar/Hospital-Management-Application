package com.patientManagement.hospital_management.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentEntity {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    LocalDateTime appointmentTime;

    @Column(length = 360)
    String reason;

    @ManyToOne
            @JoinColumn(nullable = false)
    PatientEntity patientEntity; // woning side

    @ManyToOne
            @JoinColumn(nullable = false)
    DoctorEntity doctorEntity;
}
