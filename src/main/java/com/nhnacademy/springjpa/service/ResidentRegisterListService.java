package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.domain.HouseholdCompositionResidentDto;
import com.nhnacademy.springjpa.domain.ResidentDto;
import com.nhnacademy.springjpa.exception.ResidentNotFoundException;
import com.nhnacademy.springjpa.repository.certificateIssue.CertificateIssueRepository;
import com.nhnacademy.springjpa.repository.resident.ResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ResidentRegisterListService {
    private final CertificateIssueRepository certificateIssueRepository;
    private final ResidentRepository residentRepository;

    public ResidentRegisterListService(CertificateIssueRepository certificateIssueRepository, ResidentRepository residentRepository) {
        this.certificateIssueRepository = certificateIssueRepository;
        this.residentRepository = residentRepository;
    }

    public List<HouseholdCompositionResidentDto> viewResidentRegisterList(String name, String registrationNumber) {
        if(Objects.isNull(residentRepository.findResidentByNameAndAndResidentRegistrationNumber(name, registrationNumber))) {
            throw new ResidentNotFoundException();
        }

        return certificateIssueRepository.getResidentRegisterList(name, registrationNumber);

    }
}
