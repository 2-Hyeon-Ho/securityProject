package com.nhnacademy.springjpa.repository.familyRelation;

import com.nhnacademy.springjpa.entity.FamilyRelationship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FamilyRelationRepository extends JpaRepository<FamilyRelationship, FamilyRelationship.Pk>, FamilyRelationRepositoryCustom {
    List<FamilyRelationship> findFamilyRelationshipByPk_BaseResidentSerialNumber(int serialNumber);
    FamilyRelationship findFamilyRelationshipByPk_BaseResidentSerialNumberAndAndPk_FamilyResidentRegistrationNumber(int baseNumber, int residentRegistrationNumber);
}
