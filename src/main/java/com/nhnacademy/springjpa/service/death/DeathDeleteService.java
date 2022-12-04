package com.nhnacademy.springjpa.service.death;

import com.nhnacademy.springjpa.entity.BirthDeathReportResident;
import com.nhnacademy.springjpa.exception.BirthReportNotFoundException;
import com.nhnacademy.springjpa.exception.DeathReportNotFoundException;
import com.nhnacademy.springjpa.repository.birthDeath.BirthDeathRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class DeathDeleteService {
    private final BirthDeathRepository birthDeathRepository;

    public DeathDeleteService(BirthDeathRepository birthDeathRepository) {
        this.birthDeathRepository = birthDeathRepository;
    }

    public void deleteDeath(int reportResidentNumber, int targetNumber) {
        BirthDeathReportResident deathReport = birthDeathRepository.findByPk_BirthDeathTypeCodeAndPk_ResidentSerialNumberAndPk_ReportResidentSerialNumber("사망", targetNumber, reportResidentNumber);
        if(Objects.isNull(deathReport)) {
            throw new DeathReportNotFoundException();
        }

        birthDeathRepository.delete(deathReport);
    }
}
