package com.patientManagement.hospital_management.Service;

import com.patientManagement.hospital_management.DTO.Request.DoctorRequestDto;
import com.patientManagement.hospital_management.DTO.Response.DoctorResponseDto;
import com.patientManagement.hospital_management.DTO.Update.UpdateDoctorDto;
import com.patientManagement.hospital_management.Entity.DoctorEntity;
import com.patientManagement.hospital_management.Exceptions.ResourceNotFoundException;
import com.patientManagement.hospital_management.Repository.DoctorEntityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorEntityRepository doctorRepository;
    private final ModelMapper mapper;

    //Get All Doctor
    public Page<DoctorResponseDto> getAllDoctorsPagingWay(
            int page,
            int size,
            String sortBy,
            String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<DoctorEntity> doctors = doctorRepository.findByIsActiveTrue(pageable);

//        List<DoctorEntity> doctors = doctorRepository.findByIsActiveTrue();
        return doctors.map(doctor->mapper.map(doctor, DoctorResponseDto.class));
    }

    //Get doctor by ID
    public DoctorResponseDto getDoctorById(Long doctorId) {
        DoctorEntity doctor = doctorRepository.findById(doctorId).filter(DoctorEntity::getIsActive)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("there is no doctor with id: "+ doctorId)
                );
        return mapper.map(doctor, DoctorResponseDto.class);
    }

    //Create Doctor
    public DoctorResponseDto createDoctor(DoctorRequestDto doctorDto) {
        DoctorEntity doctor = mapper.map(doctorDto, DoctorEntity.class );
        DoctorEntity savedDoctor = doctorRepository.save(doctor);
        return mapper.map(savedDoctor, DoctorResponseDto.class);
    }

    //Update Doctor
    public DoctorResponseDto updateDoctor(Long doctorId ,UpdateDoctorDto doctorDto) {
        DoctorEntity doctor = doctorRepository.findById(doctorId).filter(DoctorEntity::getIsActive).orElseThrow(
                ()-> new ResourceNotFoundException("there is no Active doctor with this id: "+doctorId)
        );
        if(doctorDto.getName() != null){
            doctor.setName(doctorDto.getName());
        }
        if(doctorDto.isActive() == false){
            doctor.setIsActive(false);
        }
        if(doctorDto.getGmail() != null){
            doctor.setGmail(doctorDto.getGmail());
        }
        if(doctorDto.getSpecialization() != null){
            doctor.setSpecialization(doctorDto.getSpecialization());
        }
        DoctorEntity savedDoctor = doctorRepository.save(doctor);
        return mapper.map(savedDoctor, DoctorResponseDto.class);
    }

    public String deleteDoctor(Long id) {
        DoctorEntity doctor  = doctorRepository.findById(id).filter(DoctorEntity::getIsActive).orElseThrow(()-> new ResourceNotFoundException("there is no active doctor with id  "+id));
         doctor.setIsActive(false);
         return "doctor with id : "+id+" has softly deleted.";
    }
}
