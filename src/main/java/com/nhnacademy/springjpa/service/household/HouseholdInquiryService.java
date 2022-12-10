package com.nhnacademy.springjpa.service.household;

import com.nhnacademy.springjpa.domain.HouseholdCompositionResidentDto;
import com.nhnacademy.springjpa.entity.CertificateIssue;
import com.nhnacademy.springjpa.entity.Household;
import com.nhnacademy.springjpa.entity.HouseholdCompositionResident;
import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.exception.ResidentNotFoundException;
import com.nhnacademy.springjpa.repository.HouseholdCompositionResident.HouseholdCompositionResidentRepository;
import com.nhnacademy.springjpa.repository.certificateIssue.CertificateIssueRepository;
import com.nhnacademy.springjpa.repository.household.HouseholdRepository;
import com.nhnacademy.springjpa.repository.householdMovementAddress.HouseholdMovementAddressRepository;
import com.nhnacademy.springjpa.repository.resident.ResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class HouseholdInquiryService {
    private final HouseholdCompositionResidentRepository householdCompositionResidentRepository;
    private final ResidentRepository residentRepository;
    private final CertificateIssueRepository certificateIssueRepository;
    private final HouseholdRepository householdRepository;

    public HouseholdInquiryService(HouseholdCompositionResidentRepository householdCompositionResidentRepository, ResidentRepository residentRepository, CertificateIssueRepository certificateIssueRepository, HouseholdRepository householdRepository) {
        this.householdCompositionResidentRepository = householdCompositionResidentRepository;
        this.residentRepository = residentRepository;
        this.certificateIssueRepository = certificateIssueRepository;
        this.householdRepository = householdRepository;
    }

    public List<HouseholdCompositionResidentDto> viewResidentRegisterList(String id) {
        if(Objects.isNull(residentRepository.findResidentById(id))) {
            throw new ResidentNotFoundException();
        }

        return householdCompositionResidentRepository.getResidentRegisterList(id);

    }

    public CertificateIssue viewCertificateIssue(String id) {
        Resident resident = residentRepository.findResidentById(id);
        if(Objects.isNull(resident)) {
            throw new ResidentNotFoundException();
        }

        return certificateIssueRepository.findByResidentAndAndCertificateCode(resident, "주민등록등본");
    }

    public Household getHouseholdResident(String id) {
        Resident resident = residentRepository.findResidentById(id);
        if(Objects.isNull(resident)) {
            throw new ResidentNotFoundException();
        }
        Integer householdSerialNumber = householdCompositionResidentRepository.findByResident(resident).getPk().getHouseholdSerialNumber();

        return householdRepository.findHouseHoldByHouseholdSerialNumber(householdSerialNumber);
    }
}
