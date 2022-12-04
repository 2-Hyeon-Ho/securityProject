package com.nhnacademy.springjpa.service.birth;

import com.nhnacademy.springjpa.domain.RestBirthDto;
import com.nhnacademy.springjpa.entity.BirthDeathReportResident;
import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.exception.BirthReportExistException;
import com.nhnacademy.springjpa.repository.birthDeath.BirthDeathRepository;
import com.nhnacademy.springjpa.repository.resident.ResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class BirthRegistrationService {
    private final BirthDeathRepository birthDeathRepository;
    private final ResidentRepository residentRepository;

    public BirthRegistrationService(BirthDeathRepository birthDeathRepository, ResidentRepository residentRepository) {
        this.birthDeathRepository = birthDeathRepository;
        this.residentRepository = residentRepository;
    }

    public BirthDeathReportResident registerBirth(int reportResidentNumber, RestBirthDto birth) {
        BirthDeathReportResident birthReport = birthDeathRepository.findByPk_BirthDeathTypeCodeAndPk_ResidentSerialNumberAndPk_ReportResidentSerialNumber(birth.getBirthDeathTypeCode(), birth.getResidentSerialNumber(), reportResidentNumber);
        if(!Objects.isNull(birthReport)) {
            throw new BirthReportExistException();
        }

        Resident resident = residentRepository.findResidentByResidentId(reportResidentNumber);

        BirthDeathReportResident newBirthReport = new BirthDeathReportResident();
        newBirthReport.setPk(new BirthDeathReportResident.Pk(birth.getBirthDeathTypeCode(), birth.getResidentSerialNumber(), reportResidentNumber));
        newBirthReport.setBirthDeathReportDate(birth.getBirthDeathReportDate());
        newBirthReport.setBirthReportQualificationsCode(birth.getBirthReportQualificationsCode());
        newBirthReport.setEmailAddress(birth.getEmailAddress());
        newBirthReport.setPhoneNumber(birth.getPhoneNumber());
        newBirthReport.setResident(resident);

        return birthDeathRepository.save(newBirthReport);
    }
}
