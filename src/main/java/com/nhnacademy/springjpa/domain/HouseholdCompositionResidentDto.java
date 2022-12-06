package com.nhnacademy.springjpa.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class HouseholdCompositionResidentDto {
    private String householdRelationshipCode;
    private String name;
    private String residentRegistrationNumber;
    private LocalDateTime reportDate;
    private String householdCompositionChangeReasonCode;
}
