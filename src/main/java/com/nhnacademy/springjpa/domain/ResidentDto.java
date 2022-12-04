package com.nhnacademy.springjpa.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nhnacademy.springjpa.entity.FamilyRelationship;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ResidentDto {
    private String familyRelationshipCode;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime birthDate;
    private String residentRegistrationNumber;
    private String genderCode;
}
