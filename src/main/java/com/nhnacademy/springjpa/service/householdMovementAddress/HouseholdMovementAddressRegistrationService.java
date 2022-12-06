package com.nhnacademy.springjpa.service.householdMovementAddress;

import com.nhnacademy.springjpa.domain.RestHouseholdMovementAddressDto;
import com.nhnacademy.springjpa.entity.Household;
import com.nhnacademy.springjpa.entity.HouseholdMovementAddress;
import com.nhnacademy.springjpa.exception.HouseholdMovementAddressExistException;
import com.nhnacademy.springjpa.repository.household.HouseholdRepository;
import com.nhnacademy.springjpa.repository.householdMovementAddress.HouseholdMovementAddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class HouseholdMovementAddressRegistrationService {
    private final HouseholdMovementAddressRepository householdMovementAddressRepository;
    private final HouseholdRepository householdRepository;

    public HouseholdMovementAddressRegistrationService(HouseholdMovementAddressRepository householdMovementAddressRepository,
                                                       HouseholdRepository householdRepository) {
        this.householdMovementAddressRepository = householdMovementAddressRepository;
        this.householdRepository = householdRepository;
    }

    public HouseholdMovementAddress householdMovementAddressRegistration(int householdSerialNumber,
                                                                         RestHouseholdMovementAddressDto householdMovementAddress) {
        HouseholdMovementAddress movementAddress = householdMovementAddressRepository
                        .findByPk_HouseMovementReportDateAndPk_HouseholdSerialNumber(householdMovementAddress.getHouseMovementReportDate(), householdSerialNumber);
        if(!Objects.isNull(movementAddress)) {
            throw new HouseholdMovementAddressExistException();
        }
        List<HouseholdMovementAddress> householdMovementAddresses = householdMovementAddressRepository.findByPk_HouseholdSerialNumber(householdSerialNumber);
        for (HouseholdMovementAddress address : householdMovementAddresses) {
            address.setLastAddressYn("N");
        }

        HouseholdMovementAddress newMovementAddress = new HouseholdMovementAddress();
        Household household = householdRepository.findHouseHoldByHouseholdSerialNumber(householdSerialNumber);

        newMovementAddress.setPk(new HouseholdMovementAddress.Pk(householdMovementAddress.getHouseMovementReportDate(), householdSerialNumber));
        newMovementAddress.setHouseMovementAddress(householdMovementAddress.getHouseMovementAddress());
        newMovementAddress.setLastAddressYn(householdMovementAddress.getLastAddressYn());
        newMovementAddress.setHouseHold(household);

        return householdMovementAddressRepository.save(newMovementAddress);
    }
}
