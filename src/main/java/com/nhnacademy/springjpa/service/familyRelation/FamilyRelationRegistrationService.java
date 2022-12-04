package com.nhnacademy.springjpa.service.familyRelation;

import com.nhnacademy.springjpa.domain.RestFamilyRelationshipDto;
import com.nhnacademy.springjpa.entity.FamilyRelationship;
import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.exception.RelationshipExistException;
import com.nhnacademy.springjpa.repository.familyRelation.FamilyRelationRepository;
import com.nhnacademy.springjpa.repository.resident.ResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class FamilyRelationRegistrationService {
    private final FamilyRelationRepository familyRelationRepository;
    private final ResidentRepository residentRepository;

    public FamilyRelationRegistrationService(FamilyRelationRepository familyRelationRepository, ResidentRepository residentRepository) {
        this.familyRelationRepository = familyRelationRepository;
        this.residentRepository = residentRepository;
    }

    public FamilyRelationship registerFamilyRelationship(int serialNumber, RestFamilyRelationshipDto familyRelationship) {
        FamilyRelationship relationship = familyRelationRepository.findFamilyRelationshipByPk_BaseResidentSerialNumberAndAndPk_FamilyResidentRegistrationNumber(serialNumber, familyRelationship.getFamilyResidentRegistrationNumber());
        if(!Objects.isNull(relationship)) {
            throw new RelationshipExistException();
        }
        FamilyRelationship newFamilyRelationship = new FamilyRelationship();

        Resident resident = residentRepository.findResidentByResidentId(serialNumber);
        newFamilyRelationship.setPk(new FamilyRelationship.Pk(familyRelationship.getFamilyResidentRegistrationNumber(), serialNumber));
        newFamilyRelationship.setFamilyRelationshipCode(familyRelationship.getFamilyRelationshipCode());
        newFamilyRelationship.setResident(resident);

        return familyRelationRepository.save(newFamilyRelationship);
    }
}
