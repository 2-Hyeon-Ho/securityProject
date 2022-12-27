package com.nhnacademy.springjpa.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "family_relationship")
public class FamilyRelationship {

    @EmbeddedId
    private Pk pk;

    @Column(name = "family_relationship_code")
    private String familyRelationshipCode;

    @MapsId("baseResidentSerialNumber")
    @ManyToOne
    @JoinColumn(
            referencedColumnName = "resident_serial_number",
            name = "base_resident_serial_number")
    private Resident resident;

//    @ManyToOne
//    @JoinColumn(referencedColumnName = )
//    private Resident resident;

    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public static class Pk implements Serializable {

        @Column(name = "family_resident_serial_number")
        private Integer familyResidentRegistrationNumber;

        @Column(name = "base_resident_serial_number")
        private Integer baseResidentSerialNumber;
    }
}
