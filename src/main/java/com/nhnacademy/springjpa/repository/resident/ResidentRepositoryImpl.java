package com.nhnacademy.springjpa.repository.resident;

import com.nhnacademy.springjpa.domain.ResidentDto;
import com.nhnacademy.springjpa.entity.QFamilyRelationship;
import com.nhnacademy.springjpa.entity.QResident;
import com.nhnacademy.springjpa.entity.Resident;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ResidentRepositoryImpl extends QuerydslRepositorySupport implements ResidentRepositoryCustom {
    public ResidentRepositoryImpl() {
        super(Resident.class);
    }

    @Override
    public List<ResidentDto> getFamilyRelation(String id) {
        QResident resident = QResident.resident;
        QResident subResident = new QResident("sub");
        QFamilyRelationship familyRelationship = QFamilyRelationship.familyRelationship;
        /*
            select  fr.family_relationship_code as '구분',
			r.name as '성명',
			r.birth_date as '출생연월일',
            r.resident_registration_number as '주민등록번호',
            r.gender_code as '성별'
	from family_relationship as fr
	inner join resident as r
		on fr.family_resident_serial_number = r.resident_serial_number
	left join resident as rr
		on fr.base_resident_serial_number = rr.resident_serial_number
    where rr.resident_serial_number =4
         */
        return from(familyRelationship)
                .innerJoin(familyRelationship.resident, resident)
                .innerJoin(familyRelationship.resident, subResident)
                .where(subResident.residentId.eq(getResidentId(id)))
                .select(Projections.constructor(
                        ResidentDto.class,
                        familyRelationship.familyRelationshipCode,
                        resident.name,
                        resident.birthDate,
                        resident.residentRegistrationNumber,
                        resident.genderCode
                )).fetch();
    }

//    public List<Integer> getFamilyResidentSerialNumber(String id) {
//        QFamilyRelationship familyRelationship = QFamilyRelationship.familyRelationship;
//
//        return from(familyRelationship)
//                .where(familyRelationship.pk.baseResidentSerialNumber.eq(getResidentId(id)))
//                .select(familyRelationship.pk.familyResidentRegistrationNumber)
//                .fetch();
//    }

    public Integer getResidentId(String id) {
        QResident resident = QResident.resident;

        return from(resident)
                .where(resident.id.eq(id))
                .select(resident.residentId).fetchOne();
    }
}
