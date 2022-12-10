package com.nhnacademy.springjpa.service.resident;

import com.nhnacademy.springjpa.domain.ResidentPasswordDto;
import com.nhnacademy.springjpa.domain.RestResidentDto;
import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.exception.ResidentNotFoundException;
import com.nhnacademy.springjpa.repository.resident.ResidentRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class ResidentModifyService {
    private final ResidentRepository residentRepository;
    private final PasswordEncoder passwordEncoder;

    public ResidentModifyService(ResidentRepository residentRepository, PasswordEncoder passwordEncoder) {
        this.residentRepository = residentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Resident residentModify(int residentId, RestResidentDto resident) {
        Resident modifyResident = residentRepository.findResidentByResidentId(residentId);
        if(Objects.isNull(modifyResident)) {
            throw new ResidentNotFoundException();
        }

        modifyResident.setName(resident.getName());
        modifyResident.setResidentRegistrationNumber(resident.getResidentRegistrationNumber());
        modifyResident.setGenderCode(resident.getGenderCode());
        modifyResident.setBirthDate(resident.getBirthDate());
        modifyResident.setBirthPlaceCode(resident.getBirthPlaceCode());
        modifyResident.setRegistrationBaseAddress(resident.getRegistrationBaseAddress());
        modifyResident.setId(resident.getId());
        modifyResident.setPassword(passwordEncoder.encode(resident.getPassword()));
        modifyResident.setEmail(resident.getEmail());

        return residentRepository.save(modifyResident);
    }

    public void passwordModify(int residentId, ResidentPasswordDto resident) {
        Resident modifyResident = residentRepository.findResidentByResidentId(residentId);
        if(Objects.isNull(modifyResident)) {
            throw new ResidentNotFoundException();
        }

        modifyResident.setPassword(passwordEncoder.encode(resident.getPassword()));

        residentRepository.save(modifyResident);
    }
}
