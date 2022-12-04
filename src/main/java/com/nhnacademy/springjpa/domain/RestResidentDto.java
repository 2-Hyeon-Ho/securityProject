package com.nhnacademy.springjpa.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nhnacademy.springjpa.entity.FamilyRelationship;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResidentRequest {
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
}
