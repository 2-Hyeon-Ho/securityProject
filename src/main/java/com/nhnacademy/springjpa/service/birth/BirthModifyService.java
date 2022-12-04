package com.nhnacademy.springjpa.service.birth;

import com.nhnacademy.springjpa.domain.RestBirthDto;
import com.nhnacademy.springjpa.entity.BirthDeathReportResident;
import com.nhnacademy.springjpa.exception.BirthReportNotFoundException;
import com.nhnacademy.springjpa.repository.birthDeath.BirthDeathRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class BirthModifyService {
    private final BirthDeathRepository birthDeathRepository;

    public BirthModifyService(BirthDeathRepository birthDeathRepository) {
        this.birthDeathRepository = birthDeathRepository;
    }

    public BirthDeathReportResident modifyBirth(int reportResidentNumber, int targetNumber, RestBirthDto birth) {
        BirthDeathReportResident birthReport = birthDeathRepository.findByPk_BirthDeathTypeCodeAndPk_ResidentSerialNumberAndPk_ReportResidentSerialNumber(birth.getBirthDeathTypeCode(), targetNumber, reportResidentNumber);
        if(Objects.isNull(birthReport)) {
            throw new BirthReportNotFoundException();
        }

        birthReport.setBirthDeathReportDate(birth.getBirthDeathReportDate());
        birthReport.setBirthReportQualificationsCode(birth.getBirthReportQualificationsCode());
        birthReport.setEmailAddress(birth.getEmailAddress());
        birthReport.setPhoneNumber(birth.getPhoneNumber());

        return birthDeathRepository.save(birthReport);
    }
}
