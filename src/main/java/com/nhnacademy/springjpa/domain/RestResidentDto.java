package com.nhnacademy.springjpa.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestResidentDto {
    private Integer residentId;

    @NotBlank
    private String name;

    @NotBlank
    private String residentRegistrationNumber;

    @NotBlank
    private String genderCode;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime birthDate;

    @NotBlank
    private String birthPlaceCode;

    @NotBlank
    private String registrationBaseAddress;

    @NotBlank
    private String id;

    @NotBlank
    private String password;

    private String email;
}
