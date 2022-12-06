package com.nhnacademy.springjpa.repository.certificateIssue;

import com.nhnacademy.springjpa.domain.HouseholdCompositionResidentDto;
import com.nhnacademy.springjpa.entity.CertificateIssue;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface CertificateIssueRepositoryCustom {
    List<HouseholdCompositionResidentDto> residentRegisterList();
}
