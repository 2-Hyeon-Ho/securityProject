package com.nhnacademy.springjpa.service.death;

import com.nhnacademy.springjpa.domain.RestDeathDto;
import com.nhnacademy.springjpa.entity.BirthDeathReportResident;
import com.nhnacademy.springjpa.exception.DeathReportNotFoundException;
import com.nhnacademy.springjpa.repository.birthDeath.BirthDeathRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class DeathModifyService {
    private final BirthDeathRepository birthDeathRepository;

    public DeathModifyService(BirthDeathRepository birthDeathRepository) {
        this.birthDeathRepository = birthDeathRepository;
    }

    public BirthDeathReportResident modifyDeath(int reportResidentNumber, int targetNumber, RestDeathDto death) {
        BirthDeathReportResident deathReport = birthDeathRepository.findByPk_BirthDeathTypeCodeAndPk_ResidentSerialNumber(death.getBirthDeathTypeCode(), targetNumber);
        if(Objects.isNull(deathReport)) {
            throw new DeathReportNotFoundException();
        }

        deathReport.setBirthDeathReportDate(death.getBirthDeathReportDate());
        deathReport.setDeathReportQualificationsCode(death.getDeathReportQualificationsCode());
        deathReport.setEmailAddress(death.getEmailAddress());
        deathReport.setPhoneNumber(death.getPhoneNumber());

        return birthDeathRepository.save(deathReport);
    }
}
