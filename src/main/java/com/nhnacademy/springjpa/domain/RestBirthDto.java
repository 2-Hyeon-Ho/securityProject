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
public class BirthDto {
    private Integer residentSerialNumber;

    @NotBlank
    private String birthDeathTypeCode;

    @NotNull
    private LocalDate birthDeathReportDate;

    @NotBlank
    private String birthReportQualificationsCode;

    @Email
    private String emailAddress;

    @NotBlank
    private String phoneNumber;
}
