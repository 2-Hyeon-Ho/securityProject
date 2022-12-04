package com.nhnacademy.springjpa.repository.birth;

import com.nhnacademy.springjpa.entity.BirthDeathReportResident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirthRepository extends JpaRepository<BirthDeathReportResident, BirthDeathReportResident.Pk> {
    BirthDeathReportResident findByPk_BirthDeathTypeCodeAndPk_ResidentSerialNumberAndPk_ReportResidentSerialNumber(String type, int targetNumber, int reportResidentNumber);
}