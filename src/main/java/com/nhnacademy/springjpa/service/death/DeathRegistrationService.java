package com.nhnacademy.springjpa.service.death;

import com.nhnacademy.springjpa.domain.RestDeathDto;
import com.nhnacademy.springjpa.entity.BirthDeathReportResident;
import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.exception.DeathReportExistException;
import com.nhnacademy.springjpa.repository.birthDeath.BirthDeathRepository;
import com.nhnacademy.springjpa.repository.resident.ResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class DeathRegistrationService {
    private final BirthDeathRepository birthDeathRepository;
    private final ResidentRepository residentRepository;

    public DeathRegistrationService(BirthDeathRepository birthDeathRepository, ResidentRepository residentRepository) {
        this.birthDeathRepository = birthDeathRepository;
        this.residentRepository = residentRepository;
    }

    public BirthDeathReportResident registerDeath(int reportResidentNumber, RestDeathDto death) {
        BirthDeathReportResident deathReport = birthDeathRepository.findByPk_BirthDeathTypeCodeAndPk_ResidentSerialNumberAndPk_ReportResidentSerialNumber(death.getBirthDeathTypeCode(), death.getResidentSerialNumber(), reportResidentNumber);
        if(!Objects.isNull(deathReport)) {
            throw new DeathReportExistException();
        }

        Resident resident = residentRepository.findResidentByResidentId(reportResidentNumber);

        BirthDeathReportResident newDeathReport = new BirthDeathReportResident();
        newDeathReport.setPk(new BirthDeathReportResident.Pk(death.getBirthDeathTypeCode(), death.getResidentSerialNumber(), reportResidentNumber));
        newDeathReport.setBirthDeathReportDate(death.getBirthDeathReportDate());
        newDeathReport.setDeathReportQualificationsCode(death.getDeathReportQualificationsCode());
        newDeathReport.setEmailAddress(death.getEmailAddress());
        newDeathReport.setPhoneNumber(death.getPhoneNumber());
        newDeathReport.setResident(resident);

        return birthDeathRepository.save(newDeathReport);
    }
}
