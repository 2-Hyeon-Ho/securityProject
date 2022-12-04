package com.nhnacademy.springjpa.service.familyRelation;

import com.nhnacademy.springjpa.domain.RestFamilyRelationshipDto;
import com.nhnacademy.springjpa.entity.FamilyRelationship;
import com.nhnacademy.springjpa.exception.ResidentNotFoundException;
import com.nhnacademy.springjpa.repository.familyRelation.FamilyRelationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class FamilyRelationModifyService {
    private final FamilyRelationRepository familyRelationRepository;

    public FamilyRelationModifyService(FamilyRelationRepository familyRelationRepository) {
        this.familyRelationRepository = familyRelationRepository;
    }

    public FamilyRelationship modifyFamilyRelationship(int baseNumber, int targetNumber, RestFamilyRelationshipDto familyRelationship) {
        FamilyRelationship relationship = familyRelationRepository.findFamilyRelationshipByPk_BaseResidentSerialNumberAndAndPk_FamilyResidentRegistrationNumber(baseNumber, targetNumber);
        if(Objects.isNull(relationship)) {
            throw new ResidentNotFoundException();
        }

        relationship.setFamilyRelationshipCode(familyRelationship.getFamilyRelationshipCode());

        return familyRelationRepository.save(relationship);
    }
}
