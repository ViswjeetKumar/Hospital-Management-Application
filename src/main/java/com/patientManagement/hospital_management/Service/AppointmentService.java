package com.patientManagement.hospital_management.Service;

import com.patientManagement.hospital_management.DTO.Request.AppointmentRequestDto;
import com.patientManagement.hospital_management.DTO.Response.AppointmentResponseDto;
import com.patientManagement.hospital_management.DTO.Response.AppointmentResponseDto1;
import com.patientManagement.hospital_management.DTO.Response.PatientWithAppointmentResponseDto;
import com.patientManagement.hospital_management.DTO.Response.ReAssignAppointmentToAnotherDoctorResponseDto;
import com.patientManagement.hospital_management.Entity.AppointmentEntity;
import com.patientManagement.hospital_management.Entity.DoctorEntity;
import com.patientManagement.hospital_management.Entity.PatientEntity;
import com.patientManagement.hospital_management.Repository.AppointmentEntityRepository;
import com.patientManagement.hospital_management.Repository.DoctorEntityRepository;
import com.patientManagement.hospital_management.Repository.PatientEntityRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentEntityRepository appointmentEntityRepository;
    private final DoctorEntityRepository doctorEntityRepository;
    private final PatientEntityRepository patientEntityRepository;
    private final ModelMapper mapper;

    public AppointmentResponseDto createNewAppointment(AppointmentRequestDto appointmentDto, Long doctorId, Long patientId){
        DoctorEntity doctor = doctorEntityRepository.findById(doctorId)
                .orElseThrow(
                        ()->new EntityNotFoundException("there is no doctor with id: "+doctorId)
                );
        PatientEntity patient = patientEntityRepository.findById(patientId)
                .orElseThrow(
                        ()->new EntityNotFoundException("there is no patient with id : "+patientId)
                );
        AppointmentEntity appointment = new AppointmentEntity();
        appointment.setAppointmentTime(appointmentDto.getAppointmentDateTime());
        appointment.setReason(appointmentDto.getReason());
        appointment.setPatientEntity(patient);
        appointment.setDoctorEntity(doctor);
        AppointmentEntity savedAppointment = appointmentEntityRepository.save(appointment);
        doctor.getAppointmentEntitySet().add(savedAppointment);
        patient.getAppointmentEntities().add(savedAppointment);


        AppointmentResponseDto responseDto = new AppointmentResponseDto();
        responseDto.setId(savedAppointment.getId());
        responseDto.setAppointmentTime(savedAppointment.getAppointmentTime());
        responseDto.setReason(savedAppointment.getReason());
        responseDto.setDoctorId(doctor.getId());
        responseDto.setDoctorName(doctor.getName());
        responseDto.setPatientId(patient.getId());
        responseDto.setPatientName(patient.getName());

        return responseDto;
    }

    @Transactional
    public ReAssignAppointmentToAnotherDoctorResponseDto reAssignAppointmentToAnotherDoctor(
            Long appointmentId,
            Long doctorId)
    {
    AppointmentEntity appointment = appointmentEntityRepository.findById(appointmentId)
            .orElseThrow(
                    ()->new EntityNotFoundException("there is no any appointment with id: "+appointmentId)
            );
        DoctorEntity doctor = doctorEntityRepository.findById(doctorId)
                .orElseThrow(
                        ()->new EntityNotFoundException("there is no doctor with id: "+doctorId)
                );
        DoctorEntity oldDoctor = appointment.getDoctorEntity();
        if(oldDoctor != null){
            oldDoctor.getAppointmentEntitySet().remove(appointment);
        }


        appointment.setDoctorEntity(doctor);
        doctor.getAppointmentEntitySet().add(appointment);

        ReAssignAppointmentToAnotherDoctorResponseDto response = new ReAssignAppointmentToAnotherDoctorResponseDto();
        response.setId(appointment.getId());
        response.setAppointmentTime(appointment.getAppointmentTime());
        response.setReason(appointment.getReason());
        response.setNewDoctorId(doctor.getId());
        response.setNewDoctorName(doctor.getName());
        response.setPatientId(appointment.getPatientEntity().getId());
        response.setPatientName(appointment.getPatientEntity().getName());

        return response;

    }


    public List<PatientWithAppointmentResponseDto> findAllPatientWithAppointments() {
        List<PatientEntity> patients = patientEntityRepository.findAllPatientWithAppointments();

        return patients.stream()
                .map(
                patient->{
                    PatientWithAppointmentResponseDto patientDto = new PatientWithAppointmentResponseDto();
                    patientDto.setPatientId(patient.getId());
                    patientDto.setPatientName(patient.getName());
                    patientDto.setPatientDob(patient.getDOB());
                    patientDto.setPatientGmail(patient.getGmail());

                    List<AppointmentResponseDto1> appointmentDtos= patient.getAppointmentEntities()
                            .stream()
                            .map(
                            appointment -> {
                                AppointmentResponseDto1 appointmentDto = new AppointmentResponseDto1();
                                appointmentDto.setId(appointment.getId());
                                appointmentDto.setAppointmentTime(appointment.getAppointmentTime());
                                appointmentDto.setReason(appointment.getReason());
                                appointmentDto.setDoctorId(appointment.getDoctorEntity().getId());
                                appointmentDto.setDoctorName(appointment.getDoctorEntity().getName());
                                return appointmentDto;
                            }
                    ).collect(Collectors.toList());
                    patientDto.setAppointmentResponseDto1List(appointmentDtos);
                    return patientDto;
                }
        ).collect(Collectors.toList());
    }
}
