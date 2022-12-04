package com.nhnacademy.springjpa.controller.household;

import com.nhnacademy.springjpa.domain.RestHouseholdDto;
import com.nhnacademy.springjpa.entity.Household;
import com.nhnacademy.springjpa.exception.ValidationFailedException;
import com.nhnacademy.springjpa.service.household.HouseholdDeleteService;
import com.nhnacademy.springjpa.service.household.HouseholdModifyService;
import com.nhnacademy.springjpa.service.household.HouseholdRegistrationService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/household")
public class HouseholdRestController {
    private final HouseholdRegistrationService householdRegistrationService;
    private final HouseholdDeleteService householdDeleteService;
    private final HouseholdModifyService householdModifyService;

    public HouseholdRestController(HouseholdRegistrationService householdRegistrationService, HouseholdDeleteService householdDeleteService, HouseholdModifyService householdModifyService) {
        this.householdRegistrationService = householdRegistrationService;
        this.householdDeleteService = householdDeleteService;
        this.householdModifyService = householdModifyService;
    }

    @PostMapping
    public Household registerHousehold(@Valid @RequestBody RestHouseholdDto household,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        return householdRegistrationService.householdRegistration(household);
    }

    @DeleteMapping("/{householdSerialNumber}")
    public void deleteHousehold(@PathVariable("householdSerialNumber") int householdSerialNumber) {
        householdDeleteService.householdDelete(householdSerialNumber);
    }

    @PostMapping("/{householdSerialNumber}")
    public Household modifyHousehold(@PathVariable("householdSerialNumber") int householdSerialNumber,
                                     @Valid @RequestBody RestHouseholdDto household,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        return householdModifyService.householdModify(householdSerialNumber, household);
    }
}
