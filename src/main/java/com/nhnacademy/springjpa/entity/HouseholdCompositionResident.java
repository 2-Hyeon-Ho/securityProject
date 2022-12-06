package com.nhnacademy.springjpa.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "household_composition_resident")
public class HouseholdCompositionResident {

    @EmbeddedId
    private Pk pk;

    @Column(name = "report_date")
    private LocalDateTime reportDate;

    @Column(name = "household_relationship_code")
    private String householdRelationshipCode;

    @Column(name = "household_composition_change_reason_code")
    private String householdCompositionChangeReasonCode;

    @MapsId("householdSerialNumber")
    @ManyToOne
    @JoinColumn(referencedColumnName = "household_serial_number", name = "household_serial_number")
    private Household houseHold;

    @MapsId("residentSerialNumber")
    @ManyToOne
    @JoinColumn(referencedColumnName = "resident_serial_number", name = "resident_serial_number")
    private Resident resident;

    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public static class Pk implements Serializable {

        @Column(name = "household_serial_number")
        private Integer householdSerialNumber;

        @Column(name = "resident_serial_number")
        private Integer residentSerialNumber;
    }
}
