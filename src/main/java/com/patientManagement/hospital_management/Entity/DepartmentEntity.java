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
public class DepartmentEntity {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

@Column(nullable = false, length = 70, unique = true)
    String name;

@OneToOne
    @JoinColumn(nullable = false)
    DoctorEntity headDoctor;

@ManyToMany
    Set<DoctorEntity> doctors = new HashSet<>();


}
