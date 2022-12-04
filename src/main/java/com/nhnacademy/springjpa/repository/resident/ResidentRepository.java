package com.nhnacademy.springjpa.repository.resident;

import com.nhnacademy.springjpa.domain.ResidentDto;
import com.nhnacademy.springjpa.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResidentRepository extends JpaRepository<Resident, Integer>, ResidentRepositoryCustom {
    Resident findResidentByResidentId(int residentId);

    Resident findResidentByNameAndAndResidentRegistrationNumber(String name, String residentId);

    @Query("select new com.nhnacademy.springjpa.domain.ResidentDto(fr.familyRelationshipCode, r.name, r.birthDate, " +
            "r.residentRegistrationNumber, r.genderCode) from Resident as r " +
            "inner join FamilyRelationship as fr " +
            "on r.residentId = fr.pk.familyResidentRegistrationNumber " +
            "where fr.pk.baseResidentSerialNumber = " +
            "(select r.residentId from Resident as r where r.name = ?1 and r.residentRegistrationNumber = ?2)")
    List<ResidentDto> getFamilyRelationshipCertificate(@Param("name") String name, @Param("personId") String registrationNumber);
}
