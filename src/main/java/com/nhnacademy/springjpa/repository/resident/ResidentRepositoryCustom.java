package com.nhnacademy.springjpa.repository.resident;

import com.nhnacademy.springjpa.domain.ResidentDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface
ResidentRepositoryCustom {
    List<ResidentDto> getFamilyRelation(String id);
}
