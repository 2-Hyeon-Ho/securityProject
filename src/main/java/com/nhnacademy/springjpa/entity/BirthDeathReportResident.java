package com.nhnacademy.springjpa.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "birth_death_report_resident")
public class BirthDeathReportResident {

    @EmbeddedId
    private Pk pk;

    @Column(name = "birth_death_report_date")
    private LocalDate birthDeathReportDate;

    @Column(name = "birth_report_qualifications_code")
    private String birthReportQualificationsCode;

    @Column(name = "death_report_qualifications_code")
    private String deathReportQualificationsCode;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(referencedColumnName = "resident_serial_number", name = "report_resident_serial_number")
    private Resident resident;

    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public static class Pk implements Serializable {

        @Column(name = "birth_death_type_code")
        private String birthDeathTypeCode;

        @Column(name = "resident_serial_number")
        private Integer residentSerialNumber;

//        @Column(name = "report_resident_serial_number")
//        private Integer reportResidentSerialNumber;
    }
}
