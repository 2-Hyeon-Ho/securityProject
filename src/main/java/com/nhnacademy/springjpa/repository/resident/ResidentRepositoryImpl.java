package com.nhnacademy.springjpa.repository.resident;

import com.nhnacademy.springjpa.domain.ResidentDto;
import com.nhnacademy.springjpa.entity.QFamilyRelationship;
import com.nhnacademy.springjpa.entity.QResident;
import com.nhnacademy.springjpa.entity.Resident;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.querydsl.jpa.JPAExpressions.select;


@Repository
public class ResidentRepositoryImpl extends QuerydslRepositorySupport implements ResidentRepositoryCustom {
    public ResidentRepositoryImpl() {
        super(Resident.class);
    }
    @Override
    public List<ResidentDto> getFamilyRelation(String id) {
        QResident resident = QResident.resident;
        QFamilyRelationship familyRelationship = QFamilyRelationship.familyRelationship;
        /*
        select fr.family_relationship_code as '구분', r.name as '성명', r.birth_date as '출생연월일', r.resident_registration_number as '주민등록번호', r.gender_code as '성별'
from resident as r
	inner join family_relationship as fr on r.resident_serial_number=fr.family_resident_serial_number
where fr.family_resident_serial_number =
		any(select fr.family_resident_serial_number From family_relationship as fr
        where fr.base_resident_serial_number = 4)
        and fr.base_resident_serial_number = 4;
         */

        JPQLQuery<ResidentDto> querydsl = from(resident)
                .innerJoin(resident.familyRelationship, familyRelationship)
                .where(familyRelationship.pk.familyResidentRegistrationNumber.eqAny(getFamilyResidentSerialNumber(id))
                        .and(familyRelationship.pk.baseResidentSerialNumber.eq(getResidentId(id))))
                .select(Projections.constructor(
                        ResidentDto.class,
                        familyRelationship.familyRelationshipCode,
                        resident.name,
                        resident.birthDate,
                        resident.residentRegistrationNumber,
                        resident.genderCode
                ));

        return querydsl.fetch();
    }

    public SubQueryExpression<? extends Integer> getFamilyResidentSerialNumber(String id) {
        QFamilyRelationship familyRelationship = QFamilyRelationship.familyRelationship;

        JPQLQuery<Integer> querydsl = from(familyRelationship)
                .where(familyRelationship.pk.baseResidentSerialNumber.eq(getResidentId(id)))
                .select(familyRelationship.pk.familyResidentRegistrationNumber);
        return querydsl;
    }

    public Integer getResidentId(String id) {
        QResident resident = QResident.resident;

        return from(resident)
                .where(resident.id.eq(id))
                .select(resident.residentId).fetchOne();
    }
}
