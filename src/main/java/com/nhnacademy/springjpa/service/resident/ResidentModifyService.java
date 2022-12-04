package com.nhnacademy.springjpa.service.resident;

import com.nhnacademy.springjpa.domain.RestResidentDto;
import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.exception.ResidentNotFoundException;
import com.nhnacademy.springjpa.repository.resident.ResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class ResidentModifyService {
    private final ResidentRepository residentRepository;

    public ResidentModifyService(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
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

        return residentRepository.save(modifyResident);
    }
}
