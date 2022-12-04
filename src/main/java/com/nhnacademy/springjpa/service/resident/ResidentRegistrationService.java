package com.nhnacademy.springjpa.service.resident;

import com.nhnacademy.springjpa.domain.RestResidentDto;
import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.exception.ResidentExistException;
import com.nhnacademy.springjpa.repository.resident.ResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class ResidentRegistrationService {
    private final ResidentRepository residentRepository;

    public ResidentRegistrationService(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
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

        return residentRepository.save(newResident);
    }
}
