package com.nhnacademy.springjpa.service.resident;

import com.nhnacademy.springjpa.domain.RestResidentDto;
import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.exception.ResidentExistException;
import com.nhnacademy.springjpa.repository.resident.ResidentRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class ResidentRegistrationService {

    private final ResidentRepository residentRepository;
    private final PasswordEncoder passwordEncoder;

    public ResidentRegistrationService(ResidentRepository residentRepository, PasswordEncoder passwordEncoder) {
        this.residentRepository = residentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Resident residentRegistration(RestResidentDto resident) {
        if(!Objects.isNull(residentRepository.findResidentByResidentId(resident.getResidentId()))) {
            throw new ResidentExistException();
        }

        Resident newResident = new Resident();

        newResident.setResidentId(resident.getResidentId());
        newResident.setName(resident.getName());
        newResident.setResidentRegistrationNumber(resident.getResidentRegistrationNumber());
        newResident.setGenderCode(resident.getGenderCode());
        newResident.setBirthDate(resident.getBirthDate());
        newResident.setBirthPlaceCode(resident.getBirthPlaceCode());
        newResident.setRegistrationBaseAddress(resident.getRegistrationBaseAddress());
        newResident.setId(resident.getId());
        newResident.setPassword(passwordEncoder.encode(resident.getPassword()));
        newResident.setEmail(resident.getEmail());

        return residentRepository.save(newResident);
    }
}
