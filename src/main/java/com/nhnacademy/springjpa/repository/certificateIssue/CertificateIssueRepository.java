package com.nhnacademy.springjpa.repository.certificateIssue;

import com.nhnacademy.springjpa.domain.HouseholdCompositionResidentDto;
import com.nhnacademy.springjpa.entity.CertificateIssue;
import com.nhnacademy.springjpa.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CertificateIssueRepository extends JpaRepository<CertificateIssue, Long> {
    CertificateIssue findByResidentAndAndCertificateCode(Resident resident, String certificateCode);

    /*
    select hc.household_relationship_code as '세대주 관계', r.name as '성명', r.resident_registration_number as '주민등록번호',
hc.report_date as '신고일', hc.household_composition_change_reason_code as '변동사유'
from household_composition_resident as hc
join resident as r ON hc.resident_serial_number = r.resident_serial_number
join household as h ON hc.household_serial_number = h.household_serial_number
where h.household_serial_number = 1 and h.household_resident_serial_number = 4
order by hc.report_date;
     */
    @Query("select new com.nhnacademy.springjpa.domain.HouseholdCompositionResidentDto(" +
            "hc.householdRelationshipCode, r.name, r.residentRegistrationNumber, hc.reportDate, " +
            "hc.householdCompositionChangeReasonCode) " +
            "from HouseholdCompositionResident as hc " +
            "inner join Resident as r " +
            "on hc.pk.residentSerialNumber = r.residentId " +
            "inner join Household as h " +
            "on hc.pk.householdSerialNumber = h.householdSerialNumber " +
            "where h.householdSerialNumber = 1 and h.resident.residentId = " +
            "(select r.residentId from Resident as r where r.name = ?1 and r.residentRegistrationNumber = ?2)")
    List<HouseholdCompositionResidentDto> getResidentRegisterList(@Param("name") String name, @Param("personId") String registrationNumber);
}
