package com.nhnacademy.springjpa.service.familyRelation;

import com.nhnacademy.springjpa.domain.ResidentDto;
import com.nhnacademy.springjpa.entity.CertificateIssue;
import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.exception.ResidentNotFoundException;
import com.nhnacademy.springjpa.repository.certificateIssue.CertificateIssueRepository;
import com.nhnacademy.springjpa.repository.resident.ResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class FamilyRelationInquiryService {
    private final ResidentRepository residentRepository;
    private final CertificateIssueRepository certificateIssueRepository;

    public FamilyRelationInquiryService(ResidentRepository residentRepository, CertificateIssueRepository certificateIssueRepository) {
        this.residentRepository = residentRepository;
        this.certificateIssueRepository = certificateIssueRepository;
    }

    public List<ResidentDto> viewFamily(String name, String registrationNumber) {
        if(Objects.isNull(residentRepository.findResidentByNameAndAndResidentRegistrationNumber(name, registrationNumber))) {
            throw new ResidentNotFoundException();
        }

        return residentRepository.getFamilyRelationshipCertificate(name, registrationNumber);
    }

    public CertificateIssue viewCertificateIssue(String name, String registrationNumber) {
        Resident resident = residentRepository.findResidentByNameAndAndResidentRegistrationNumber(name, registrationNumber);
        if(Objects.isNull(resident)) {
            throw new ResidentNotFoundException();
        }

        return certificateIssueRepository.findByResidentAndAndCertificateCode(resident, "가족관계증명서");
    }

    public Resident getResident(String name, String registrationNumber) {
        Resident resident = residentRepository.findResidentByNameAndAndResidentRegistrationNumber(name, registrationNumber);
        if(Objects.isNull(resident)) {
            throw new ResidentNotFoundException();
        }

        return resident;
    }
}
