package com.nhnacademy.springjpa.service.household;

import com.nhnacademy.springjpa.domain.RestHouseholdDto;
import com.nhnacademy.springjpa.entity.Household;
import com.nhnacademy.springjpa.exception.HouseholdNotFoundException;
import com.nhnacademy.springjpa.repository.household.HouseholdRepository;
import com.nhnacademy.springjpa.repository.resident.ResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class HouseholdModifyService {
    private final HouseholdRepository householdRepository;
    private final ResidentRepository residentRepository;

    public HouseholdModifyService(HouseholdRepository householdRepository, ResidentRepository residentRepository) {
        this.householdRepository = householdRepository;
        this.residentRepository = residentRepository;
    }

    public Household householdModify(int householdSerialNumber, RestHouseholdDto household) {
        Household existingHousehold = householdRepository.findHouseHoldByHouseholdSerialNumber(household.getHouseholdSerialNumber());
        if(Objects.isNull(existingHousehold)) {
            throw new HouseholdNotFoundException();
        }

        householdRepository.delete(existingHousehold);

        Household modifyHousehold = new Household();

        modifyHousehold.setHouseholdSerialNumber(householdSerialNumber);
        modifyHousehold.setResident(residentRepository.findResidentByResidentId(household.getHouseholdResidentSerialNumber()));
        modifyHousehold.setHouseholdCompositionDate(household.getHouseholdCompositionDate());
        modifyHousehold.setHouseholdCompositionReasonCode(household.getHouseholdCompositionReasonCode());
        modifyHousehold.setCurrentHouseMovementAddress(household.getCurrentHouseMovementAddress());

        return householdRepository.save(modifyHousehold);
    }
}
