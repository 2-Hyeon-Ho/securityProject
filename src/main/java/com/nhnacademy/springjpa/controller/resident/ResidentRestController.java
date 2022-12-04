package com.nhnacademy.springjpa.controller.resident;

import com.nhnacademy.springjpa.domain.RestResidentDto;
import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.exception.ValidationFailedException;
import com.nhnacademy.springjpa.service.resident.ResidentModifyService;
import com.nhnacademy.springjpa.service.resident.ResidentRegistrationService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/residents")
public class ResidentRestController {
    private final ResidentRegistrationService residentRegistrationService;
    private final ResidentModifyService residentModifyService;

    public ResidentRestController(ResidentRegistrationService residentRegistrationService, ResidentModifyService residentModifyService) {
        this.residentRegistrationService = residentRegistrationService;
        this.residentModifyService = residentModifyService;
    }

    @PostMapping
    public Resident registerResident(@Valid @RequestBody RestResidentDto resident,
                                     BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        return residentRegistrationService.residentRegistration(resident);
    }

    @PutMapping("/{residentId}")
    public Resident modifyResident(@PathVariable("residentId") Integer residentId,
                                   @Valid @RequestBody RestResidentDto resident,
                                   BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        return residentModifyService.residentModify(residentId, resident);
    }
}
