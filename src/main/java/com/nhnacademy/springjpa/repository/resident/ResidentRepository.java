package com.nhnacademy.springjpa.repository.resident;

import com.nhnacademy.springjpa.domain.ResidentDto;
import com.nhnacademy.springjpa.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ResidentRepository extends JpaRepository<Resident, Integer>, ResidentRepositoryCustom {
    Resident findResidentByResidentId(int residentId);

    Resident findResidentById(String id);

    Optional<Resident> findById(String id);

    Resident findByEmail(String email);

    @Query("select new com.nhnacademy.springjpa.domain.ResidentDto(fr.familyRelationshipCode, r.name, r.birthDate, " +
            "r.residentRegistrationNumber, r.genderCode) from Resident as r " +
            "inner join FamilyRelationship as fr " +
            "on r.residentId = fr.pk.familyResidentRegistrationNumber " +
            "where fr.pk.baseResidentSerialNumber = " +
            "(select r.residentId from Resident as r where r.id = ?1)")
    List<ResidentDto> getFamilyRelationshipCertificate(String id);
}
