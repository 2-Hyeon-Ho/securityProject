package com.nhnacademy.springjpa.service.householdMovementAddress;

import com.nhnacademy.springjpa.domain.RestHouseholdMovementAddressDto;
import com.nhnacademy.springjpa.entity.HouseholdMovementAddress;
import com.nhnacademy.springjpa.exception.HouseholdMovementAddressNotFoundException;
import com.nhnacademy.springjpa.repository.householdMovementAddress.HouseholdMovementAddressRepository;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Service
@Transactional
public class HouseholdMovementAddressModifyService {
    private final HouseholdMovementAddressRepository householdMovementAddressRepository;

    public HouseholdMovementAddressModifyService(HouseholdMovementAddressRepository householdMovementAddressRepository) {
        this.householdMovementAddressRepository = householdMovementAddressRepository;
    }

    public HouseholdMovementAddress householdMovementAddressModify(int householdSerialNumber,
                                                                   String stringDate,
                                                                   RestHouseholdMovementAddressDto householdMovementAddress) {

        LocalDate reportDate = LocalDate.parse(stringDate, DateTimeFormatter.ISO_DATE);
        HouseholdMovementAddress modifyMovementAddress = householdMovementAddressRepository
                .findByPk_HouseMovementReportDateAndPk_HouseholdSerialNumber(reportDate, householdSerialNumber);

        if(Objects.isNull(modifyMovementAddress)) {
            throw new HouseholdMovementAddressNotFoundException();
        }

        modifyMovementAddress.setHouseMovementAddress(householdMovementAddress.getHouseMovementAddress());

        return householdMovementAddressRepository.save(modifyMovementAddress);
    }
}
