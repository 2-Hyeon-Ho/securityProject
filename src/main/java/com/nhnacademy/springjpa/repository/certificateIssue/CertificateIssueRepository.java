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

}
