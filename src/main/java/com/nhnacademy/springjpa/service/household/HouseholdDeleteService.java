package com.nhnacademy.springjpa.service.household;

import com.nhnacademy.springjpa.entity.Household;
import com.nhnacademy.springjpa.exception.HouseholdNotFoundException;
import com.nhnacademy.springjpa.repository.household.HouseholdRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class HouseholdDeleteService {
    private final HouseholdRepository householdRepository;

    public HouseholdDeleteService(HouseholdRepository householdRepository) {
        this.householdRepository = householdRepository;
    }

    public void householdDelete(int householdSerialNumber) {
        Household household = householdRepository.findHouseHoldByHouseholdSerialNumber(householdSerialNumber);
        if(Objects.isNull(household)) {
            throw new HouseholdNotFoundException();
        }

        householdRepository.delete(household);
    }
}
