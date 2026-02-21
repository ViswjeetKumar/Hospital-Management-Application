package com.patientManagement.hospital_management.Entity;

import com.patientManagement.hospital_management.Entity.TypeOptions.BloodGroupType;
import com.patientManagement.hospital_management.Entity.TypeOptions.GenderType;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(
        name = "patient_table",
        uniqueConstraints  = {
                @UniqueConstraint(name = "name_gmail_constraint", columnNames = {"name", "gmail"})
},
        indexes = {
                @Index(name = "name_index", columnList = "name"),
                @Index(name = "createdAt_index", columnList = "createdAt")
        }
)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PatientEntity {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, length = 40)
    String name;
    LocalDate DOB;
    String gmail;
    @Enumerated(value = EnumType.STRING)
    GenderType genderType;
    @Enumerated(value = EnumType.STRING)
    BloodGroupType bloodGroupType;
@CreationTimestamp
    LocalDateTime createdAt;

@OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true) // MERGE will work at updation and persist will help to save the patient at the first time.
        @JoinColumn(name = "patient_insurance_id")
    InsuranceEntity insuranceEntity; //This is woning side

@OneToMany(mappedBy = "patientEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    Set<AppointmentEntity> appointmentEntities = new HashSet<>(); // inverse side

//    @Override
//    public String toString() {
//        return "PatientEntity{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", appointments=" + appointmentEntities.size() +
//                '}';
//    }
}
