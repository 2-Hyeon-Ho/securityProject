package com.nhnacademy.springjpa.controller.birthDeath;

import com.nhnacademy.springjpa.domain.RestBirthDto;
import com.nhnacademy.springjpa.entity.BirthDeathReportResident;
import com.nhnacademy.springjpa.exception.ValidationFailedException;
import com.nhnacademy.springjpa.service.birth.BirthDeleteService;
import com.nhnacademy.springjpa.service.birth.BirthModifyService;
import com.nhnacademy.springjpa.service.birth.BirthRegistrationService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/residents/{serialNumber}/birth")
public class BirthRestController {
    private final BirthRegistrationService birthRegistrationService;
    private final BirthModifyService birthModifyService;
    private final BirthDeleteService birthDeleteService;

    public BirthRestController(BirthRegistrationService birthRegistrationService, BirthModifyService birthModifyService, BirthDeleteService birthDeleteService) {
        this.birthRegistrationService = birthRegistrationService;
        this.birthModifyService = birthModifyService;
        this.birthDeleteService = birthDeleteService;
    }

    @PostMapping
    public BirthDeathReportResident registerBirth(@PathVariable("serialNumber") int reportResidentNumber,
                                                  @Valid @RequestBody RestBirthDto birth,
                                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        return birthRegistrationService.registerBirth(reportResidentNumber, birth);
    }

    @PutMapping("/{targetSerialNumber}")
    public BirthDeathReportResident modifyBirth(@PathVariable("serialNumber") int reportResidentNumber,
                                                @PathVariable("targetSerialNumber") int targetNumber,
                                                @Valid @RequestBody RestBirthDto birth,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        return birthModifyService.modifyBirth(reportResidentNumber, targetNumber, birth);
    }

    @DeleteMapping("/{targetSerialNumber}")
    public void deleteBirth(@PathVariable("serialNumber") int reportResidentNumber,
                            @PathVariable("targetSerialNumber") int targetNumber) {

        birthDeleteService.deleteBirth(reportResidentNumber, targetNumber);
    }
}
