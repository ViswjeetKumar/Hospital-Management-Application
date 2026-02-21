package com.patientManagement.hospital_management.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoctorEntity {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 70)
    String name;

    @Column(length = 70)
    String specialization;

    @Column(nullable = false, length = 70, unique = true)
    String gmail;

    @Column(nullable = false)
    Boolean isActive = true;

    @OneToMany(mappedBy = "doctorEntity")
 Set<AppointmentEntity> appointmentEntitySet = new HashSet<>();
}
