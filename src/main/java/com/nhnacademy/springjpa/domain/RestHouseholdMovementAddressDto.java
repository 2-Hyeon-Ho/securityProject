package com.nhnacademy.springjpa.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestHouseholdMovementAddressDto {

    private LocalDate houseMovementReportDate;

    @NotBlank
    private String houseMovementAddress;

    private String lastAddressYn;
}
