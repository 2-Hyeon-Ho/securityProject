package com.nhnacademy.springjpa.service.household;

import com.nhnacademy.springjpa.domain.RestHouseholdDto;
import com.nhnacademy.springjpa.entity.Household;
import com.nhnacademy.springjpa.exception.HouseholdExistException;
import com.nhnacademy.springjpa.repository.household.HouseholdRepository;
import com.nhnacademy.springjpa.repository.resident.ResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class HouseholdRegistrationService {
    private final HouseholdRepository householdRepository;
    private final ResidentRepository residentRepository;

    public HouseholdRegistrationService(HouseholdRepository householdRepository, ResidentRepository residentRepository) {
        this.householdRepository = householdRepository;
        this.residentRepository = residentRepository;
    }

    public Household householdRegistration(RestHouseholdDto household) {
        if(!Objects.isNull(householdRepository.findHouseHoldByHouseholdSerialNumber(household.getHouseholdSerialNumber()))) {
            throw new HouseholdExistException();
        }

        Household newHousehold = new Household();

        newHousehold.setHouseholdSerialNumber(household.getHouseholdSerialNumber());
        newHousehold.setResident(residentRepository.findResidentByResidentId(household.getHouseholdResidentSerialNumber()));
        newHousehold.setHouseholdCompositionDate(household.getHouseholdCompositionDate());
        newHousehold.setHouseholdCompositionReasonCode(household.getHouseholdCompositionReasonCode());
        newHousehold.setCurrentHouseMovementAddress(household.getCurrentHouseMovementAddress());

        return householdRepository.save(newHousehold);
    }
}
