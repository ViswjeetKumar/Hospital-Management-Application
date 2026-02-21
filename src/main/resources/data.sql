-- =========================
-- PATIENT DATA (10 Records)
-- =========================

INSERT INTO patient_table
(name, gender_type, dob, gmail, blood_group_type)
VALUES
('Aarav Sharma', 'MALE', '1990-05-10', 'aarav.sharma@example.com', 'O_POSITIVE'),
('Diya Patel', 'FEMALE', '1995-08-20', 'diya.patel@example.com', 'A_POSITIVE'),
('Dishant Verma', 'MALE', '1988-03-15', 'dishant.verma@example.com', 'A_POSITIVE'),
('Neha Iyer', 'FEMALE', '1992-12-01', 'neha.iyer@example.com', 'AB_POSITIVE'),
('Kabir Singh', 'MALE', '1993-07-11', 'kabir.singh@example.com', 'O_POSITIVE'),
('Ananya Gupta', 'FEMALE', '1997-04-18', 'ananya.gupta@example.com', 'B_POSITIVE'),
('Rohan Das', 'MALE', '1985-09-22', 'rohan.das@example.com', 'O_NEGATIVE'),
('Meera Nair', 'FEMALE', '1991-06-30', 'meera.nair@example.com', 'A_NEGATIVE'),
('Arjun Rao', 'MALE', '1989-11-05', 'arjun.rao@example.com', 'B_NEGATIVE'),
('Sanya Malhotra', 'FEMALE', '1994-02-14', 'sanya.malhotra@example.com', 'AB_NEGATIVE');


-- =========================
-- DOCTOR DATA (6 Records)
-- =========================

INSERT INTO doctor_entity (name, gmail, specialization, is_active)
VALUES
('Dr. Arjun Mehta', 'arjun.mehta@hospital.com', 'Cardiology', true),
('Dr. Priya Nair', 'priya.nair@hospital.com', 'Neurology', true),
('Dr. Rohan Kapoor', 'rohan.kapoor@hospital.com', 'Oncology', true),
('Dr. Sneha Reddy', 'sneha.reddy@hospital.com', 'Pediatrics', true),
('Dr. Vikram Desai', 'vikram.desai@hospital.com', 'Orthopedics', true),
('Dr. Kavya Sharma', 'kavya.sharma@hospital.com', 'Dermatology', true);


INSERT INTO appointment_entity
(appointment_time, reason, doctor_entity_id, patient_entity_id)
VALUES
('2025-07-01 10:30:00', 'General Checkup', 1, 2),
('2025-07-02 11:00:00', 'Skin Rash', 2, 2),
('2025-07-03 09:45:00', 'Knee Pain', 3, 3),
('2025-07-04 14:00:00', 'Follow-up Visit', 1, 1),
('2025-07-05 16:15:00', 'Consultation', 4, 4),
('2025-07-06 08:30:00', 'Allergy Treatment', 2, 5),
('2025-07-07 12:00:00', 'Diabetes Check', 3, 6),
('2025-07-08 15:45:00', 'Migraine Evaluation', 5, 7),
('2025-07-09 10:15:00', 'Orthopedic Review', 6, 8),
('2025-07-10 13:30:00', 'Blood Pressure Check', 1, 9),
('2025-07-11 09:00:00', 'Cardiac Screening', 1, 10),
('2025-07-12 17:00:00', 'Thyroid Consultation', 4, 3),
('2025-07-13 11:20:00', 'Asthma Follow-up', 2, 6),
('2025-07-14 08:45:00', 'Routine Check', 5, 1),
('2025-07-15 16:40:00', 'Physiotherapy Session', 6, 4);
