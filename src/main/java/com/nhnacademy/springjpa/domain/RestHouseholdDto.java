package com.nhnacademy.springjpa.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestHouseholdDto {

    private Integer householdSerialNumber;

    @NotNull
    private Integer householdResidentSerialNumber;

    @NotNull
    private LocalDate householdCompositionDate;

    @NotBlank
    private String householdCompositionReasonCode;

    @NotBlank
    private String currentHouseMovementAddress;
}
