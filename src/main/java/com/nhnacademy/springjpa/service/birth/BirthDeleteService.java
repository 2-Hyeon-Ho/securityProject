package com.nhnacademy.springjpa.service.birth;

import com.nhnacademy.springjpa.entity.BirthDeathReportResident;
import com.nhnacademy.springjpa.exception.BirthReportNotFoundException;
import com.nhnacademy.springjpa.repository.birthDeath.BirthDeathRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class BirthDeleteService {
    private final BirthDeathRepository birthDeathRepository;

    public BirthDeleteService(BirthDeathRepository birthDeathRepository) {
        this.birthDeathRepository = birthDeathRepository;
    }

    public void deleteBirth(int reportResidentNumber, int targetNumber) {
        BirthDeathReportResident birthReport = birthDeathRepository.findByPk_BirthDeathTypeCodeAndPk_ResidentSerialNumber("출생", targetNumber);
        if(Objects.isNull(birthReport)) {
            throw new BirthReportNotFoundException();
        }

        birthDeathRepository.delete(birthReport);
    }
}
