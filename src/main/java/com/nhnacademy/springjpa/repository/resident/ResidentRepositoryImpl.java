package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.domain.FamilyRelationshipDto;
import com.nhnacademy.springjpa.domain.ResidentDto;
import com.nhnacademy.springjpa.entity.QFamilyRelationship;
import com.nhnacademy.springjpa.entity.QResident;
import com.nhnacademy.springjpa.entity.Resident;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.querydsl.jpa.JPAExpressions.select;


@Repository
public class ResidentRepositoryImpl extends QuerydslRepositorySupport implements ResidentRepositoryCustom {
    public ResidentRepositoryImpl() {
        super(Resident.class);
    }
    public Integer getResidentSerialNumber(String name, String registrationNumber) {
        QResident resident = QResident.resident;

        return from(resident)
                .where(resident.name.eq(name).and(resident.residentRegistrationNumber.eq(registrationNumber)))
                .select(resident.residentId)
                .fetchOne();
    }

    @Override
    public List<ResidentDto> getFamilyRelationshipCertificate(String name, String registrationNumber) {
        QResident resident = QResident.resident;
        QFamilyRelationship familyRelationship = QFamilyRelationship.familyRelationship;

        return from(resident)
                .innerJoin(resident.familyRelationship, familyRelationship)
//                .on(resident.residentId.eq(familyRelationship.pk.baseResidentSerialNumber))
                .where((familyRelationship.pk.familyResidentRegistrationNumber).eq(getResidentSerialNumber(name, registrationNumber)))
                .orderBy(resident.birthDate.asc())
                .select(Projections.constructor(
                        ResidentDto.class,
                        familyRelationship.familyRelationshipCode,
                        familyRelationship.resident.name,
                        familyRelationship.resident.birthDate,
                        familyRelationship.resident.residentRegistrationNumber,
                        familyRelationship.resident.genderCode
                )).fetch();
    }

    @Override
    public List<FamilyRelationshipDto> getFamilyRelation(String name, String registrationNumber) {
        QFamilyRelationship familyRelationship = QFamilyRelationship.familyRelationship;

        return from(familyRelationship)
                .where(familyRelationship.pk.baseResidentSerialNumber.eq(getResidentSerialNumber(name, registrationNumber)))
                .select(Projections.constructor(
                        FamilyRelationshipDto.class,
                        familyRelationship.familyRelationshipCode
                )).fetch();
    }
}
