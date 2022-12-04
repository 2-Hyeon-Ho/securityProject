package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.domain.FamilyRelationshipDto;
import com.nhnacademy.springjpa.domain.ResidentDto;
import com.nhnacademy.springjpa.entity.Resident;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface ResidentRepositoryCustom {
    List<ResidentDto> getFamilyRelationshipCertificate(String name, String registrationNumber);

    List<FamilyRelationshipDto> getFamilyRelation(String name, String registrationNumber);
}
