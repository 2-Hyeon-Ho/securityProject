package com.nhnacademy.springjpa.controller.householdMovementAddress;

import com.nhnacademy.springjpa.domain.RestHouseholdMovementAddressDto;
import com.nhnacademy.springjpa.entity.HouseholdMovementAddress;
import com.nhnacademy.springjpa.exception.ValidationFailedException;
import com.nhnacademy.springjpa.service.householdMovementAddress.HouseholdMovementAddressDeleteService;
import com.nhnacademy.springjpa.service.householdMovementAddress.HouseholdMovementAddressModifyService;
import com.nhnacademy.springjpa.service.householdMovementAddress.HouseholdMovementAddressRegistrationService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("/household/{householdSerialNumber}/movement")
public class HouseholdMovementAddressRestController {
    private final HouseholdMovementAddressRegistrationService householdMovementAddressRegistrationService;
    private final HouseholdMovementAddressModifyService householdMovementAddressModifyService;
    private final HouseholdMovementAddressDeleteService householdMovementAddressDeleteService;

    public HouseholdMovementAddressRestController(HouseholdMovementAddressRegistrationService householdMovementAddressRegistrationService, HouseholdMovementAddressModifyService householdMovementAddressModifyService, HouseholdMovementAddressDeleteService householdMovementAddressDeleteService) {
        this.householdMovementAddressRegistrationService = householdMovementAddressRegistrationService;
        this.householdMovementAddressModifyService = householdMovementAddressModifyService;
        this.householdMovementAddressDeleteService = householdMovementAddressDeleteService;
    }

    @PostMapping
    public HouseholdMovementAddress registerHouseholdMovementAddress(@PathVariable("householdSerialNumber") int householdSerialNumber,
                                                                     @Valid @RequestBody RestHouseholdMovementAddressDto householdMovementAddress,
                                                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        return householdMovementAddressRegistrationService.householdMovementAddressRegistration(householdSerialNumber, householdMovementAddress);
    }

    @PutMapping("/{reportDate}")
    public HouseholdMovementAddress modifyHouseholdMovementAddress(@PathVariable("householdSerialNumber") int householdSerialNumber,
                                                                   @PathVariable("reportDate") String stringDate,
                                                                   @Valid @RequestBody RestHouseholdMovementAddressDto householdMovementAddress,
                                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        return householdMovementAddressModifyService.householdMovementAddressModify(householdSerialNumber, stringDate, householdMovementAddress);
    }

    @DeleteMapping("/{reportDate}")
    public void deleteHouseholdMovementAddress(@PathVariable("householdSerialNumber") int householdSerialNumber,
                                                                   @PathVariable("reportDate") String stringDate) {

        householdMovementAddressDeleteService.householdMovementAddressDelete(householdSerialNumber, stringDate);
    }
}
