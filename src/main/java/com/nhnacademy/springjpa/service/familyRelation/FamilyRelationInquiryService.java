package com.nhnacademy.springjpa.service.familyRelation;

import com.nhnacademy.springjpa.domain.FamilyRelationshipDto;
import com.nhnacademy.springjpa.domain.ResidentDto;
import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.repository.ResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InquiryFamilyRelationService {
    private final ResidentRepository residentRepository;

    public InquiryFamilyRelationService(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    public List<ResidentDto> viewFamily(String name, String registrationNumber) {
        List<ResidentDto> familyRelationshipCertificate = residentRepository.getFamilyRelationshipCertificate(name, registrationNumber);
        List<FamilyRelationshipDto> familyRelation = residentRepository.getFamilyRelation(name, registrationNumber);

        int i = 0;
        for (ResidentDto residentDto : familyRelationshipCertificate) {
            residentDto.setFamilyRelationship(familyRelation.get(i).getFamilyRelationshipCode());
            i++;
        }

        return familyRelationshipCertificate;
    }
}
