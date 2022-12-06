package com.nhnacademy.springjpa.service.householdMovementAddress;

import com.nhnacademy.springjpa.entity.HouseholdMovementAddress;
import com.nhnacademy.springjpa.exception.HouseholdMovementAddressNotFoundException;
import com.nhnacademy.springjpa.repository.householdMovementAddress.HouseholdMovementAddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Service
@Transactional
public class HouseholdMovementAddressDeleteService {
    private final HouseholdMovementAddressRepository householdMovementAddressRepository;

    public HouseholdMovementAddressDeleteService(HouseholdMovementAddressRepository householdMovementAddressRepository) {
        this.householdMovementAddressRepository = householdMovementAddressRepository;
    }

    public void householdMovementAddressDelete(int householdSerialNumber, String stringDate) {

        LocalDate reportDate = LocalDate.parse(stringDate, DateTimeFormatter.ISO_DATE);

        HouseholdMovementAddress deleteMovementAddress = householdMovementAddressRepository
                .findByPk_HouseMovementReportDateAndPk_HouseholdSerialNumber(reportDate, householdSerialNumber);

        if(Objects.isNull(deleteMovementAddress)) {
            throw new HouseholdMovementAddressNotFoundException();
        }

        householdMovementAddressRepository.delete(deleteMovementAddress);
    }
}
