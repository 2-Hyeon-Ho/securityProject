package com.nhnacademy.springjpa.controller.familyRelation;

import com.nhnacademy.springjpa.domain.RestFamilyRelationshipDto;
import com.nhnacademy.springjpa.entity.FamilyRelationship;
import com.nhnacademy.springjpa.exception.ValidationFailedException;
import com.nhnacademy.springjpa.service.familyRelation.FamilyRelationDeleteService;
import com.nhnacademy.springjpa.service.familyRelation.FamilyRelationModifyService;
import com.nhnacademy.springjpa.service.familyRelation.FamilyRelationRegistrationService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/residents/{serialNumber}/relationship")
public class FamilyRelationRestController {
    private final FamilyRelationRegistrationService familyRelationRegistrationService;
    private final FamilyRelationModifyService familyRelationModifyService;
    private final FamilyRelationDeleteService familyRelationDeleteService;

    public FamilyRelationRestController(FamilyRelationRegistrationService familyRelationRegistrationService, FamilyRelationModifyService familyRelationModifyService, FamilyRelationDeleteService familyRelationDeleteService) {
        this.familyRelationRegistrationService = familyRelationRegistrationService;
        this.familyRelationModifyService = familyRelationModifyService;
        this.familyRelationDeleteService = familyRelationDeleteService;
    }

    @PostMapping
    public FamilyRelationship registerRelationship(@PathVariable("serialNumber") int baseNumber,
                                                   @Valid @RequestBody RestFamilyRelationshipDto familyRelationship,
                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        return familyRelationRegistrationService.registerFamilyRelationship(baseNumber, familyRelationship);
    }

    @PutMapping("/{familySerialNumber}")
    public FamilyRelationship modifyRelationship(@PathVariable("serialNumber") int baseNumber,
                                                 @PathVariable("familySerialNumber") int targetNumber,
                                                 @Valid @RequestBody RestFamilyRelationshipDto familyRelationship,
                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        return familyRelationModifyService.modifyFamilyRelationship(baseNumber, targetNumber, familyRelationship);
    }

    @DeleteMapping("/{familySerialNumber}")
    public void deleteRelationship(@PathVariable("serialNumber") int baseNumber,
                                   @PathVariable("familySerialNumber") int targetNumber) {
        familyRelationDeleteService.deleteFamilyRelationship(baseNumber, targetNumber);
    }
}
