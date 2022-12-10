package com.nhnacademy.springjpa.repository.household;

import com.nhnacademy.springjpa.entity.Household;
import com.nhnacademy.springjpa.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdRepository extends JpaRepository<Household, Integer> {
    Household findHouseHoldByHouseholdSerialNumber(int householdSerialNumber);


}
