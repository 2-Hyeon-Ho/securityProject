package com.nhnacademy.springjpa.repository.birthDeath;

import com.nhnacademy.springjpa.entity.BirthDeathReportResident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirthDeathRepository extends JpaRepository<BirthDeathReportResident, BirthDeathReportResident.Pk> {
    BirthDeathReportResident findByPk_BirthDeathTypeCodeAndPk_ResidentSerialNumberAndPk_ReportResidentSerialNumber(String type, int targetNumber, int reportResidentNumber);
}
