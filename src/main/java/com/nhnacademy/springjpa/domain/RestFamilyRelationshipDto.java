package com.nhnacademy.springjpa.domain;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestFamilyRelationshipDto {
    private Integer familyResidentRegistrationNumber;

    @NotBlank
    private String familyRelationshipCode;
}
