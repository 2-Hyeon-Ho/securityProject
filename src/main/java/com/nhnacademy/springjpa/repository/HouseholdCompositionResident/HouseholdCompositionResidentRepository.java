package com.nhnacademy.springjpa.repository.HouseholdCompositionResident;

import com.nhnacademy.springjpa.domain.HouseholdCompositionResidentDto;
import com.nhnacademy.springjpa.entity.HouseholdCompositionResident;
import com.nhnacademy.springjpa.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HouseholdCompositionResidentRepository extends JpaRepository<HouseholdCompositionResident, HouseholdCompositionResident.Pk> {

    HouseholdCompositionResident findByResident(Resident resident);
    @Query("select new com.nhnacademy.springjpa.domain.HouseholdCompositionResidentDto(" +
            "hc.householdRelationshipCode, r.name, r.residentRegistrationNumber, hc.reportDate, " +
            "hc.householdCompositionChangeReasonCode) " +
            "from HouseholdCompositionResident as hc " +
            "inner join Resident as r " +
            "on hc.pk.residentSerialNumber = r.residentId " +
            "where hc.pk.householdSerialNumber = " +
            "(select hc.pk.householdSerialNumber from Resident as r inner join HouseholdCompositionResident as hc " +
            "on r.residentId = hc.pk.residentSerialNumber " +
            "where r.id = ?1)")
    List<HouseholdCompositionResidentDto> getResidentRegisterList(String id);
}
