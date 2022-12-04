package com.nhnacademy.springjpa.service.familyRelation;

import com.nhnacademy.springjpa.domain.ResidentDto;
import com.nhnacademy.springjpa.exception.ResidentNotFoundException;
import com.nhnacademy.springjpa.repository.resident.ResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class FamilyRelationInquiryService {
    private final ResidentRepository residentRepository;

    public FamilyRelationInquiryService(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    public List<ResidentDto> viewFamily(String name, String registrationNumber) {
        if(Objects.isNull(residentRepository.findResidentByNameAndAndResidentRegistrationNumber(name, registrationNumber))) {
            throw new ResidentNotFoundException();
        }

        return residentRepository.getFamilyRelationshipCertificate(name, registrationNumber);
    }
}
