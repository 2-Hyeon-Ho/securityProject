package com.nhnacademy.springjpa.service.familyRelation;

import com.nhnacademy.springjpa.entity.FamilyRelationship;
import com.nhnacademy.springjpa.exception.RelationshipNotFoundException;
import com.nhnacademy.springjpa.repository.familyRelation.FamilyRelationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class FamilyRelationDeleteService {
    private final FamilyRelationRepository familyRelationRepository;

    public FamilyRelationDeleteService(FamilyRelationRepository familyRelationRepository) {
        this.familyRelationRepository = familyRelationRepository;
    }

    public void deleteFamilyRelationship(int baseNumber, int targetNumber) {
        FamilyRelationship relationship = familyRelationRepository.findFamilyRelationshipByPk_BaseResidentSerialNumberAndAndPk_FamilyResidentRegistrationNumber(baseNumber, targetNumber);
        if(Objects.isNull(relationship)) {
            throw new RelationshipNotFoundException();
        }

        familyRelationRepository.delete(relationship);
    }
}
