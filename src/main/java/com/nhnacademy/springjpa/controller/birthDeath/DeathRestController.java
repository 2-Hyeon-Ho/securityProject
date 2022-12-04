package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.domain.DeathDto;
import com.nhnacademy.springjpa.entity.BirthDeathReportResident;
import com.nhnacademy.springjpa.exception.ValidationFailedException;
import com.nhnacademy.springjpa.service.death.DeathDeleteService;
import com.nhnacademy.springjpa.service.death.DeathModifyService;
import com.nhnacademy.springjpa.service.death.DeathRegistrationService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/residents/{serialNumber}/death")
public class DeathRestController {
    private final DeathRegistrationService deathRegistrationService;
    private final DeathModifyService deathModifyService;
    private final DeathDeleteService deathDeleteService;

    public DeathRestController(DeathRegistrationService deathRegistrationService, DeathModifyService deathModifyService, DeathDeleteService deathDeleteService) {
        this.deathRegistrationService = deathRegistrationService;
        this.deathModifyService = deathModifyService;
        this.deathDeleteService = deathDeleteService;
    }

    @PostMapping
    public BirthDeathReportResident registerDeath(@PathVariable("serialNumber") int reportResidentNumber,
                                                  @Valid @RequestBody DeathDto death,
                                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        return deathRegistrationService.registerDeath(reportResidentNumber, death);
    }

    @PutMapping("/{targetSerialNumber}")
    public BirthDeathReportResident modifyDeath(@PathVariable("serialNumber") int reportResidentNumber,
                                                @PathVariable("targetSerialNumber") int targetNumber,
                                                @Valid @RequestBody DeathDto death,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        return deathModifyService.modifyDeath(reportResidentNumber, targetNumber, death);
    }

    @DeleteMapping("/{targetSerialNumber}")
    public void deleteDeath(@PathVariable("serialNumber") int reportResidentNumber,
                            @PathVariable("targetSerialNumber") int targetNumber) {

        deathDeleteService.deleteDeath(reportResidentNumber, targetNumber);
    }
}
