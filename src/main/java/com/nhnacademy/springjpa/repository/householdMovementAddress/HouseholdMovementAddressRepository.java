package com.nhnacademy.springjpa.repository.householdMovementAddress;

import com.nhnacademy.springjpa.domain.HouseholdCompositionResidentDto;
import com.nhnacademy.springjpa.entity.HouseholdMovementAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface HouseholdMovementAddressRepository extends JpaRepository<HouseholdMovementAddress, HouseholdMovementAddress.Pk> {
    HouseholdMovementAddress findByPk_HouseMovementReportDateAndPk_HouseholdSerialNumber(LocalDate movementDate, int householdNumber);
    List<HouseholdMovementAddress> findByPk_HouseholdSerialNumber(int householdNumber);

}
