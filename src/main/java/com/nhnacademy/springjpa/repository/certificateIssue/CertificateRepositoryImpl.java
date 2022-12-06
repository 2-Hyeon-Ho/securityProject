package com.nhnacademy.springjpa.repository.certificateIssue;

import com.nhnacademy.springjpa.domain.HouseholdCompositionResidentDto;
import com.nhnacademy.springjpa.entity.CertificateIssue;
import com.nhnacademy.springjpa.entity.QHousehold;
import com.nhnacademy.springjpa.entity.QHouseholdCompositionResident;
import com.nhnacademy.springjpa.entity.QResident;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CertificateRepositoryImpl extends QuerydslRepositorySupport implements CertificateIssueRepositoryCustom {
    public CertificateRepositoryImpl() {
        super(CertificateIssue.class);
    }

    @Override
    public List<HouseholdCompositionResidentDto> residentRegisterList() {
        QHouseholdCompositionResident compositionResident = QHouseholdCompositionResident.householdCompositionResident;
        QResident resident = QResident.resident;
        QHousehold household = QHousehold.household;

        return from(compositionResident)
                .join(compositionResident.resident, resident)
                .on(compositionResident.pk.residentSerialNumber.eq(resident.residentId))
                .join(compositionResident.houseHold, household)
                .on(compositionResident.pk.householdSerialNumber.eq(household.householdSerialNumber))
                .select(Projections.constructor(
                        HouseholdCompositionResidentDto.class,
                        compositionResident.householdRelationshipCode,
                        resident.name,
                        resident.residentRegistrationNumber,
                        compositionResident.reportDate,
                        compositionResident.householdCompositionChangeReasonCode
                )).fetch();
    }
}
