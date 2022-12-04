package com.nhnacademy.springjpa.domain;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FamilyRelationshipDto {
    private Integer familyResidentRegistrationNumber;

    @NotBlank
    private String familyRelationshipCode;
}
