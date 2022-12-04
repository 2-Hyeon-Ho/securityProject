package com.nhnacademy.springjpa.domain;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class DeathDto {
    private Integer residentSerialNumber;

    @NotBlank
    private String birthDeathTypeCode;

    @NotNull
    private LocalDate birthDeathReportDate;

    @NotBlank
    private String deathReportQualificationsCode;

    @Email
    private String emailAddress;

    @NotBlank
    private String phoneNumber;
}
